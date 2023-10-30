package s.volodymyr.onlinebookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(

        @Email
        @NotBlank
        @Size(min = 4, max = 30)
        String email,
        @NotBlank
        @Size(min = 6, max = 50)
        String password
) {
}
