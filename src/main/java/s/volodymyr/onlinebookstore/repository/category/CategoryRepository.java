package s.volodymyr.onlinebookstore.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.volodymyr.onlinebookstore.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
