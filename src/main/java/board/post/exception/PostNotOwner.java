package board.post.exception;

import board.common.exception.ForbiddenException;

public class PostNotOwner extends ForbiddenException {
    public PostNotOwner(String message) {
        super(message);
    }
}
