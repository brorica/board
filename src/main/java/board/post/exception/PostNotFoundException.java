package board.post.exception;

import board.common.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
