package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.AddressEntity;
import com.sparta.eng80.pressplay.entities.CityEntity;
import com.sparta.eng80.pressplay.entities.CountryEntity;
import com.sparta.eng80.pressplay.repositories.AddressRepository;
import com.sparta.eng80.pressplay.repositories.CityRepository;
import com.sparta.eng80.pressplay.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public Optional<AddressEntity> findFullAddressByCustomerId(int id) {
        return addressRepository.findFullAddressByCustomerId(id);
    }

    public Optional<CityEntity> findCityByCityAndCountry(String city, String country) {
        return cityRepository.findCityEntitiesByCityEqualsAndCountryCountryEquals(city, country);
    }

    public Optional<CountryEntity> findCountryByName(String name) {
        return countryRepository.findCountryEntityByCountryEquals(name);
    }

    public void saveAddress(AddressEntity addressEntity) {
        addressRepository.save(addressEntity);
    }

    public void saveCity(CityEntity cityEntity) {
        cityEntity.setLastUpdate(Date.valueOf(LocalDate.now()));
        cityRepository.save(cityEntity);
    }

    public void saveCountry(CountryEntity countryEntity) {
        countryEntity.setLastUpdate(Date.valueOf(LocalDate.now()));
        countryRepository.save(countryEntity);
    }
}
