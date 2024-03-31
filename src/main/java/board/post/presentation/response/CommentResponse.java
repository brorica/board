package board.post.presentation.response;

import board.post.domain.Comment;
import java.time.LocalDateTime;

public class CommentResponse {

    private final Long postId;

    private final Long commentId;

    private final String content;

    private final String creator;

    private final LocalDateTime createdAt;

    public CommentResponse(final Comment comment) {
        this.postId = comment.getPostId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.creator = comment.getCreator();
        this.createdAt = comment.getCreatedAt();
    }

    public Long getPostId() {
        return postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public String getCreator() {
        return creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
