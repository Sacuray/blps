package org.example.Services;

import org.example.DTO.CarProfile;
import org.example.Entities.CarEntity;
import org.example.Entities.UserCarEntity;
import org.example.Entities.UserEntity;
import org.example.Repos.CarRepo;
import org.example.Repos.UserCarRepo;
import org.example.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
                return new CarProfile(car.getModel(), car.getAdNumber(), car.getYear_of_release(), car.getColour(), car.getWheel(), car.getEngine(), car.getEngineVolume(), car.getMileage(), car.getAccident(), car.getPrice());
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }



    public void addCarWithOwnership(UserEntity userEntity, CarEntity carEntity) {
        UserCarEntity userCar = new UserCarEntity();
        userCar.setCar_id(carEntity.getId());
        userCar.setUser_id(userEntity.getUser_id());
        userCarRepo.save(userCar);
    }
}
