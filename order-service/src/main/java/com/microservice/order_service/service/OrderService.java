package com.microservice.order_service.service;

import com.microservice.order_service.dto.OrderDTO;
import com.microservice.order_service.dto.OrderItemDTO;
import com.microservice.order_service.model.Order;
import com.microservice.order_service.model.OrderItem;
import com.microservice.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<OrderDTO> GetALlOrders() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO placeOrder(Long userId, List<OrderItemDTO> items) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus("Processing");

        List<OrderItem> orderItems = items.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setDeliveryStatus("Pending");
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        repository.save(order);

        return convertToDTO(order);
    }


    public OrderDTO getOrderById(Long orderId) {
        return repository.findById(orderId).map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void updateDeliveryStatus(Long orderId, Long productId, String newStatus) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.getOrderItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setDeliveryStatus(newStatus);
            }
        });

        repository.save(order);
    }


    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getOrderStatus(),
                order.getOrderItems().stream()
                        .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity(), item.getDeliveryStatus()))
                        .collect(Collectors.toList())
        );
    }

}
