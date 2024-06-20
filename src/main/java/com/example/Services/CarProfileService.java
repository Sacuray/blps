package com.example.Services;

import com.example.DTO.CarProfile;
import com.example.Entities.CarEntity;
import com.example.Entities.UserCarEntity;
import com.example.Entities.UserEntity;
import com.example.Repos.CarRepo;
import com.example.Repos.UserCarRepo;
import com.example.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarProfileService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserCarRepo userCarRepo;

    public CarProfile carProfile(Long adNumber) {
        try {
            CarEntity car = carRepo.findByAdNumber(adNumber);
            if (car != null)
                return new CarProfile(car.getModel(), car.getAdNumber(), car.getYearOfRelease(), car.getColour(), car.getWheel(), car.getEngine(), car.getEngineVolume(), car.getMileage(), car.getAccident(), car.getPrice());
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }



    public void addCarWithOwnership(UserEntity userEntity, CarEntity carEntity) {
        UserCarEntity userCar = new UserCarEntity();
        userCar.setCarId(carEntity.getId());
        userCar.setUserId(userEntity.getUserId());
        userCarRepo.save(userCar);
    }
}
