package board.member.presentation;

import board.member.application.MemberService;
import board.member.presentation.dto.LoginMemberInfo;
import board.member.presentation.dto.request.MemberSignInRequest;
import board.member.presentation.dto.request.MemberSignUpRequest;
import jakarta.servlet.http.HttpSession;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/members")
@RestController
public class MemberPresentation {

    private final MemberService memberService;

    public MemberPresentation(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUpMember(@RequestBody final MemberSignUpRequest memberSignUpRequest) {
        memberService.createMember(memberSignUpRequest);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signInMember(@RequestBody final MemberSignInRequest memberSignInRequest, HttpSession session) {
        final LoginMemberInfo loginMemberInfo = memberService.authenticate(memberSignInRequest);
        session.setAttribute("loginMemberInfo", loginMemberInfo);
        return ResponseEntity.ok().build();
    }
}
