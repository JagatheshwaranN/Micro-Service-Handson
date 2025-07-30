package com.learn_everday.order_service.controller;

import com.learn_everday.order_service.config.WebClientConfig;
import com.learn_everday.order_service.dto.OrderResponseDTO;
import com.learn_everday.order_service.dto.ProductDTO;
import com.learn_everday.order_service.entity.Order;
import com.learn_everday.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order) {
        return webClientBuilder.build()
                .get().uri("http://localhost:8081/products/" + order.getProductId())
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .map(productDTO -> {
                    OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                    orderResponseDTO.setOrderId(order.getId());
                    orderResponseDTO.setProductId(order.getProductId());
                    orderResponseDTO.setQuantity(order.getQuantity());
                    orderResponseDTO.setProductName(productDTO.getName());
                    orderResponseDTO.setProductPrice(productDTO.getPrice());
                    orderResponseDTO.setTotalPrice(order.getQuantity() * productDTO.getPrice());
                    orderRepository.save(order);
                    return ResponseEntity.ok(orderResponseDTO);
                });
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
