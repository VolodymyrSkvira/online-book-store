package s.volodymyr.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserResponseDto;
import s.volodymyr.onlinebookstore.model.User;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = CartItemMapper.class
)
public interface UserMapper {
    User toUser(UserRegistrationRequestDto requestDto);

    UserResponseDto toUserResponseDto(User user);
}
