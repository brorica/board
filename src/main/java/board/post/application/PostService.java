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
        final List<PostListEntry> postListEntries = postsPage.getContent().stream()
                                                             .map(post -> new PostListEntry(post.getId(), post.getTitle()))
                                                             .collect(Collectors.toList());
        final List<Integer> pageNumbers = getPageNumbers(pageable.getPageNumber(), postListEntries);
        return new PageableResponse<>(postListEntries,
                                      postsPage.getNumber(),
                                      postsPage.isFirst(),
                                      postsPage.isLast(),
                                      pageNumbers);
    }

    private List<Integer> getPageNumbers(int page, List<PostListEntry> postListEntries) {
        List<Integer> pageNumbers = new ArrayList<>();
        // 존재하지 않는 페이지로 이동시 빈 값 반환
        if(postListEntries.isEmpty()) {
            return pageNumbers;
        }
        final long pageFirstPostId = postListEntries.get(0).getPostId();
        final int total = postRepository.countByDeleteFalseAndIdLessThanEqual(pageFirstPostId);
        // 페이지당 10개의 게시글을 보여주므로 몇 개의 페이지가 나오는지 계산
        int totalPageNumber = total / 10;
        // 마지막 페이지에서 10개 미만인 경우 하나의 페이지로 간주
        if(total % 10 > 0) {
            totalPageNumber += 1;
        }
        // 페이지 리스트를 10개 까지 보여줘야 함
        if(totalPageNumber < 10) {
            int prevPageNumberStart = 10 - totalPageNumber;
            for (int i = prevPageNumberStart; i > 0; i--) {
                pageNumbers.add(page - i);
            }
            for (int i = 0; i < totalPageNumber; i++) {
                pageNumbers.add(page + i);
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                pageNumbers.add(page + i);
            }
        }
        return pageNumbers;
    }
}
