package board.post.application;

import board.common.PageableRequest;
import board.common.PageableResponse;
import board.post.domain.Post;
import board.post.presentation.response.PostListEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostPaginationService {

    private final PostService postService;

    public PostPaginationService(PostService postService) {
        this.postService = postService;
    }

    public PageableResponse readPostList(final int page, final int size) {
        final Pageable pageable = PageableRequest.createPageRequest(page, size, Sort.Direction.DESC, "id");
        final Page<Post> postsPage = postService.readPostList(pageable);
        final List<PostListEntry> postListEntries = postsPageToDtoList(postsPage);
        final List<Integer> pageNumbers = getPageNumbers(pageable, postsPage.getTotalPages());
        return buildPostPageableResponse(postsPage, postListEntries, pageNumbers);
    }

    private List<PostListEntry> postsPageToDtoList(final Page<Post> postsPage) {
        return postsPage.getContent()
                .stream()
                .map(post -> new PostListEntry(post.getId(),
                        post.getTitle(),
                        post.getCreator(),
                        post.getCreatedAt(),
                        post.getViewCount(),
                        post.getUpvoteCount())
                )
                .collect(Collectors.toList());
    }

    private List<Integer> getPageNumbers(final Pageable pageable, int totalPages) {
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

    private PageableResponse buildPostPageableResponse(final Page<Post> postsPage,
                                                       final List<PostListEntry> postListEntries,
                                                       final List<Integer> pageNumbers) {
        return new PageableResponse<>(postListEntries,
                postsPage.getNumber(),
                postsPage.isFirst(),
                postsPage.isLast(),
                pageNumbers);
    }
}
