package com.example.delegate;

import com.example.DTO.UserRegistration;
import com.example.Services.AuthService;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Slf4j
@Named("userRegistrationDelegate")
@RequiredArgsConstructor
@Component("userRegistrationDelegate")
public class UserRegistrationDelegate implements JavaDelegate{

    @Autowired
    private AuthService authService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username");
        String email = (String) delegateExecution.getVariable("email");
        String password = (String) delegateExecution.getVariable("password");
        String phoneNumber = (String) delegateExecution.getVariable("phoneNumber");

        log.info("username: {}, email: {}, password: {}, phoneNumber: {}", username, email, password, phoneNumber);
        authService.saveUser(new UserRegistration(username, email, password, phoneNumber));
        if (password.isBlank()) {
            throw new BpmnError("Все поля формы должны быть заполнены");
        }
    }
}
