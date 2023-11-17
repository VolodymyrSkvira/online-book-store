package s.volodymyr.onlinebookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static s.volodymyr.onlinebookstore.util.UtilBookDataSupplier.getDefaultCreateBookRequestDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import s.volodymyr.onlinebookstore.dto.book.BookDto;
import s.volodymyr.onlinebookstore.dto.book.CreateBookRequestDto;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.mapper.BookMapper;
import s.volodymyr.onlinebookstore.mapper.impl.BookMapperImpl;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.model.Category;
import s.volodymyr.onlinebookstore.repository.book.BookRepository;
import s.volodymyr.onlinebookstore.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Spy
    private BookMapper bookMapper = new BookMapperImpl();

    @Test
    @DisplayName("""
            """)
    public void save_WithValidData_ShouldReturnBookDto() {
        Long id = 1L;
        CreateBookRequestDto requestDto = getDefaultCreateBookRequestDto();

        Book book = new Book();
        book.setTitle(requestDto.title());
        book.setAuthor(requestDto.author());
        book.setIsbn(requestDto.isbn());
        book.setPrice(requestDto.price());
        book.setDescription(requestDto.description());
        book.setCoverImage(requestDto.coverImage());
        book.setCategories(Set.of(new Category(id)));

        BookDto bookDto = new BookDto(id,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getDescription(),
                book.getCoverImage(),
                List.of(id));

        when(bookMapper.toBook(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto actual = bookService.save(requestDto);

        assertEquals(actual, bookDto);
    }

    @Test
    @DisplayName("""
            """)
    public void getBookById_WithValidData_ShouldReturnBookDto() {
        Long id = 1L;

        Book book = new Book();
        book.setTitle("Turkey Trouble");
        book.setAuthor("Wendi Silvano");
        book.setIsbn("9780761455295");
        book.setPrice(BigDecimal.valueOf(19));
        book.setDescription("Awesome book");
        book.setCoverImage("image_1");
        book.setCategories(Set.of(new Category(id)));

        BookDto bookDto = new BookDto(id,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getDescription(),
                book.getCoverImage(),
                List.of(id));

        when(bookRepository.getBookById(id)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto actual = bookService.getBookById(id);

        assertEquals(actual, bookDto);
    }

    @Test
    @DisplayName("""
            """)
    public void getBookById_WithInvalidData_ShouldThrowException() {
        Long id = 1L;

        when(bookRepository.getBookById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.getBookById(id)
        );

        String expected = "Cannot find book by id " + id;
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            """)
    public void update_WithValidData_ShouldReturnBookDto() {
        Long id = 1L;

        Book book = new Book();
        book.setTitle("Test title");
        book.setAuthor("Test author");
        book.setIsbn("9780761455295");
        book.setPrice(BigDecimal.valueOf(29));
        book.setDescription("Test description");
        book.setCoverImage("Test image");
        book.setCategories(Set.of(new Category(id)));

        CreateBookRequestDto requestDto = getDefaultCreateBookRequestDto();

        when(bookRepository.getBookById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        BookDto updated = bookService.update(requestDto, id);

        assertThat(updated).hasFieldOrPropertyWithValue("title", requestDto.title())
                .hasFieldOrPropertyWithValue("author", requestDto.author());
    }

    @Test
    @DisplayName("""
            """)
    public void update_WithInvalidData_ShouldThrowException() {
        Long id = 1L;

        CreateBookRequestDto requestDto = getDefaultCreateBookRequestDto();

        when(bookRepository.getBookById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.update(requestDto, id)
        );

        String expected = "Cannot find book by id " + id;
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            """)
    public void deleteById_WithValidData_ShouldDoNothing() {
        Long id = 1L;

        bookService.deleteById(id);

        verify(bookRepository, times(1)).deleteById(id);
    }
}
