package s.volodymyr.onlinebookstore.repository.book;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import s.volodymyr.onlinebookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Optional<Book> getBookById(Long id);

    List<Book> findAllByCategoriesId(Long categoryId, Pageable pageable);
}

