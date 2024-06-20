package com.example.Services;


import com.example.Entities.CarEntity;
import com.example.Entities.UserCarEntity;
import com.example.Entities.UserEntity;
import com.example.Repos.CarRepo;
import com.example.Repos.UserCarRepo;
import com.example.Repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class DeleteService {
    @Autowired
    private final CarRepo carRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final UserCarRepo userCarRepo;

    private final TransactionTemplate transactionTemplate;

    public DeleteService(CarRepo carRepo, UserRepo userRepo, UserCarRepo userCarRepo, PlatformTransactionManager transactionManager) {
        this.carRepo = carRepo;
        this.userRepo = userRepo;
        this.userCarRepo = userCarRepo;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }


    public boolean delete(Long adNumber) {

            CarEntity car = carRepo.findByAdNumber(adNumber);
            UserCarEntity old = userCarRepo.findByCarId(car.getId());

            if(car == null || old == null) {
                return false;
            }
            UserEntity user = userRepo.getById(old.getUserId());
            userCarRepo.delete(old);
            carRepo.delete(car);

            return true;

    }
}
