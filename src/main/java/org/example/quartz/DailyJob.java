package org.example.quartz;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.Entities.CarEntity;
import org.example.Repos.CarRepo;
import org.example.Repos.UserCarRepo;
import org.example.message.MQTTSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DailyJob implements Job {

    @Autowired
    private CarRepo carRepo;





    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CarEntity carEntity = carRepo.findFirstByOrderByPriceAsc();
        if (carEntity != null) {
            MQTTSender mqttSender = new MQTTSender();
            try {
                mqttSender.publish("ad:" + carEntity.getId());
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }
}