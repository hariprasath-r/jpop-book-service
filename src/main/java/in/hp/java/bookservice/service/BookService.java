package in.hp.java.bookservice.service;

import in.hp.java.bookservice.model.Book;
import in.hp.java.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
