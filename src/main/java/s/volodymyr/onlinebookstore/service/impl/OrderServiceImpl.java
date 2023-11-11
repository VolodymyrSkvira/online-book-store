package s.volodymyr.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s.volodymyr.onlinebookstore.dto.order.CreateOrderRequestDto;
import s.volodymyr.onlinebookstore.dto.order.OrderDto;
import s.volodymyr.onlinebookstore.dto.order.UpdateOrderStatusDto;
import s.volodymyr.onlinebookstore.dto.orderitem.OrderItemDto;
import s.volodymyr.onlinebookstore.exception.EntityNotFoundException;
import s.volodymyr.onlinebookstore.mapper.OrderItemMapper;
import s.volodymyr.onlinebookstore.mapper.OrderMapper;
import s.volodymyr.onlinebookstore.model.Order;
import s.volodymyr.onlinebookstore.model.OrderItem;
import s.volodymyr.onlinebookstore.model.ShoppingCart;
import s.volodymyr.onlinebookstore.repository.cartitem.CartItemRepository;
import s.volodymyr.onlinebookstore.repository.order.OrderRepository;
import s.volodymyr.onlinebookstore.repository.orderitem.OrderItemRepository;
import s.volodymyr.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import s.volodymyr.onlinebookstore.repository.user.UserRepository;
import s.volodymyr.onlinebookstore.service.OrderService;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderDto> getAllById(Pageable pageable, Long id) {
        return orderRepository.findAllByUserId(pageable, id).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderDto createOrder(Long id, CreateOrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart is empty")
        );
        Order order = orderMapper.toOrder(shoppingCart, requestDto);
        orderRepository.save(order);
        shoppingCart.clear();
        return orderMapper.toDto(order);
    }

    @Transactional
    @Override
    public OrderDto update(Long orderId,
                           UpdateOrderStatusDto updateDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Cannot find order by id " + orderId)
        );
        order.setStatus(updateDto.status());
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemDto> getItemsByOrderId(Pageable pageable, Long orderId) {
        return orderItemRepository.findAllByOrderId(pageable, orderId).stream()
                .map(orderItemMapper::toDto)
                .toList();

    }

    @Override
    public OrderItemDto getItemByOrderIdAndItemId(Long orderId,
                                                  Long itemId) {
        OrderItem orderItem = orderItemRepository.findByIdAndOrderId(itemId, orderId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cannot find item by id "
                                + itemId
                                + " in order by id: "
                                + orderId)
                );
        return orderItemMapper.toDto(orderItem);
    }
}
