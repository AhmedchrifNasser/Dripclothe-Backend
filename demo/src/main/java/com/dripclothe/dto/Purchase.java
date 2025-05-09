package com.dripclothe.dto;

import com.dripclothe.model.Address;
import com.dripclothe.model.Customer;
import com.dripclothe.model.Order;
import com.dripclothe.model.OrderItem;

import java.util.Set;

public class Purchase {

    private Customer customer;
    private Order order;
    private Set<OrderItem> orderItems;
    private Address shippingAddress;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
