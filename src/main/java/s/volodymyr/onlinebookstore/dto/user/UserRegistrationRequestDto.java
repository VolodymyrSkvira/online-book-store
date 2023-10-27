package s.volodymyr.onlinebookstore.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import s.volodymyr.onlinebookstore.validation.FieldMatch;

public record UserRegistrationRequestDto(
        @NotBlank
        @Size(min = 4, max = 30)
        String email,
        @NotBlank
        @Size(min = 6, max = 50)
        @FieldMatch
        String password,
        @NotBlank
        @Size(min = 6, max = 50)
        @FieldMatch
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress
) {
}
