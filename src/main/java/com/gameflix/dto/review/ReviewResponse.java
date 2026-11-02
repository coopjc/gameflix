package com.gameflix.dto.review;

import com.gameflix.model.GameRating;
import com.gameflix.model.Review;
import com.gameflix.model.ReviewRating;

import java.time.Instant;
import java.util.UUID;

public record ReviewResponse(
        UUID id,
        UUID userId,
        UUID gameId,
        String title,
        String body,
        ReviewRating rating,
        Instant createdAt,
        Instant updated
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUser().getId(),
                review.getGame().getId(),
                review.getTitle(),
                review.getBody(),
                review.getRating(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}