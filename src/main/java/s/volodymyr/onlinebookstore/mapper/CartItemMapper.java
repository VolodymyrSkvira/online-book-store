package s.volodymyr.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import s.volodymyr.onlinebookstore.dto.cartitem.CartItemDto;
import s.volodymyr.onlinebookstore.dto.cartitem.CreateCartItemRequestDto;
import s.volodymyr.onlinebookstore.model.CartItem;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = BookMapper.class
)
public interface CartItemMapper {
    CartItem toCartItem(CreateCartItemRequestDto requestDto);

    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "book.id", target = "bookId")
    CartItemDto toDto(CartItem cartItem);
}
