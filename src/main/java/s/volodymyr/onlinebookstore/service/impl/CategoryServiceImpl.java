package s.volodymyr.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import s.volodymyr.onlinebookstore.dto.category.CategoryDto;
import s.volodymyr.onlinebookstore.dto.category.CreateCategoryRequestDto;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.mapper.CategoryMapper;
import s.volodymyr.onlinebookstore.model.Category;
import s.volodymyr.onlinebookstore.repository.category.CategoryRepository;
import s.volodymyr.onlinebookstore.service.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.getCategoryById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find category by id " + id)
        ));
    }

    @Override
    public CreateCategoryRequestDto save(CategoryDto categoryRequest) {
        return categoryMapper
                .toRequestDto(categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
    }

    @Override
    public CreateCategoryRequestDto update(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.getCategoryById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find category by id " + id)
        );
        categoryMapper.updateCategory(categoryDto, category);
        return categoryMapper.toRequestDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
