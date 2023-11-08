package s.volodymyr.onlinebookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import s.volodymyr.onlinebookstore.model.CartItem;
import s.volodymyr.onlinebookstore.model.Order;
import s.volodymyr.onlinebookstore.model.OrderItem;
import s.volodymyr.onlinebookstore.model.ShoppingCart;
import s.volodymyr.onlinebookstore.model.Status;
import s.volodymyr.onlinebookstore.model.User;
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

    @Transactional
    @Override
    public List<OrderDto> getAllById(Pageable pageable, Long id) {
        return orderRepository.findAllByUserId(pageable, id).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderDto createOrder(Long id, CreateOrderRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user")
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart is empty")
        );
        Order order = createOrderObject(shoppingCart.getCartItems());
        order.setUser(user);
        order.setShippingAddress(requestDto.shippingAddress());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.NEW);
        order.setTotal(getTotal(order.getOrderItems()));
        orderRepository.save(order);
        cartItemRepository.deleteByShoppingCartId(shoppingCart.getId());
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
        orderRepository.save(order);
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

    private Order createOrderObject(Set<CartItem> cartItems) {
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem = new OrderItem();
        for (CartItem item : cartItems) {
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getBook().getPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity())));
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return order;
    }

    private BigDecimal getTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
