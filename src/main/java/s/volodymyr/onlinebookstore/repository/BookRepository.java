package s.volodymyr.onlinebookstore.repository;

import java.util.List;
import s.volodymyr.onlinebookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
