package s.volodymyr.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s.volodymyr.onlinebookstore.dto.order.CreateOrderRequestDto;
import s.volodymyr.onlinebookstore.dto.order.OrderDto;
import s.volodymyr.onlinebookstore.dto.order.UpdateOrderStatusDto;
import s.volodymyr.onlinebookstore.dto.orderitem.OrderItemDto;
import s.volodymyr.onlinebookstore.model.User;
import s.volodymyr.onlinebookstore.service.OrderService;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get orders",
            description = "Receive user's order history")
    public List<OrderDto> getOrders(Authentication authentication,
                                    Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllById(pageable, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Place order",
            description = "Allows user to place an order")
    public OrderDto placeOrder(Authentication authentication,
                               @RequestBody CreateOrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.createOrder(user.getId(), requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update order",
            description = "Update the status of an existing order")
    public OrderDto updateOrder(@RequestBody UpdateOrderStatusDto updateDto,
                                @PathVariable Long id) {
        return orderService.update(id, updateDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/items")
    @Operation(summary = "Get order items by order id",
            description = "Receive order items by order id")
    public List<OrderItemDto> getItemsByOrderId(Pageable pageable, @PathVariable Long id) {
        return orderService.getItemsByOrderId(pageable, id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item by order id and item id",
            description = "Receive order item by order id and item id")
    public OrderItemDto getItemByOrderAndItemId(@PathVariable Long orderId,
                                                @PathVariable Long itemId) {
        return orderService.getItemByOrderIdAndItemId(orderId, itemId);
    }
}