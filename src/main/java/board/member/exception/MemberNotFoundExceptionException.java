package board.member.exception;

import board.common.exception.NotFoundException;

public class MemberNotFoundExceptionException extends NotFoundException {
    public MemberNotFoundExceptionException(String message) {
        super(message);
    }
}
