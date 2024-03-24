package board.post.presentation.response;

import java.time.LocalDateTime;

public class PostListEntry {

    private final Long postId;

    private final String title;

    private final String creator;

    private final LocalDateTime createdAt;

    private final Long viewCount;

    private final Long upvoteCount;

    public PostListEntry(final Long postId,
                         final String title,
                         final String creator,
                         final LocalDateTime createdAt,
                         final Long viewCount,
                         final Long upvoteCount) {
        this.postId = postId;
        this.title = title;
        this.creator = creator;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.upvoteCount = upvoteCount;
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

    public Long getUpvoteCount() {
        return upvoteCount;
    }
}
