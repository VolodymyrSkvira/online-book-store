package s.volodymyr.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import s.volodymyr.onlinebookstore.dto.book.BookDto;
import s.volodymyr.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import s.volodymyr.onlinebookstore.dto.book.BookSearchParameters;
import s.volodymyr.onlinebookstore.dto.book.CreateBookRequestDto;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.mapper.BookMapper;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.repository.book.BookRepository;
import s.volodymyr.onlinebookstore.repository.book.BookSpecificationBuilder;
import s.volodymyr.onlinebookstore.repository.category.CategoryRepository;
import s.volodymyr.onlinebookstore.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toBook(bookRequestDto)));
    }

    @Override
    public BookDto update(CreateBookRequestDto bookRequestDto, Long id) {
        Book book = bookRepository.getBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book by id " + id)
        );
        bookMapper.updateBook(bookRequestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.getBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book by id " + id)
        ));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameters params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " doesn't exist");
        }
        return bookRepository.findAllByCategoriesId(id, pageable)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
