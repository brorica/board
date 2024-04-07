package board.common;

import board.common.exception.ConflictException;
import board.common.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorMessage buildErrorMessage(Exception exception, HttpServletRequest request) {
        return new ErrorMessage.Builder()
                .title(exception.getClass().getSimpleName())
                .detail(exception.getMessage())
                .instance(request.getRequestURI())
                .build();
    }

    // 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorMessage(e, request), NOT_FOUND);
    }

    // 409
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleConflictException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorMessage(e, request), CONFLICT);
    }
}
