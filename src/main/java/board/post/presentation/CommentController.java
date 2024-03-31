package board.post.presentation;

import board.member.presentation.dto.LoginMemberInfo;
import board.post.application.CommentService;
import board.post.presentation.request.CommentCreateRequest;
import board.post.presentation.request.CommentUpdateRequest;
import board.post.presentation.response.CommentList;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/posts/{postId}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                              @PathVariable("postId") final Long postId,
                                              @RequestBody final CommentCreateRequest commentCreateRequest) {
        final Long commentId = commentService.createComment(loginMemberInfo.getId(), postId, commentCreateRequest);
        // todo post에도 uri 중복 생성하는거 간소화하기
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(commentId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                              @PathVariable("commentId") final Long commentId) {
        commentService.deleteComment(loginMemberInfo.getId(), commentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                              @PathVariable("commentId") final Long commentId,
                                              @RequestBody final CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(loginMemberInfo.getId(), commentId, commentUpdateRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * todo: 대댓글 처리와 페이지네이션을 어떻게 할지 생각하기
     */
    @GetMapping
    public ResponseEntity<CommentList> getCommentList(@PathVariable("postId") final Long postId) {
        final CommentList commentList = commentService.readCommentList(postId);
        return ResponseEntity.ok(commentList);
    }

}
