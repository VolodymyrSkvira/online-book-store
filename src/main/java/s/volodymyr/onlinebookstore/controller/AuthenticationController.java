package s.volodymyr.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s.volodymyr.onlinebookstore.dto.user.UserLoginRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserLoginResponseDto;
import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;
import s.volodymyr.onlinebookstore.dto.user.UserResponseDto;
import s.volodymyr.onlinebookstore.exception.RegistrationException;
import s.volodymyr.onlinebookstore.security.AuthenticationService;
import s.volodymyr.onlinebookstore.service.UserService;

@Tag(name = "User management", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(
            summary = "Register new user",
            description = "Registration of the new user"
    )
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Login of the existing user"
    )
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
