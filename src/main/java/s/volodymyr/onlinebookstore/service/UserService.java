package s.volodymyr.onlinebookstore.service;

import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserResponseDto;
import s.volodymyr.onlinebookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
