package s.volodymyr.onlinebookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequestDto(
        @NotBlank
        @Size(max = 30)
        String name,
        @Size(max = 255)
        String description) {
}
