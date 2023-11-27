package s.volodymyr.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import s.volodymyr.onlinebookstore.dto.book.BookDto;
import s.volodymyr.onlinebookstore.dto.book.BookSearchParameters;
import s.volodymyr.onlinebookstore.dto.book.CreateBookRequestDto;
import s.volodymyr.onlinebookstore.service.BookService;

@Tag(name = "Book management", description = "Endpoints for managing books")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @Operation(summary = "Get all books",
               description = "Receive a list of available books")
    public List<BookDto> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID",
               description = "Receive a book by ID")
    public BookDto getBookById(@PathVariable @Positive Long id) {
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book",
               description = "Delete a book by ID")
    public void delete(@PathVariable @Positive Long id) {
        bookService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create book",
               description = "Create a new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/search")
    @Operation(summary = "Search for a book",
               description = "Receive all books by given parameters")
    public List<BookDto> search(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update book",
               description = "Update a book by ID")
    public BookDto update(
            @RequestBody @Valid CreateBookRequestDto bookRequestDto,
            @PathVariable @Positive Long id) {
        return bookService.update(bookRequestDto, id);
    }
}
