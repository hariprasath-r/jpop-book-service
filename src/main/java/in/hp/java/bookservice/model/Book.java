package in.hp.java.bookservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String bookName;

    private String description;

    private String genre;

    private String author;
}
