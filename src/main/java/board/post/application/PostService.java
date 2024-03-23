package board.post.application;

import board.common.PageableResponse;
import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.domain.Post;
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

@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

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
    public void updatePost(final Long memberId, final Long postId,
        final PostUpdateRequest postUpdateRequest) {
        final Post post = findPost(postId);
        post.update(memberId, postUpdateRequest);
    }

    public PostDetailResponse readPostDetail(final Long postId) {
        final Post post = findPost(postId);
        return new PostDetailResponse(post);
    }

    public PageableResponse readPostList(final Pageable pageable) {
        final Page<Post> postsPage = postRepository.findByDeleteFalseOrderByIdDesc(pageable);
        final List<PostListEntry> postListEntries = postsPage.getContent()
                                                             .stream()
                                                             .map(post -> new PostListEntry(post.getId(), post.getTitle()))
                                                             .collect(Collectors.toList());
        final List<Integer> pageNumbers = getPageNumbers(pageable, postsPage.getTotalPages());
        return new PageableResponse<>(postListEntries,
                                      postsPage.getNumber(),
                                      postsPage.isFirst(),
                                      postsPage.isLast(),
                                      pageNumbers);
    }

    private List<Integer> getPageNumbers(Pageable pageable, int totalPages) {
        int currentPage = pageable.getPageNumber();
        List<Integer> pageNumbers = new ArrayList<>();

        int startPage = Math.max(currentPage - 4, 0);
        int endPage = Math.min(startPage + 10, totalPages);

        // 시작 페이지를 조정하여 항상 페이지 목록이 10개가 되도록 함
        if (endPage - startPage < 10) {
            startPage = Math.max(endPage - 10, 0);
        }
        for (int i = startPage; i < endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }
}
