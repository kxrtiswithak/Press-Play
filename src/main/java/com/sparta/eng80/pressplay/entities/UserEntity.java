package com.sparta.eng80.pressplay.entities;

public interface UserEntity {

    String getFirstName();
    String getLastName();
    String getEmail();
    AddressEntity getAddress();
    String getPassword();
}
