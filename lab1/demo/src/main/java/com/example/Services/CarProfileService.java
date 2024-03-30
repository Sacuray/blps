package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.CarProfile;
import com.example.Entities.CarEntity;
import com.example.Repos.CarRepo;

@Service
public class CarProfileService {
    @Autowired
    private CarRepo carRepo;

    public CarProfile carProfile(Long adNumber) {
        try {
            CarEntity car = carRepo.findByAdNumber(adNumber);
            if (car != null)
                return new CarProfile(car.getModel(), car.getAdNumber(), car.getYear_of_release(), car.getColour(), car.getWheel(), car.getEngine(), car.getEngineVolume(), car.getMileage(), car.getAccident(), car.getPrice());
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
