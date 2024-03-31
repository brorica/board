package board.member.presentation;

import board.member.application.MemberService;
import board.member.domain.Member;
import board.member.presentation.dto.LoginMemberInfo;
import board.member.presentation.dto.request.MemberSignInRequest;
import board.member.presentation.dto.request.MemberSignUpRequest;
import board.member.presentation.dto.response.SignInResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/members")
@RestController
public class MemberPresentation {

    private final MemberService memberService;

    private final String clientRoute = "http://localhost:3000";

    public MemberPresentation(final MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUpMember(@RequestBody final MemberSignUpRequest memberSignUpRequest) {
        memberService.createMember(memberSignUpRequest);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * 중복 체크
     */
    @GetMapping("/sign-up")
    public ResponseEntity<Void> checkDuplicateEmail(@RequestParam(value = "email") final String email) {
        final Boolean isDuplicated = memberService.checkDuplicated(email);
        if(isDuplicated) {
            return ResponseEntity.status(409).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    /**
     * 로그인
     */
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signInMember(
        @RequestBody final MemberSignInRequest memberSignInRequest, HttpSession session) {
        final LoginMemberInfo loginMemberInfo = memberService.authenticate(memberSignInRequest);
        session.setAttribute("loginMemberInfo", loginMemberInfo);
        return ResponseEntity.ok(new SignInResponse(loginMemberInfo.getEmail()));
    }

    /**
     * 로그아웃
     */
    @GetMapping("/sign-out")
    public ResponseEntity<Void> signOutMember(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
