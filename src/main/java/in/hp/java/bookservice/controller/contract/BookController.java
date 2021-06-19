package in.hp.java.bookservice.controller.contract;

import in.hp.java.bookservice.dto.BookApiResponse;
import in.hp.java.bookservice.dto.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
public interface BookController {

    @Operation(summary = "Gets all Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping
    ResponseEntity<BookApiResponse<Object>> getBooks();

    @Operation(summary = "Gets a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found"),
            @ApiResponse(responseCode = "404", description = "Book Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/{id}")
    ResponseEntity<BookApiResponse<Object>> getBook(@PathVariable Long id);

    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Added"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Updated"),
            @ApiResponse(responseCode = "404", description = "Book Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PutMapping
    ResponseEntity<Object> updateBook(@RequestBody BookDto book);

    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410", description = "Book Deleted"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
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
