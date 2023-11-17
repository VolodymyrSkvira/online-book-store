package s.volodymyr.onlinebookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import s.volodymyr.onlinebookstore.model.Status;

public record UpdateOrderStatusDto(
        @NotNull
        Status status
) {
}
