package in.hp.java.bookservice.service;

import in.hp.java.bookservice.dto.BookDto;
import in.hp.java.bookservice.entity.Book;
import in.hp.java.bookservice.exception.BookNotFoundException;
import in.hp.java.bookservice.mapper.BookMapper;
import in.hp.java.bookservice.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public void addBook(BookDto bookDto) {
        var book = bookMapper.toEntity(bookDto);
        log.info("Mapped to entity: {}", book);
        bookRepository.save(book);
    }

    private Book findBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            log.info("Book found: {}", id);
            return book.get();
        } else {
            var errorMessage = "Book not found: " + id;
            log.info(errorMessage);
            throw new BookNotFoundException(errorMessage);
        }
    }

    public BookDto getBook(Long id) {
        return bookMapper.toDto(findBook(id));
    }

    public List<BookDto> getBooks() {
        var bookList = bookRepository.findAll();
        log.info("Books from repo: {}", bookList);
        return bookList.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooks(List<Long> bookIdentifiers) {
        log.info("Book Identifiers received :: {}", bookIdentifiers.size());
        var books = bookRepository.findAllById(bookIdentifiers);
        log.info("Books found: {}", books.size());
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateBook(BookDto bookDto) {
        findBook(bookDto.getId());
        bookRepository.save(bookMapper.toEntity(bookDto));
        log.info("Book {}, updated successfully.", bookDto.getId());
    }

    public void deleteBook(Long id) {
        findBook(id);
        bookRepository.deleteById(id);
        log.info("Book {}, deleted successfully.", id);
    }
}
