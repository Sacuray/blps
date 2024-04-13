package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CarProfile;
import com.example.demo.Entities.CarEntity;
import com.example.demo.Repos.CarRepo;

@Service
public class CarProfileService {
    @Autowired
    private CarRepo carRepo;

    public CarProfile carProfile(Long adNumber) {
        try {
            CarEntity car = carRepo.findByAdNumber(adNumber);
            if (car != null)
                return new CarProfile(car.getBrand(), car.getModel(), car.getAdNumber(), car.getYear_of_release(), car.getColour(), car.getWheel(), car.getEngine(), car.getEngineVolume(), car.getMileage(), car.getAccident(), car.getPrice());
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
