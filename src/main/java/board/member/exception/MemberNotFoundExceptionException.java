package board.member.exception;

import board.common.exception.NotFound;

public class MemberNotFoundException extends NotFound {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
