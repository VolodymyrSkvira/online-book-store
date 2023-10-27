package s.volodymyr.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import s.volodymyr.onlinebookstore.dto.user.UserRegistrationRequestDto;

@RequiredArgsConstructor
public class FieldMatcherValidator implements ConstraintValidator<
        FieldMatch,
        UserRegistrationRequestDto> {

    private final UserRegistrationRequestDto registrationRequestDto;

    @Override
    public boolean isValid(
            UserRegistrationRequestDto registrationRequestDto,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        String password = registrationRequestDto.password();
        String repeatPassword = registrationRequestDto.repeatPassword();
        return password != null && password.equals(repeatPassword);
    }
}
