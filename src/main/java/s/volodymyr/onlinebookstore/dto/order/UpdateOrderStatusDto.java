package s.volodymyr.onlinebookstore.dto.order;

import s.volodymyr.onlinebookstore.model.Status;

public record UpdateOrderStatusDto(
        Status status
) {
}
