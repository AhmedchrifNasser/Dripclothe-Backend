package com.dripclothe.repository;

import com.dripclothe.model.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StyleRepo extends JpaRepository<Style, Integer> {
    Page<Style> findAllByArtisteId(Integer artisteId, Pageable pageable);
    List<Style> findAllByArtisteId(Integer artisteId);
    Style findStyleById(Integer styleId);
}
