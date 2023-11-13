package s.volodymyr.onlinebookstore.dto.shoppingcart;

import java.util.Set;
import s.volodymyr.onlinebookstore.dto.cartitem.CartItemDto;

public record ShoppingCartDto(
        Long id,
        Long userId,
        Set<CartItemDto> cartItems) {
}
