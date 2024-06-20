package com.example.delegate;

import com.example.DTO.SearchParametrs;
import com.example.DTO.UserRegistration;
import com.example.Entities.CarEntity;
import com.example.Services.AuthService;
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
@Named("searchCars")
@RequiredArgsConstructor
@Component("searchCars")
public class SearchCars implements JavaDelegate{

    @Autowired
    private SearchService searchService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String model = (String) delegateExecution.getVariable("model");
        String color = (String) delegateExecution.getVariable("color");
        Long minPrice = (Long) delegateExecution.getVariable("minPrice");
        Long maxPrice = (Long) delegateExecution.getVariable("maxPrice");
        SearchParametrs searchParametrs = new SearchParametrs();
        searchParametrs.setModel(model);
        searchParametrs.setColor(color);

        searchParametrs.setMinPrice(minPrice);
        searchParametrs.setMaxPrice(maxPrice);
        CarEntity car = searchService.search(searchParametrs).get(0);
        if (model.isBlank() || color.isBlank()) {
            throw new BpmnError("Все поля формы должны быть заполнены");
        }
        delegateExecution.setVariable("adnumber", car.getAdNumber());

    }
}
