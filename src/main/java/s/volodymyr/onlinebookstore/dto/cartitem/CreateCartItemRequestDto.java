package s.volodymyr.onlinebookstore.dto.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        @Positive
        Long bookId,

        @Min(1)
        int quantity) {
}
