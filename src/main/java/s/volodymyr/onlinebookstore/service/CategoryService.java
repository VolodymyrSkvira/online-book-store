package s.volodymyr.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import s.volodymyr.onlinebookstore.dto.category.CategoryDto;
import s.volodymyr.onlinebookstore.dto.category.CreateCategoryRequestDto;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getCategoryById(Long id);

    CreateCategoryRequestDto save(CategoryDto categoryDto);

    CreateCategoryRequestDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);
}
