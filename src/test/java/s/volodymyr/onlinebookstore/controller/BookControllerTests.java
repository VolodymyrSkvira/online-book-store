package s.volodymyr.onlinebookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static s.volodymyr.onlinebookstore.util.UtilBookDataSupplier.createTestList;
import static s.volodymyr.onlinebookstore.util.UtilBookDataSupplier.getDefaultCreateBookRequestDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import s.volodymyr.onlinebookstore.dto.book.BookDto;
import s.volodymyr.onlinebookstore.dto.book.CreateBookRequestDto;

@Sql(scripts = "classpath:database/books/add-three-default-books-with-category.sql")
@Sql(scripts = "classpath:database/books/delete-all-from-books-and-books-categories.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "classpath:database/categories/delete-all-from-categories.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTests {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Create book with valid data")
    void createBook_ValidRequestDto_ShouldReturnBookDto() throws Exception {
        Long id = 4L;

        CreateBookRequestDto requestDto = getDefaultCreateBookRequestDto();

        BookDto expected = new BookDto(id,
                requestDto.title(),
                requestDto.author(),
                requestDto.isbn(),
                requestDto.price(),
                requestDto.description(),
                requestDto.coverImage(),
                requestDto.categoryIds());

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        MvcResult result = mockMvc.perform(
                    post("/books")
                .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn();

        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                                                BookDto.class);
        assertEquals(expected, actual);
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Receive all books from catalogue")
    void getAll_WithBooksInCatalog_ShouldReturnAllBooks() throws Exception {
        List<BookDto> expected = createTestList();

        MvcResult result = mockMvc.perform(
                        get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        BookDto[] actual = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                                                  BookDto[].class);
        assertEquals(3, actual.length);
        assertEquals(expected, Arrays.stream(actual).toList());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Receive a book by a valid id")
    void getBookById_WithBooksInCatalog_ShouldReturnBookDto() throws Exception {
        BookDto expected = new BookDto(1L,
                "First book",
                "First author",
                "9780761455295",
                BigDecimal.valueOf(19),
                "Awesome book",
                "image_1",
                Collections.emptyList());

        MvcResult result = mockMvc.perform(
                        get("/books/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                BookDto.class);
        assertEquals(expected, actual);
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Delete a book by a valid id")
    void delete_WithValidData_ShouldDoNothing() throws Exception {
        MvcResult result = mockMvc.perform(
                        delete("/books/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Update a book by a valid id")
    void update_withValidData_ShouldReturnBookDto() throws Exception {
        Long id = 1L;

        CreateBookRequestDto requestDto = getDefaultCreateBookRequestDto();

        BookDto expected = new BookDto(1L,
                "Turkey Trouble",
                "Wendi Silvano",
                "9780761455296",
                BigDecimal.valueOf(19),
                "Awesome book",
                "image_1",
                List.of(id));

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        MvcResult result = mockMvc.perform(
                        put("/books/1")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                BookDto.class);

        assertEquals(expected, actual);
    }
}
