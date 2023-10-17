package s.volodymyr.onlinebookstore.repository;

import org.springframework.data.jpa.domain.Specification;
import s.volodymyr.onlinebookstore.dto.BookSearchParameters;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameters searchParameters);
}
