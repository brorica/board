package board.common.exception;

public class Unauthorized extends RuntimeException {

    public static final String MEMBER_SIGN_IN_UNAUTHORIZED = "아이디 또는 패스워드가 틀렸습니다.";
    public Unauthorized(String message) {
        super(message);
    }
}
