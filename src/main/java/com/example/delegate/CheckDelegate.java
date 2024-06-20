package com.example.delegate;

import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Slf4j
@Named("checkDelegate")
@RequiredArgsConstructor
@Component("checkDelegate")
public class CheckDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String user = (String) delegateExecution.getVariable("user");
        String password = (String) delegateExecution.getVariable("password");

        log.info("country: {}, title: {}", user, password);
        if (user.isBlank() || password.isBlank()) {
            throw new BpmnError("Все поля формы должны быть заполнены");
        }
    }
}
