package board.post.presentation.response;

public class PostListEntry {

    private final Long postId;

    private final String title;

    public PostListEntry(final Long postId, final String title) {
        this.postId = postId;
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }
}
