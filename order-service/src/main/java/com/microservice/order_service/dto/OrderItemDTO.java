package com.microservice.order_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long productId;
    private int quantity;
    private String deliveryStatus;
}
