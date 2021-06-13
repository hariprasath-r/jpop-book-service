package in.hp.java.bookservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

    private Long id;

    private String bookName;

    private String description;

    private String genre;

    private String author;
}
