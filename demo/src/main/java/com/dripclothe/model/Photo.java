package com.dripclothe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photoUrl")
    public String photoUrl;

    @Column(name = "color")
    public String color;
}
