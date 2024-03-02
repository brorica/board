package board.post.presentation;

import board.member.presentation.dto.LoginMemberInfo;
import board.post.application.PostService;
import board.post.presentation.request.PostCreateRequest;
import board.post.presentation.request.PostUpdateRequest;
import board.post.presentation.response.PostDetailResponse;
import board.post.presentation.response.PostList;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Void> createPost(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                           @RequestBody final PostCreateRequest postCreateRequest) {
        final Long postId = postService.createPost(loginMemberInfo.getId(), postCreateRequest);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                   .buildAndExpand(postId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                           @PathVariable("id") final Long postId) {
        postService.deletePost(loginMemberInfo.getId(), postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@SessionAttribute("loginMemberInfo") final LoginMemberInfo loginMemberInfo,
                                           @PathVariable("id") final Long postId,
                                           @RequestBody final PostUpdateRequest postUpdateRequest) {
        postService.updatePost(loginMemberInfo.getId(), postId, postUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable("id") final Long postId) {
        return ResponseEntity.ok(postService.readPostDetail(postId));
    }

    @GetMapping
    public ResponseEntity<PostList> getPostList(@RequestParam(value = "lastId", required  = false) Long lastId) {
        lastId = validateAndReplacePostId(lastId);
        final PostList postList = postService.readPostList(lastId);
        return ResponseEntity.ok(postList);
    }

    private Long  validateAndReplacePostId(final Long lastId) {
        if (lastId == null || lastId <= 0) {
            return Long.MAX_VALUE;
        }
        return lastId;
    }
}
