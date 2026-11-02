package com.gameflix.dto.game;

import com.gameflix.model.Game;

import java.time.Instant;
import java.util.UUID;

public record GameResponse(
        UUID id,
        String title,
        String description,
        String rating,
        Instant createdAt,
        Instant updatedAt
) {
    public static GameResponse from(Game game) {
        return new GameResponse(
                game.getId(),
                game.getTitle(),
                game.getDescription(),
                game.getRating().toString(),
                game.getCreatedAt(),
                game.getUpdatedAt()
        );
    }
}