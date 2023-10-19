package s.volodymyr.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import s.volodymyr.onlinebookstore.dto.BookDto;
import s.volodymyr.onlinebookstore.dto.CreateBookRequestDto;
import s.volodymyr.onlinebookstore.model.Book;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toBook(CreateBookRequestDto requestDto);

    void updateBook(CreateBookRequestDto dto, @MappingTarget Book book);
}
