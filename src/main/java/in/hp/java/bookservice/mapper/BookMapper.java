package in.hp.java.bookservice.mapper;

import in.hp.java.bookservice.dto.BookDto;
import in.hp.java.bookservice.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
