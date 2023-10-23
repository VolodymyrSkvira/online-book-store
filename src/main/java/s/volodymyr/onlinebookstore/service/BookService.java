package s.volodymyr.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import s.volodymyr.onlinebookstore.dto.BookDto;
import s.volodymyr.onlinebookstore.dto.BookSearchParameters;
import s.volodymyr.onlinebookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    BookDto update(CreateBookRequestDto bookRequestDto, Long id);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
