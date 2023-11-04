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
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find category by id " + id)
                );
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryRequest) {
        return categoryMapper
                .toDto(categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find category by id " + id)
        );
        categoryMapper.updateCategory(requestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
