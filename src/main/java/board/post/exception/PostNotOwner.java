package board.post.exception;

import board.common.exception.ForbiddenException;

public class PostNotOwnerException extends ForbiddenException {
    public PostNotOwnerException(String message) {
        super(message);
    }
}
