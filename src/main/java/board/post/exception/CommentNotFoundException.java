package board.post.exception;

import board.common.exception.NotFound;

public class CommentNotFound extends NotFound {
    public CommentNotFound(String message) {
        super(message);
    }
}
