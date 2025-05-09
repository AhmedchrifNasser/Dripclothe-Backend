package com.dripclothe.service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dripclothe.model.Artiste;
import com.dripclothe.repository.ArtisteRepo;

@Service
public class ArtisteService {
    private final ArtisteRepo artisteRepo;

    public ArtisteService(ArtisteRepo artisteRepo) {
        this.artisteRepo = artisteRepo;
    }

    public Artiste addArtiste(Artiste artiste) {
        return artisteRepo.save(artiste);
    }

    public void deleteArtiste(Integer id){
        Artiste artiste = artisteRepo.findById(id).orElseThrow();
        artisteRepo.delete(artiste);
    }
    public Artiste updateArtiste(Artiste artiste){
        return artisteRepo.save(artiste);
    }

    public List<Artiste> findAllArtistes() {
        return artisteRepo.findAll();
    }

    public Artiste findArtisteById(Integer id) {
        if (id == null) {
            //Throw an exception
            return null;
        }
        return artisteRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aucun artiste avec l'ID=" + id + "n'ete trouve dans la BDD "));
    }

    public Page<Artiste> findAllArtistesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return artisteRepo.findAll(pageable);
    }

}
