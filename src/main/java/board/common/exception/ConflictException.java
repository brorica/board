package board.common.exception;

public class ConflictException extends RuntimeException {

    public static final String MEMBER_SIGNUP_DUPLICATED_EMAIL = "중복된 이메일입니다.";

    public ConflictException(String message) {
        super(message);
    }
}
