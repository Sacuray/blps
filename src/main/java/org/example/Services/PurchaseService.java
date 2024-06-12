package org.example.Services;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.Entities.CarEntity;
import org.example.Entities.UserCarEntity;
import org.example.Entities.UserEntity;
import org.example.Repos.CarRepo;
import org.example.Repos.UserCarRepo;
import org.example.Repos.UserRepo;
import org.example.message.MQTTSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PurchaseService {
    @Autowired
    private final CarRepo carRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final UserCarRepo userCarRepo;

    private final TransactionTemplate transactionTemplate;

    public PurchaseService(CarRepo carRepo, UserRepo userRepo, UserCarRepo userCarRepo, PlatformTransactionManager transactionManager) {
        this.carRepo = carRepo;
        this.userRepo = userRepo;
        this.userCarRepo = userCarRepo;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }


    public boolean purchaseCar(String username, Long adNumber) {
        return transactionTemplate.execute(status -> {
            UserEntity user = userRepo.getByUsername(username);
            CarEntity car = carRepo.findByAdNumber(adNumber);
            UserCarEntity old = userCarRepo.findByCarId(car.getId());
            UserEntity userOld = userRepo.findById(old.getUserId()).orElseThrow(() -> new RuntimeException("UserOld not found"));

            if (user.getWallet() < car.getPrice()) {
                return false;
            }

            userOld.setWallet(userOld.getWallet() + car.getPrice());
            user.setWallet(user.getWallet() - car.getPrice());
            userCarRepo.deleteByCarId(car.getId());

            UserCarEntity userCar = new UserCarEntity();
            userCar.setCarId(car.getId());
            userCar.setUserId(user.getUserId());
            userRepo.saveAndFlush(userOld);
            userRepo.saveAndFlush(user);
            userCarRepo.saveAndFlush(userCar);
            MQTTSender mqttSender = new MQTTSender();
            try {
                mqttSender.publish("purchase:" + userOld.getUsername());
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
    }
}