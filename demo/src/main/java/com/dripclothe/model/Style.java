package com.dripclothe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import java.util.Set;

@Entity
@Table(name = "style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @Column(name = "style_id_artiste")
    private Integer styleIdByArtiste;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "artiste_id")

    private Artiste artiste;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "style")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Integer getStyleIdByArtiste() {
        return styleIdByArtiste;
    }

    public void setStyleIdByArtiste(Integer styleIdByArtiste) {
        this.styleIdByArtiste = styleIdByArtiste;
    }

    public void addProduct(Product product){
        if(! products.contains(product) ){
            products.add(product);

        }
    }
}
