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
        return transactionTemplate.execute(status -> {
            CarEntity car = carRepo.findByAdNumber(adNumber);
            UserCarEntity old = userCarRepo.findByCarId(car.getId());

            if(car == null || old == null) {
                return false;
            }
            UserEntity user = userRepo.getById(old.getUserId());
            userCarRepo.delete(old);
            carRepo.delete(car);
            MQTTSender mqttSender = new MQTTSender();
            try {
                mqttSender.publish("delete:" + user.getUsername());
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
    }
}
