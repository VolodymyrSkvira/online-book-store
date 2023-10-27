package s.volodymyr.onlinebookstore.dto.user;

import java.util.Set;
import s.volodymyr.onlinebookstore.model.Role;

public record UserDto(
        Long id,
        String email,
        String password,
        String firstName,
        String lastName,
        String shippingAddress,
        Set<Role> roles
) {
}
