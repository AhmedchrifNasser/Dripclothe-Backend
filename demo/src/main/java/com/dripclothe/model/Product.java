package com.dripclothe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
    @Column(name = "shippingFee")
    private Double shippingFee;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<Size> sizes;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<Color> colors;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<Photo> photos;

    @ManyToOne
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Categories categories;

    public Product(Product originalProduct) {
        this.id = originalProduct.id;
        this.name = originalProduct.name;
        this.description = originalProduct.description;
        this.price = originalProduct.price;
        this.shippingFee = originalProduct.shippingFee;
        this.sizes = originalProduct.sizes;
        this.style = originalProduct.style;
        this.colors = originalProduct.colors;
        this.photos = originalProduct.photos;
        this.categories = originalProduct.categories;
    }

    public Product() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
