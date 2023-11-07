package s.volodymyr.onlinebookstore.service;

import s.volodymyr.onlinebookstore.dto.cartitem.CreateCartItemRequestDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.ChangeShoppingCartQuantityDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto findById(Long id);

    ShoppingCartDto addToCart(Long id, CreateCartItemRequestDto requestDto);

    ShoppingCartDto update(Long userId, Long cartItemId, ChangeShoppingCartQuantityDto quantityDto);

    ShoppingCartDto removeCartItem(Long userId, Long cartItemId);
}
