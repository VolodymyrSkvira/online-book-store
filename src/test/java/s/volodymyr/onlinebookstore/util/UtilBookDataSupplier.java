package s.volodymyr.onlinebookstore.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import s.volodymyr.onlinebookstore.dto.book.BookDto;
import s.volodymyr.onlinebookstore.dto.book.CreateBookRequestDto;

public class UtilBookDataSupplier {

    public static CreateBookRequestDto getDefaultCreateBookRequestDto() {
        Long id = 1L;

        return new CreateBookRequestDto("Turkey Trouble",
                "Wendi Silvano",
                "9780761455296",
                BigDecimal.valueOf(19),
                "Awesome book",
                "image_1",
                List.of(id));
    }

    public static List<BookDto> createTestList() {
        Long id = 1L;

        return List.of(new BookDto(1L,
                "First book",
                "First author",
                "9780761455295",
                BigDecimal.valueOf(19),
                "Awesome book",
                "image_1",
                Collections.emptyList()),
                new BookDto(2L,
                        "Second book",
                        "Second author",
                        "9680761455295",
                        BigDecimal.valueOf(19),
                        "Awesome book",
                        "image_2",
                        Collections.emptyList()),
                new BookDto(3L,
                        "Third book",
                        "Third author",
                        "9580761455295",
                        BigDecimal.valueOf(19),
                        "Awesome book",
                        "image_3",
                        Collections.emptyList()));
    }
}
