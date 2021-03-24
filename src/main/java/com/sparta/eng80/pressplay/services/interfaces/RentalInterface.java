package com.sparta.eng80.pressplay.services.interfaces;

import com.sparta.eng80.pressplay.entities.RentalEntity;

public interface RentalInterface extends ServiceInterface<RentalEntity>{

    Iterable<RentalEntity> findByCustomerId(int id);
}
