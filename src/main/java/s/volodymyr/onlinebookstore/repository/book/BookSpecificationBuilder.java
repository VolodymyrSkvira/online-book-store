package s.volodymyr.onlinebookstore.repository.book;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import s.volodymyr.onlinebookstore.dto.BookSearchParameters;
import s.volodymyr.onlinebookstore.model.Book;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder {

    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            spec = spec.and(getTitleSpecification(searchParameters.titles()));
        }
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(getAuthorSpecification(searchParameters.authors()));
        }
        return spec;
    }

    private Specification<Book> getTitleSpecification(String[] titles) {
        return (root, query, criteriaBuilder) ->
                root.get("title").in(Arrays.stream(titles).toArray());
    }

    private Specification<Book> getAuthorSpecification(String[] authors) {
        return (root, query, criteriaBuilder) ->
                root.get("author").in(Arrays.stream(authors).toArray());
    }
}
