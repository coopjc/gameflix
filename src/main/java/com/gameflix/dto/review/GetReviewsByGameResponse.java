package com.gameflix.dto.review;

import org.springframework.data.domain.Page;

public record GetReviewsByGameResponse(
        Page<ReviewResponse> reviews
) {
    public static GetReviewsByGameResponse from(Page<ReviewResponse> reviews) {
        return new GetReviewsByGameResponse(reviews);
    }
}