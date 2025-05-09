package com.dripclothe.controller;

import com.dripclothe.model.Color;
import com.dripclothe.model.Size;
import com.dripclothe.service.ColorSizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/color_size")
public class ColorSizeController {
    private final ColorSizeService colorSizeService;

    public ColorSizeController(ColorSizeService colorSizeService) {
        this.colorSizeService = colorSizeService;
    }

    @GetMapping("/color/{colorId}")
    public ResponseEntity<Color> getColor(@PathVariable("colorId") Integer colorId) {
        Color color = this.colorSizeService.findColorById(colorId);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @GetMapping("/size/{sizeId}")
    public ResponseEntity<Size> getSize(@PathVariable("sizeId") Integer sizeId) {
        Size size = this.colorSizeService.findSizeById(sizeId);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
}
