package com.dripclothe.controller;

import java.util.List;

import com.dripclothe.model.OrderItem;
import com.dripclothe.model.OrderTracker;
import com.dripclothe.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dripclothe.model.Order;
import com.dripclothe.service.OrdersService;

@RestController
@RequestMapping("/backend/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PutMapping("/test")
    public ResponseEntity<Order> addUpdateOrderTracker(@RequestBody Order order) {
        Order order1 = ordersService.updateOrderTracker(order.getOrderTrackers(), order);
        return new ResponseEntity<>(order1, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = ordersService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id) {
        ordersService.deleteOrder(id);
        return new ResponseEntity<String>("Order is deleted successfully.!", HttpStatus.OK);
    }

    @GetMapping("/item/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable("orderId") Integer orderId) {
        List<OrderItem> orderItems = ordersService.findAllOrderItemsPerOrder(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/{findOrderById}")
    public ResponseEntity<Order> getOrder(@PathVariable("findOrderById") String findOrderById) {
        Order order = ordersService.findOrderTrackingNumber(findOrderById);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
