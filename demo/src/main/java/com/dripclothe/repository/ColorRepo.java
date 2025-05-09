package com.dripclothe.repository;

import com.dripclothe.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepo extends JpaRepository<Color, Integer> {
    Color findColorByName(String name);
}
