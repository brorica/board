package board.post.presentation.response;

import board.post.domain.Post;

public class PostDetailResponse {

    private Long postId;
    private String title;
    private String content;

    public PostDetailResponse(final Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
