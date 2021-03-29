package com.sparta.eng80.pressplay.services.interfaces;


import java.util.ArrayList;
import java.util.Optional;

public interface AccountInterface<T> extends ServiceInterface<T>{

    Optional<T> findByEmail(String email);
    boolean isAdmin(Integer id);
    ArrayList<String> getDetails(T t);
}
