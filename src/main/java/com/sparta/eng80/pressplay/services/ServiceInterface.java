package com.sparta.eng80.pressplay.services;

import java.util.Optional;

public interface ServiceInterface<T> {

    Optional<T> findById(Integer id);
    Iterable<T> findAll();
    void save(T t);

}
