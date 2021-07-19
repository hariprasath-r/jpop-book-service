package in.hp.java.bookservice.controller.contract;

import in.hp.java.bookservice.dto.BookApiResponse;
import in.hp.java.bookservice.dto.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Controller", description = "Book data manipulation")
@RequestMapping("/books")
public interface BookController {

    @Operation(summary = "Gets all Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping
    ResponseEntity<BookApiResponse<List<BookDto>>> getBooks();

    @Operation(summary = "Gets a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found"),
            @ApiResponse(responseCode = "404", description = "Book Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/{id}")
    ResponseEntity<BookApiResponse<BookDto>> getBook(@PathVariable Long id);

    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Added"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @Operation(summary = "Updates a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Updated"),
            @ApiResponse(responseCode = "404", description = "Book Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PutMapping
    ResponseEntity<Object> updateBook(@RequestBody BookDto book);

    @Operation(summary = "Deletes a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410", description = "Book Deleted"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteBook(@PathVariable Long id);

    default <T> ResponseEntity<BookApiResponse<T>> generateResponse(T response, HttpStatus httpStatus) {
        var bookApiResponse = BookApiResponse.<T>builder()
                .response(response)
                .build();

        return ResponseEntity.status(httpStatus)
                .body(bookApiResponse);
    }
}
