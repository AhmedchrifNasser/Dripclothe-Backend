package com.dripclothe.service;

import com.dripclothe.model.Reviews;
import com.dripclothe.repository.ReviewsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {

    private ReviewsRepo reviewsRepo;

    public ReviewService(ReviewsRepo reviewsRepo) {
        this.reviewsRepo = reviewsRepo;
    }

    public Reviews addReview(Reviews review){
        return reviewsRepo.save(review);
    }

    public List<Reviews> findAllReviews() {
        return reviewsRepo.findAll();
    }
}
