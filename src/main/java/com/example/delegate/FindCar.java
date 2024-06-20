package com.example.delegate;

import com.example.DTO.SearchParametrs;
import com.example.DTO.UserRegistration;
import com.example.Entities.CarEntity;
import com.example.Repos.CarRepo;
import com.example.Services.AuthService;
import com.example.Services.CarProfileService;
import com.example.Services.SearchService;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;




@Slf4j
@Named("findCar")
@RequiredArgsConstructor
@Component("findCar")
public class FindCar implements JavaDelegate{

    @Autowired
    private CarProfileService carProfileService;
    @Autowired
    private CarRepo carRepo;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long adnumber = (Long) delegateExecution.getVariable("adnumber");
        CarEntity car = carRepo.findByAdNumber(adnumber);
        log.info(car.toString());
        if (car == null) {
            log.info("Машина не найдена");
            throw new BpmnError("Машина не найдена");
        }


    }
}
