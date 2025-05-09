package com.dripclothe.repository;

import com.dripclothe.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepo extends JpaRepository<Size, Integer> {
    Size findByName(String name);
}
