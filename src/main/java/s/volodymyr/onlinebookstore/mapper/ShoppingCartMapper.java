package s.volodymyr.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import s.volodymyr.onlinebookstore.dto.cartitem.CreateCartItemRequestDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.ChangeShoppingCartQuantityDto;
import s.volodymyr.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import s.volodymyr.onlinebookstore.model.ShoppingCart;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = CartItemMapper.class
)
public interface ShoppingCartMapper {
    @Mapping(source = "cartItems", target = "cartItems")
    @Mapping(source = "user.id", target = "userId")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    ShoppingCartDto toSaveResponseDto(CreateCartItemRequestDto requestDto);

    ShoppingCartDto toUpdateResponseDto(ChangeShoppingCartQuantityDto requestDto);
}
