package com.dripclothe.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.dripclothe.model.OrderItem;
import com.dripclothe.model.OrderTracker;
import com.dripclothe.repository.OrderItemRepo;
import com.dripclothe.repository.OrderTrackerRepo;
import org.springframework.stereotype.Service;

import com.dripclothe.model.Order;
import com.dripclothe.repository.OrderRepo;

@Service
public class OrdersService {
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final OrderTrackerRepo orderTrackerRepo;

    public OrdersService(OrderRepo orderRepo, OrderItemRepo orderItemRepo, OrderTrackerRepo orderTrackerRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.orderTrackerRepo = orderTrackerRepo;
    }

    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }

    public List<OrderItem> findAllOrderItemsPerOrder(Integer orderId){
        return orderItemRepo.findOrderItemByOrderId(orderId);
    }

    public void deleteOrder(Integer id){
        Order order = orderRepo.findById(id).orElseThrow();
        orderRepo.delete(order);
    }

    public Order updateOrderStatus(Order order){
        Order test= orderRepo.findById(order.getId()).orElseThrow();
        if (!Objects.equals(test.getStatus(), order.getStatus()))
        {
            test.setStatus(order.getStatus());
            orderRepo.save(test);
        }
        return test;
    }

    public Order updateOrderTracker(Set<OrderTracker> orderTrackerList, Order order){
        for(OrderTracker orderTracker : orderTrackerList){
            orderTracker.setOrder(order);
            orderTrackerRepo.save(orderTracker);
        }
        this.updateOrderStatus(order);
        return order;
    }

    public Order findOrderTrackingNumber(String orderTrackerId){
        return orderRepo.findByorderTrackingNumber(orderTrackerId).orElseThrow();
    }

}
