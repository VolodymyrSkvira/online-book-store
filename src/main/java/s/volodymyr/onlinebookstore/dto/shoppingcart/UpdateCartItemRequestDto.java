package s.volodymyr.onlinebookstore.dto.shoppingcart;

import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequestDto(
        @Positive
        int quantity) {
}
