package com.learn_everday.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;

    private String productName;
    private Double productPrice;
}
