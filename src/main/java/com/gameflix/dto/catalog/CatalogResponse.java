package com.gameflix.dto.catalog;

import com.gameflix.dto.game.GameResponse;
import com.gameflix.model.Game;
import org.springframework.data.domain.Page;

public record CatalogResponse(
        Page<GameResponse> catalog
) {
    public static CatalogResponse from(Page<GameResponse> games) {
        return new CatalogResponse(games);
    }
}