package board.post.exception;

import board.common.exception.NotFound;

public class PostNotFound extends NotFound {
    public PostNotFound(String message) {
        super(message);
    }
}
