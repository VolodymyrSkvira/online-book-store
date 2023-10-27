package s.volodymyr.onlinebookstore.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(

        @NotBlank
        @Size(min = 4, max = 30)
        String email,
        @NotBlank
        @Size(min = 6, max = 50)
        String password,
        @NotBlank
        @Size(min = 6, max = 50)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress
) {
}
