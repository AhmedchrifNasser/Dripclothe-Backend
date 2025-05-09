package com.dripclothe.repository;

import com.dripclothe.model.Artiste;
import com.dripclothe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtisteRepo extends JpaRepository<Artiste, Integer> {

}