package com.example.delegate;

import com.example.DTO.LoginDTO;
import com.example.DTO.LoginResponse;
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
@Named("loginDelegate")
@RequiredArgsConstructor
@Component("loginDelegate")
public class LoginDelegate implements JavaDelegate{

    @Autowired
    private AuthService authService;
    @Autowired
    private CarRepo carRepo;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        LoginResponse response = authService.login(loginDTO);
        log.info(response.toString());
        if (response == null) {
            log.info("Машина не найдена");
            throw new BpmnError("Пользоваетль не найден");
        }
        if(response.getJwt() == "ROLE_ADMIN"){
            delegateExecution.setVariable("is_admin", true);
        }else{
            delegateExecution.setVariable("is_admin", false);
        }


    }
}
