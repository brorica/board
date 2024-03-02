package board.post.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostUpdateRequest {

    private final String title;

    private final String content;

    public PostUpdateRequest(@JsonProperty("title") final String title,
                             @JsonProperty("content") final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
