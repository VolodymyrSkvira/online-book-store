package s.volodymyr.onlinebookstore.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.repository.BookRepository;
import s.volodymyr.onlinebookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
