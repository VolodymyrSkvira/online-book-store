package s.volodymyr.onlinebookstore.service;

import s.volodymyr.onlinebookstore.dto.cartitem.CreateCartItemRequestDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.UpdateCartItemRequestDto;

public interface ShoppingCartService {
    ShoppingCartDto findById(Long id);

    ShoppingCartDto addToCart(Long id, CreateCartItemRequestDto requestDto);

    ShoppingCartDto update(Long userId, Long cartItemId, UpdateCartItemRequestDto quantityDto);

    ShoppingCartDto removeCartItem(Long userId, Long cartItemId);
}
