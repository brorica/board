package board.member.application;

import board.member.domain.Member;
import board.member.exception.DuplicatedEmailException;
import board.member.exception.LoginFailException;
import board.member.exception.MemberNotFoundExceptionException;
import board.member.persistence.MemberRepository;
import board.member.presentation.dto.LoginMemberInfo;
import board.member.presentation.dto.request.MemberSignInRequest;
import board.member.presentation.dto.request.MemberSignUpRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static board.common.exception.ConflictException.MEMBER_SIGNUP_DUPLICATED_EMAIL;
import static board.common.exception.NotFoundException.MEMBER_NOT_FOUND;
import static board.common.exception.UnauthorizedException.MEMBER_SIGN_IN_UNAUTHORIZED;

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
            throw new DuplicatedEmailException(MEMBER_SIGNUP_DUPLICATED_EMAIL);
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
            throw new LoginFailException(MEMBER_SIGN_IN_UNAUTHORIZED);
        }
        return matchedMember.get(0);
    }

    public Member findMember(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundExceptionException(MEMBER_NOT_FOUND));
    }

    public void checkEmailDuplicatedAtSigunUp(final String email) {
        Optional<Member> optionalMemberByEmail = memberRepository.findByEmail(email);
        if (optionalMemberByEmail.isPresent()) {
            throw new DuplicatedEmailException(MEMBER_SIGNUP_DUPLICATED_EMAIL);
        }
    }
}

