package com.sparta.eng80.pressplay.services;

import java.util.Optional;

public interface AccountInterface<T> extends ServiceInterface<T>{

    Optional<T> findByUsername(String username);
    boolean isAdmin(Integer id);

}
