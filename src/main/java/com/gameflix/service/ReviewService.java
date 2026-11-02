package com.gameflix.service;

import com.gameflix.exception.game.GameNotFoundException;
import com.gameflix.model.Review;
import com.gameflix.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public Page<Review> getReviewsByGame(UUID gameId, Pageable pageable)
    {
        if(!reviewRepository.existsById(gameId)) {
            throw new GameNotFoundException("Game not found");
        }

        return reviewRepository.findByGameId(gameId, pageable);
    }
}