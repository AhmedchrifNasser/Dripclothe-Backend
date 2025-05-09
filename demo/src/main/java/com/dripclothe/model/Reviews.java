package com.dripclothe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "starNumber")
    private Integer starNumber;

    public Integer getStarNumber() {
        return starNumber;
    }

    @Column(name = "review")
    private String review;

    public Integer getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
