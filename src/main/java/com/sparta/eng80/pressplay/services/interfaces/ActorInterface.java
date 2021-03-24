package com.sparta.eng80.pressplay.services.interfaces;

import com.sparta.eng80.pressplay.entities.ActorEntity;

public interface ActorInterface extends ServiceInterface<ActorEntity> {

    Iterable<ActorEntity> getAllActorsAlphabetically();


}
