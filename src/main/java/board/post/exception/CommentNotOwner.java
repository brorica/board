package board.post.exception;

import board.common.exception.ForbiddenException;

public class CommentNotOwner extends ForbiddenException {
    public CommentNotOwner(String message) {
        super(message);
    }
}
