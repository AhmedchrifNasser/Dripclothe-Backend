package com.dripclothe.model;

public class ProductQuantity {
    Product product;
    Integer Quantity;

    public ProductQuantity(Product productById, Integer quantity) {
        this.product = productById;
        this.Quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
