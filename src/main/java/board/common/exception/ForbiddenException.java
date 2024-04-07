package board.common.exception;

// 403 Forbidden
public class ForbiddenException extends RuntimeException {

    public static final String POST_NOT_OWNER= "글 작성자가 아닙니다.";
    public static final String COMMENT_NOT_OWNER= "댓글 작성자가 아닙니다.";

    public ForbiddenException(String message) {
        super(message);
    }
}
