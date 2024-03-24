package board.member.application;

import board.member.domain.Member;
import board.member.persistence.MemberRepository;
import board.member.presentation.dto.LoginMemberInfo;
import board.member.presentation.dto.request.MemberSignInRequest;
import board.member.presentation.dto.request.MemberSignUpRequest;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void createMember(final MemberSignUpRequest memberSignUpRequest) {
        checkDuplicatedEmail(memberSignUpRequest.getEmail());
        memberRepository.save(memberSignUpRequest.toEntity());
    }

    public void checkDuplicatedEmail(final String email) {
        final List<Long> memberIdByEmail = memberRepository.findIdByEmail(email);
        if (!memberIdByEmail.isEmpty()) {
            throw new RuntimeException("중복된 이메일");
        }
    }

    @Transactional
    public LoginMemberInfo authenticate(final MemberSignInRequest memberSignInRequest) {
        Member member = findMember(memberSignInRequest.getEmail(), memberSignInRequest.getPassword());
        member.updateCurrentTimeModifiedAt();
        return new LoginMemberInfo(member);
    }

    public Member findMember(final String email, final String password) {
        final List<Member> matchedMember = memberRepository.findAllByEmailAndPassword(email, password);
        if (matchedMember.isEmpty()) {
            throw new RuntimeException("아이디 또는 패스워드가 틀렸습니다.");
        }
        return matchedMember.get(0);
    }

    public Member findMember(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
    }
}

