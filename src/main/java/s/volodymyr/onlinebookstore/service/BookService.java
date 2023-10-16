package s.volodymyr.onlinebookstore.service;

import java.util.List;
import s.volodymyr.onlinebookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
