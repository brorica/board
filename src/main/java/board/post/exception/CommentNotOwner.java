package board.post.exception;

import board.common.exception.ForbiddenException;

public class CommentNotOwnerException extends ForbiddenException {
    public CommentNotOwnerException(String message) {
        super(message);
    }
}
