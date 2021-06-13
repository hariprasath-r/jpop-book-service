package in.hp.java.bookservice.controller.contract;

import in.hp.java.bookservice.dto.BookApiResponse;
import in.hp.java.bookservice.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
public interface BookController {

    @GetMapping
    ResponseEntity<BookApiResponse<Object>> getBooks();

    @GetMapping("/{id}")
    ResponseEntity<BookApiResponse<Object>> getBook(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @PutMapping
    ResponseEntity<Object> updateBook(@RequestBody BookDto book);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteMapping(@PathVariable Long id);

    default <T> ResponseEntity<BookApiResponse<Object>> generateResponse(T response, HttpStatus httpStatus) {
        var bookApiResponse = BookApiResponse.builder()
                .response(response)
                .build();

        return ResponseEntity.status(httpStatus)
                .body(bookApiResponse);
    }
}
