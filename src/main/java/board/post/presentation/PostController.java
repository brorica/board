package board.post.presentation;

import board.member.presentation.dto.LoginMemberInfo;
import board.post.application.PostService;
import board.post.presentation.request.PostCreateRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> createPost( @SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                            @RequestBody final PostCreateRequest postCreateRequest) {
        final Long postId = postService.createPost(loginMemberInfo.getId(), postCreateRequest);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                   .buildAndExpand(postId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost( @SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                            @PathVariable("id") final Long postId) {
        postService.DeletePost(loginMemberInfo.getId(), postId);
        return ResponseEntity.ok().build();
    }
}
