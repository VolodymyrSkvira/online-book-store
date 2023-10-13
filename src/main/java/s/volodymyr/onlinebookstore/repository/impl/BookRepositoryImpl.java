package s.volodymyr.onlinebookstore.repository.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import s.volodymyr.onlinebookstore.exception.DataProcessingException;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        try {
            sessionFactory.inTransaction(session -> session.persist(book));
            return book;
        } catch (Exception e) {
            throw new DataProcessingException("Cannot save new book", e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> getAllBooks = session.createQuery(
                    "from Book", Book.class);
            return getAllBooks.getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Cannot get all books", e);
        }
    }
}
