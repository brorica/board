package board.post.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentCreateRequest {

    private final String content;

    public CommentCreateRequest(@JsonProperty("content") final String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
