package s.volodymyr.onlinebookstore.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import s.volodymyr.onlinebookstore.dto.BookDto;
import s.volodymyr.onlinebookstore.dto.BookSearchParameters;
import s.volodymyr.onlinebookstore.dto.CreateBookRequestDto;
import s.volodymyr.onlinebookstore.service.BookService;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable @Positive Long id) {
        return bookService.getBookById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        bookService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/search")
    public List<BookDto> search(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public BookDto update(
            @RequestBody @Valid CreateBookRequestDto bookRequestDto,
            @PathVariable @Positive Long id) {
        return bookService.update(bookRequestDto, id);
    }
}
