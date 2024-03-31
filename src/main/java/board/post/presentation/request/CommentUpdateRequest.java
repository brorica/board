package board.post.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentUpdateRequest {

    private final String content;

    public CommentUpdateRequest(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
