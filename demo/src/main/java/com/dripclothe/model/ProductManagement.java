package com.dripclothe.model;

import jakarta.persistence.*;
@Entity
@Table(name = "ProductManagement")
public class ProductManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "AliexpressId")
    private String aliExpressId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // Cascade persist operation to Product
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAliExpressId() {
        return aliExpressId;
    }

    public void setAliExpressId(String aliExpressId) {
        this.aliExpressId = aliExpressId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
