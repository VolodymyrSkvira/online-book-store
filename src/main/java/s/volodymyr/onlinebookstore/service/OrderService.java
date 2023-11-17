package s.volodymyr.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import s.volodymyr.onlinebookstore.dto.order.CreateOrderRequestDto;
import s.volodymyr.onlinebookstore.dto.order.OrderDto;
import s.volodymyr.onlinebookstore.dto.order.UpdateOrderStatusDto;
import s.volodymyr.onlinebookstore.dto.orderitem.OrderItemDto;

public interface OrderService {
    List<OrderDto> getAllById(Pageable pageable, Long id);

    OrderDto createOrder(Long id, CreateOrderRequestDto requestDto);

    OrderDto update(Long orderId, UpdateOrderStatusDto updateDto);

    List<OrderItemDto> getItemsByOrderId(Pageable pageable, Long orderId);

    OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId);
}
