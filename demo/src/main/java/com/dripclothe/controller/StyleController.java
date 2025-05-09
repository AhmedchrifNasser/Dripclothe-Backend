package com.dripclothe.controller;

import com.dripclothe.model.Artiste;
import com.dripclothe.model.Style;
import com.dripclothe.service.StyleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/backend/style")
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping("/addStylePerArtisteId/{artisteId}")
    public ResponseEntity<Style> addStyle(@RequestBody Style style, @PathVariable(value = "artisteId") Integer artisteId) throws IOException {
        Style newStyle = styleService.addStyle(style, artisteId);
        return new ResponseEntity<>(newStyle, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Style> updateStyle(@RequestBody Style style) {
        Style updatedStyle = styleService.updateStyle(style);
        return new ResponseEntity<Style>(updatedStyle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStyle(@PathVariable("id") Integer id) {
        styleService.deleteStyle(id);
        return new ResponseEntity<String>("Expense is deleted successfully.!", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Style>> getAllStyle() {
        List<Style> styles = styleService.findAllStyles();
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    /* @GetMapping("/{artisteId}")
    public ResponseEntity<List<Style>> getStylesByArtisteId(@PathVariable(value = "artisteId") Integer artisteId) {
        List<Style> styles = styleService.findStyleByArtisteId(artisteId);
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }*/

    @GetMapping("/{artisteId}")
    public ResponseEntity<Page<Style>> getAllArtiste(@PathVariable(value = "artisteId") Integer artisteId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<Style> styles = styleService.findStyleByArtisteIdPaginated(artisteId,page, size);
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

}
