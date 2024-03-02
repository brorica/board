package board.post.application;

import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.domain.Post;
import board.post.persistence.PostRepository;
import board.post.presentation.request.PostCreateRequest;
import board.post.presentation.request.PostUpdateRequest;
import board.post.presentation.response.PostDetailResponse;
import board.post.presentation.response.PostList;
import board.post.presentation.response.PostListEntry;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

    public static int PAGE_SIZE = 3;

    public PostService(final PostRepository postRepository, final MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    @Transactional(readOnly = false)
    public Long createPost(final Long memberId, final PostCreateRequest postCreateRequest) {
        final Member member = memberService.findMember(memberId);
        return postRepository.save(new Post(member, postCreateRequest)).getId();
    }

    public Post findPost(final Long postId) {
        return postRepository.findByIdAndDeleteIsFalse(postId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
    }

    @Transactional(readOnly = false)
    public void deletePost(final Long memberId, final Long postId) {
        final Post post = findPost(postId);
        post.delete(memberId);
    }

    @Transactional(readOnly = false)
    public void updatePost(final Long memberId, final Long postId, final PostUpdateRequest postUpdateRequest) {
        final Post post = findPost(postId);
        post.update(memberId, postUpdateRequest);
    }

    public PostDetailResponse readPostDetail(final Long postId) {
        final Post post = findPost(postId);
        return new PostDetailResponse(post);
    }

    public PostList readPostList(Long lastPostId) {
        final PageRequest pageRequest  = PageRequest.of(0, PAGE_SIZE);
        final List<PostListEntry> postsPage10 = postRepository.findPostsPage10(lastPostId, pageRequest);
        return new PostList(postsPage10);
    }
}
