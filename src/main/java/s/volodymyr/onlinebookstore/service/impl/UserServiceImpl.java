package s.volodymyr.onlinebookstore.service.impl;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserResponseDto;
import s.volodymyr.onlinebookstore.exception.RegistrationException;
import s.volodymyr.onlinebookstore.mapper.UserMapper;
import s.volodymyr.onlinebookstore.model.Role;
import s.volodymyr.onlinebookstore.model.RoleName;
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
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER));
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        return userMapper.toResponseDto(userRepository.save(user));
    }

}
