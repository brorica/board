package board.common.exception;

// 403 Forbidden
public class Forbidden extends RuntimeException {

    public static final String POST_NOT_OWNER= "글 작성자가 아닙니다.";
    public static final String COMMENT_NOT_OWNER= "댓글 작성자가 아닙니다.";

    public Forbidden(String message) {
        super(message);
    }
}
