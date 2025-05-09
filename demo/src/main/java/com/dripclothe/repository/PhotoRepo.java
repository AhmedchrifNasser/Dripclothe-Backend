package com.dripclothe.repository;

import com.dripclothe.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {
    //List<Photo> findAllByProductId(Integer productId);
}
