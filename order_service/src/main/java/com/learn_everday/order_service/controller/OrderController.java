package com.learn_everday.order_service.controller;

import com.learn_everday.order_service.config.WebClientConfig;
import com.learn_everday.order_service.dto.OrderResponseDTO;
import com.learn_everday.order_service.dto.ProductDTO;
import com.learn_everday.order_service.entity.Order;
import com.learn_everday.order_service.repository.OrderRepository;
import com.learn_everday.order_service.service.OrderService;
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
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}
