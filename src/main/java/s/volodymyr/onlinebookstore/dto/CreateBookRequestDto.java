package s.volodymyr.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public record CreateBookRequestDto(
        @NotNull
        String title,

        @NotNull
        String author,

        @NotNull @Pattern(
                regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|"
                        + "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)"
                        + "97[89][- ]?[0-9]{1,5}[- ]?[0-9]+"
                        + "[- ]?[0-9]+[- ]?[0-9]$")
        String isbn,

        @Min(0)
        BigDecimal price,
        String description,
        String coverImage
) {
}
