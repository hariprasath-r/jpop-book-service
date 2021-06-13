package in.hp.java.bookservice.mapper;

import in.hp.java.bookservice.dto.BookDto;
import in.hp.java.bookservice.entity.Book;

public class BookMapper {

    private BookMapper() {
    }

    public static BookDto mapToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .description(book.getDescription())
                .build();
    }

    public static Book mapToEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .bookName(bookDto.getBookName())
                .author(bookDto.getAuthor())
                .genre(bookDto.getGenre())
                .description(bookDto.getDescription())
                .build();
    }
}
