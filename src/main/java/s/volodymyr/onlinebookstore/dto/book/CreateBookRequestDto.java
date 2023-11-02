package s.volodymyr.onlinebookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

public record CreateBookRequestDto(
        @NotBlank
        String title,

        @NotBlank
        String author,

        @NotBlank @Pattern(
                regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|"
                        + "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)"
                        + "97[89][- ]?[0-9]{1,5}[- ]?[0-9]+"
                        + "[- ]?[0-9]+[- ]?[0-9]$")
        String isbn,

        @PositiveOrZero
        BigDecimal price,
        String description,
        String coverImage,
        List<Long> categoryIds
) {
}
