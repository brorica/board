package board.post.presentation.response;

import java.util.Collections;
import java.util.List;

public class CommentList {

    private final List<CommentResponse> commentResponseList;

    public CommentList(final List<CommentResponse> commentResponseList) {
        this.commentResponseList = Collections.unmodifiableList(commentResponseList);
    }

    public List<CommentResponse> getCommentResponseList() {
        return commentResponseList;
    }
}
