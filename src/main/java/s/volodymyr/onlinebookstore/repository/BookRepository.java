package s.volodymyr.onlinebookstore.repository;

import java.util.List;
import java.util.Optional;
import s.volodymyr.onlinebookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> getBookByID(Long id);
}
