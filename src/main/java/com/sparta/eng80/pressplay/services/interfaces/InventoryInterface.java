package com.sparta.eng80.pressplay.services.interfaces;

import com.sparta.eng80.pressplay.entities.InventoryEntity;

public interface InventoryInterface extends ServiceInterface<InventoryEntity>{

    int getNumberInStockByFilmId(int filmId);
}
