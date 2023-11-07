package s.volodymyr.onlinebookstore.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserResponseDto;
import s.volodymyr.onlinebookstore.exception.RegistrationException;
import s.volodymyr.onlinebookstore.mapper.UserMapper;
import s.volodymyr.onlinebookstore.model.RoleName;
import s.volodymyr.onlinebookstore.model.ShoppingCart;
import s.volodymyr.onlinebookstore.model.User;
import s.volodymyr.onlinebookstore.repository.role.RoleRepository;
import s.volodymyr.onlinebookstore.repository.user.UserRepository;
import s.volodymyr.onlinebookstore.service.UserService;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(
            UserRegistrationRequestDto request) throws RegistrationException {
        if (userRepository.existsByEmail(request.email())) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = userMapper.toUser(request);
        setShoppingCartForUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName(RoleName.ROLE_USER)));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    private void setShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
    }
}
