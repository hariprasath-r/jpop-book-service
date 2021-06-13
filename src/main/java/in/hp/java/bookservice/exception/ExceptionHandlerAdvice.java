package in.hp.java.bookservice.exception;

import in.hp.java.bookservice.dto.BookApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private BookApiResponse<Object> generateBookApiResponse(String message, String desciption) {
        return BookApiResponse.builder()
                .response(new ApiError(message, desciption))
                .build();
    }

    @ExceptionHandler({BookNotFoundException.class})
    public final ResponseEntity<BookApiResponse<Object>> handleUserNotFoundException(
            BookNotFoundException ex,
            WebRequest request
    ) {
        log.error("UserNotFoundException: {}", ex.getMessage());
        var BookApiResponse = generateBookApiResponse(
                ex.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BookApiResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BookApiResponse<Object>> handleException(Exception ex) {
        log.error("Exception Occurred: {}", ex.getMessage());
        var BookApiResponse = generateBookApiResponse(
                ex.getMessage(), ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookApiResponse);
    }

}
