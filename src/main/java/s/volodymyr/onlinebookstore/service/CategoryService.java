package s.volodymyr.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import s.volodymyr.onlinebookstore.dto.category.CategoryDto;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getCategoryById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);
}
