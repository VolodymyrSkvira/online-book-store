package s.volodymyr.onlinebookstore.repository.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import s.volodymyr.onlinebookstore.exception.DataProcessingException;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.repository.BookRepository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final SessionFactory sessionFactory;

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
            return session.createQuery("from Book", Book.class).getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Cannot get all books", e);
        }
    }

    @Override
    public Optional<Book> getBookByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Book.class, id));
        } catch (Exception e) {
            throw new EntityNotFoundException("Cannot find book by id " + id, e);
        }
    }
}
