package board.post.presentation.response;

import board.post.domain.Post;

public class PostDetailResponse {

    private Long id;
    private String title;
    private String content;

    public PostDetailResponse(final Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
