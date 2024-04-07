package board.common.exception;

// 404 Not Found
public class NotFound extends RuntimeException {

    public static final String POST_NOT_FOUND = "존재하지 않는 글입니다.";
    public static final String COMMENT_NOT_FOUND = "존재하지 않는 댓글입니다.";
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 회원입니다.";

    public NotFound(String message) {
        super(message);
    }
}
