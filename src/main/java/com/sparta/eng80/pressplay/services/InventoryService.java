package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public int getNumberInStockByFilmId(int filmId) {
        return inventoryRepository.getInventoryCountByFilmId(filmId);
    }
}
