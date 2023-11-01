package s.volodymyr.onlinebookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import s.volodymyr.onlinebookstore.validation.FieldMatch;

@FieldMatch(message = "Password don`t match",
            field = "password",
            fieldMatch = "repeatPassword")
public record UserRegistrationRequestDto(
        @Email
        @NotBlank
        @Size(min = 4, max = 30)
        String email,
        @NotBlank
        @Size(min = 6, max = 60)
        String password,
        @NotBlank
        @Size(min = 6, max = 60)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress
) {
}
