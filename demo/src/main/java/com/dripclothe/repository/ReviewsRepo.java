package com.dripclothe.repository;

import com.dripclothe.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepo extends JpaRepository<Reviews, Integer> {
}
