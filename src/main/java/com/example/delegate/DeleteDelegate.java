package com.example.delegate;

import com.example.DTO.LoginDTO;
import com.example.DTO.LoginResponse;
import com.example.DTO.SearchParametrs;
import com.example.DTO.UserRegistration;
import com.example.Entities.CarEntity;
import com.example.Repos.CarRepo;
import com.example.Services.*;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;




@Slf4j
@Named("deleteDelegate")
@RequiredArgsConstructor
@Component("deleteDelegate")
public class DeleteDelegate implements JavaDelegate{

    @Autowired
    private DeleteService deleteService;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        Long adnumber = (Long) delegateExecution.getVariable("adnumber");
        deleteService.delete(adnumber);



    }
}
