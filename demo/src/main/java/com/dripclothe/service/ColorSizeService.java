package com.dripclothe.service;

import com.dripclothe.model.Color;
import com.dripclothe.model.Size;
import com.dripclothe.repository.ColorRepo;
import com.dripclothe.repository.SizeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorSizeService {
    private final ColorRepo colorRepo;
    private final SizeRepo sizeRepo;

    public ColorSizeService(ColorRepo colorRepo, SizeRepo sizeRepo) {
        this.colorRepo = colorRepo;
        this.sizeRepo = sizeRepo;
    }

    public Size findSizeById(Integer sizeId){
        return sizeRepo.findById(sizeId).orElseThrow(
                () -> new EntityNotFoundException("Aucun size avec l'ID=" + sizeId + "n'ete trouve dans la BDD "));
    }
    public Color findColorById(Integer colorId){
        return colorRepo.findById(colorId).orElseThrow(
                () -> new EntityNotFoundException("Aucun color avec l'ID=" + colorId + "n'ete trouve dans la BDD "));
    }
}
