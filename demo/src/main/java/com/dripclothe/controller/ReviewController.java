package com.dripclothe.controller;

import com.dripclothe.model.Reviews;
import com.dripclothe.model.Style;
import com.dripclothe.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/review")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/add")
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review){
        Reviews newReview = reviewService.addReview(review);
        return  new ResponseEntity<>(newReview, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Reviews>> getAllReviews(){
        List<Reviews> reviews = reviewService.findAllReviews();
        return  new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    /*
    @GetMapping("/find/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable("id") Integer id){
        Style style = styleService.findStyleById(id);
        return new ResponseEntity<>(style, HttpStatus.OK);
    }*/


}
