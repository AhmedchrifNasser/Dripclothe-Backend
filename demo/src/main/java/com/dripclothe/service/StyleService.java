package com.dripclothe.service;

import com.dripclothe.model.Artiste;
import com.dripclothe.model.Style;
import com.dripclothe.repository.ArtisteRepo;
import com.dripclothe.repository.StyleRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StyleService {
    private final StyleRepo styleRepo;
    private final ArtisteRepo artisteRepo;

    public StyleService(StyleRepo styleRepo, ArtisteRepo artisteRepo) {this.styleRepo = styleRepo;
        this.artisteRepo = artisteRepo;
    }
    public Style addStyle(Style style, Integer artisteId) throws IOException {
        Artiste artiste = artisteRepo.findById(artisteId).orElseThrow(null);
        style.setArtiste(artiste);
        return styleRepo.save(style);
    }

    public void deleteStyle(Integer id){
        Style style = styleRepo.findStyleById(id);
        styleRepo.delete(style);
    }

    public Style updateStyle(Style style){
        return styleRepo.save(style);
    }

    public List<Style> findAllStyles() {
        return styleRepo.findAll();
    }
    public Style findStyleById(Integer styleId){
        if(styleId == null) {
            return null;
        }
        return styleRepo.findStyleById(styleId);
    }

    public List<Style> findStyleByArtisteId(Integer artisteId) {
        if (artisteId == null) {
            //Throw an exception
            return null;
        }
        return styleRepo.findAllByArtisteId(artisteId);
    }

    public Page<Style> findStyleByArtisteIdPaginated(Integer artisteId, int page, int size) {
        if (artisteId == null) {
            return null;
        }
        Pageable pageable = PageRequest.of(page, size);
        return styleRepo.findAllByArtisteId(artisteId,pageable);
    }
}
