package s.volodymyr.onlinebookstore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import s.volodymyr.onlinebookstore.model.Book;

@Service
public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
