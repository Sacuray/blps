package org.example.Services;

import org.example.Entities.CarEntity;
import org.example.Entities.UserCarEntity;
import org.example.Entities.UserEntity;
import org.example.Repos.CarRepo;
import org.example.Repos.UserCarRepo;
import org.example.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    @Autowired
    private final CarRepo carRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final UserCarRepo userCarRepo;

    public PurchaseService(CarRepo carRepo, UserRepo userRepo, UserCarRepo userCarRepo) {
        this.carRepo = carRepo;
        this.userRepo = userRepo;
        this.userCarRepo = userCarRepo;
    }


    public void purchaseCar(Long userId, Long carId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CarEntity car = carRepo.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        userCarRepo.deleteByCarId(carId);

        UserCarEntity userCar = new UserCarEntity();
        userCar.setCarId(car.getId());
        userCar.setUserId(user.getUserId());
        userCarRepo.saveAndFlush(userCar);
    }
}
