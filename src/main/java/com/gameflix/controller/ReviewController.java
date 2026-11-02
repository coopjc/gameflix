package com.gameflix.controller;

import com.gameflix.dto.review.GetReviewsByGameResponse;
import com.gameflix.dto.review.ReviewResponse;
import com.gameflix.model.Review;
import com.gameflix.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReviewsByGameResponse> getReviewsByGame(@PathVariable("id") UUID gameId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size)
    {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Review> reviews = reviewService.getReviewsByGame(gameId, pageable);
        Page<ReviewResponse> response = reviews.map(ReviewResponse::from);

        return new ResponseEntity<>(GetReviewsByGameResponse.from(response), HttpStatus.OK);
    }
}