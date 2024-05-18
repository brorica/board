package board.upvote;

import board.member.presentation.dto.LoginMemberInfo;
import board.upvote.application.UpvoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequestMapping("/api/upvote")
public class UpvoteController {

    private final UpvoteService upvoteService;

    public UpvoteController(final UpvoteService upvoteService) {
        this.upvoteService = upvoteService;
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> toggleUpvote(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                             @PathVariable("postId") final Long postId) {
        upvoteService.toggleUpvote(loginMemberInfo.getId(), postId);
        return ResponseEntity.ok().build();
    }
}
