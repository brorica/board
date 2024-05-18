package board.post.presentation.response;

import board.post.domain.Post;
import java.time.LocalDateTime;

public class PostDetailResponse {

    private final Long postId;

    private final String title;

    private final String creator;

    private final LocalDateTime createdAt;

    private final Long viewCount;

    private final String content;

    public PostDetailResponse(final Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.creator = post.getCreator();
        this.createdAt = post.getCreatedAt();
        this.viewCount = post.getViewCount();
        this.content = post.getContent();
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public String getContent() {
        return content;
    }
}
