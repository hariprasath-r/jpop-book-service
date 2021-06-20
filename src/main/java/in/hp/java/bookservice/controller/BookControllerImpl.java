package in.hp.java.bookservice.controller;

import in.hp.java.bookservice.controller.contract.BookController;
import in.hp.java.bookservice.dto.BookApiResponse;
import in.hp.java.bookservice.dto.BookDto;
import in.hp.java.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Controller")
@RestController
@Slf4j
public class BookControllerImpl implements BookController {

    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<BookApiResponse<Object>> getBooks() {
        log.info("Get Books");
        return generateResponse(bookService.getBooks(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookApiResponse<Object>> getBook(Long id) {
        log.info("Get Book: {}", id);
        return generateResponse(bookService.getBook(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addBook(BookDto book) {
        log.info("Add Book: {}", book);
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Object> updateBook(BookDto book) {
        log.info("Update Book: {}", book);
        bookService.updateBook(book);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Object> deleteMapping(Long id) {
        log.info("Delete Book: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
