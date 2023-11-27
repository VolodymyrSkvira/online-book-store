package s.volodymyr.onlinebookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import s.volodymyr.onlinebookstore.dto.category.CategoryDto;
import s.volodymyr.onlinebookstore.dto.category.CreateCategoryRequestDto;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.mapper.CategoryMapper;
import s.volodymyr.onlinebookstore.mapper.impl.CategoryMapperImpl;
import s.volodymyr.onlinebookstore.model.Category;
import s.volodymyr.onlinebookstore.repository.category.CategoryRepository;
import s.volodymyr.onlinebookstore.service.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private CategoryMapper categoryMapper = new CategoryMapperImpl();

    @Test
    @DisplayName("Receive a category by a valid id")
    public void getCategoryById_WithValidData_ShouldReturnCategoryDto() {
        Long id = 1L;

        Category category = new Category();
        category.setId(id);
        category.setName("Fiction");
        category.setDescription("Awesome category");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        CategoryDto actual = categoryService.getCategoryById(id);

        assertThat(actual).hasFieldOrPropertyWithValue("name", category.getName())
                .hasFieldOrPropertyWithValue("description", category.getDescription());
    }

    @Test
    @DisplayName("Receive a category by a non-valid id")
    public void getCategoryById_WithInvalidData_ShouldThrowException() {
        Long id = 1L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> categoryService.getCategoryById(id)
        );

        String expected = "Cannot find category by id " + id;
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create category with valid data")
    public void save_WithValidData_ShouldReturnCategoryDto() {
        Long id = 1L;

        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto("Fiction",
                                                                           "Awesome category");

        Category category = new Category();
        category.setName(requestDto.name());
        category.setDescription(requestDto.description());

        when(categoryMapper.toCategory(requestDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDto actual = categoryService.save(requestDto);

        assertThat(actual).hasFieldOrPropertyWithValue("name", requestDto.name())
                .hasFieldOrPropertyWithValue("description", requestDto.description());
    }

    @Test
    @DisplayName("Update category by a valid id")
    public void update_WithValidData_ShouldReturnCategoryDto() {
        Long id = 1L;

        Category category = new Category();
        category.setName("Test name");
        category.setDescription("Test description");

        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto("Fiction",
                "Awesome category");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDto updated = categoryService.update(id, requestDto);

        assertThat(updated).hasFieldOrPropertyWithValue("name", requestDto.name())
                .hasFieldOrPropertyWithValue("description", requestDto.description());
    }

    @Test
    @DisplayName("Update category by a non-valid id")
    public void update_WithInvalidData_ShouldThrowException() {
        Long id = 1L;

        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto("Fiction",
                "Awesome category");

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> categoryService.update(id, requestDto)
        );

        String expected = "Cannot find category by id " + id;
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Delete category by a valid id")
    public void deleteById_WithValidData_ShouldDoNothing() {
        Long id = 1L;

        categoryService.deleteById(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }
}
