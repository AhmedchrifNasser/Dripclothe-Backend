package com.dripclothe.controller;

import com.dripclothe.model.Artiste;
import com.dripclothe.service.ArtisteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/artiste")
public class ArtisteController {

    private final ArtisteService artisteService;

    public ArtisteController(ArtisteService artisteService){
        this .artisteService = artisteService;
    }

    @PostMapping("/add")
    public ResponseEntity<Artiste> addArtiste(@RequestBody Artiste artiste) {
        Artiste newArtiste = artisteService.addArtiste(artiste);
        return  new ResponseEntity<>(newArtiste, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArtiste(@PathVariable("id") Integer id){
        artisteService.deleteArtiste(id);
        return new ResponseEntity<String>("Expense is deleted successfully.!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Artiste> updateArtiste(@RequestBody Artiste artiste){
        Artiste updatedArtiste =  artisteService.updateArtiste(artiste);
        return new ResponseEntity<Artiste>(updatedArtiste, HttpStatus.OK);
    }

    /*@GetMapping("/all")
    public ResponseEntity<List<Artiste>> getAllArtiste(){
        List<Artiste> artistes = artisteService.findAllArtistes();
        return  new ResponseEntity<>(artistes, HttpStatus.OK);
    }*/

    @GetMapping("/all")
    public ResponseEntity<Page<Artiste>> getAllArtiste(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size){
        Page<Artiste> artistes = artisteService.findAllArtistesPaginated(page,size);
        return  new ResponseEntity<>(artistes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Artiste> getArtisteById(@PathVariable("id") Integer id){
        Artiste artiste = artisteService.findArtisteById(id);
        return new ResponseEntity<>(artiste, HttpStatus.OK);
    }
}
