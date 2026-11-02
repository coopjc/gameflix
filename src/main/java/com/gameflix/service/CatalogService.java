package com.gameflix.service;

import com.gameflix.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private final GameService gameService;

    public CatalogService(GameService gameService) {
        this.gameService = gameService;
    }

    public Page<Game> getCatalog(Pageable pageable) {
        return gameService.getGames(pageable);
    }
}