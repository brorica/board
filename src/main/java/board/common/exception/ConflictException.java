package board.common.exception;

public class Conflict extends RuntimeException {

    public static final String MEMBER_SIGNUP_DUPLICATED_EMAIL = "중복된 이메일입니다.";

    public Conflict(String message) {
        super(message);
    }
}
