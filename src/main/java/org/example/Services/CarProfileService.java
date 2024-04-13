package org.example.Services;

import org.example.DTO.CarProfile;
import org.example.Entities.CarEntity;
import org.example.Repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
