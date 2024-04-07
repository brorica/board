package board.member.exception;

import board.common.exception.UnauthorizedException;

public class LoginFailException extends UnauthorizedException {
    public LoginFailException(String message) {
        super(message);
    }
}
