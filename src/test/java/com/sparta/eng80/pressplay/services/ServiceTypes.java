package com.sparta.eng80.pressplay.services;

public enum ServiceTypes {
    CUSTOMER, FILM, RENTAL, SERVICE, ACCOUNT;

    public Object getServiceType() {
        switch(this) {
            case CUSTOMER:
                return CustomerInterface.class;
            case FILM:
                return FilmInterface.class;
            case RENTAL:
                return RentalInterface.class;
            case SERVICE:
                return ServiceInterface.class;
            case ACCOUNT:
                return AccountInterface.class;
            default:
                return null;
        }
    }
}
