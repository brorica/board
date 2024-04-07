package board.member.exception;

import board.common.exception.ConflictException;

public class DuplicatedEmailException extends ConflictException {
    public DuplicatedEmailException(String message) {
        super(message);
    }
}
