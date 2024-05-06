package board.post.application;

import board.common.PageableResponse;
import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.domain.Post;
import board.post.exception.PostNotFoundException;
import board.post.persistence.PostRepository;
import board.post.presentation.request.PostCreateRequest;
import board.post.presentation.request.PostUpdateRequest;
import board.post.presentation.response.PostDetailResponse;
import board.post.presentation.response.PostListEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static board.common.exception.NotFoundException.POST_NOT_FOUND;

@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

    public PostService(final PostRepository postRepository, final MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    @Transactional
    public Long createPost(final Long memberId, final PostCreateRequest postCreateRequest) {
        final Member member = memberService.findMember(memberId);
        return postRepository.save(new Post(member, postCreateRequest)).getId();
    }

    public Post findPost(final Long postId) {
        return postRepository.findByIdAndDeleteIsFalse(postId)
            .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
    }

    @Transactional
    public void deletePost(final Long memberId, final Long postId) {
        Post post = findPost(postId);
        post.delete(memberId);
    }

    @Transactional
    public void updatePost(final Long memberId, final Long postId, final PostUpdateRequest postUpdateRequest) {
        Post post = findPost(postId);
        post.update(memberId, postUpdateRequest);
    }

    /**
     * 이 메소드는 게시글 상세정보를 반환하면서 조회수를 올리는 2가지 역할을 함
     * 이걸 분리할지 말지에 대해 찾아봐야 함
     */
    @Transactional
    public PostDetailResponse readPostDetailAndIncreaseViewCount(final Long postId) {
        Post post = findPost(postId);
        post.increaseViewCount();
        return new PostDetailResponse(post);
    }

    public Page<Post> readPostList(final Pageable pageable) {
        return postRepository.findByDeleteFalseOrderByIdDesc(pageable);
    }

    @Transactional
    public void increaseOrDecreasePostUpvote(final Long postId) {
        // todo: 추천 관련 erd 그리고 구현하기
        final Post post = findPost(postId);
    }
}
