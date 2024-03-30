package com.example.demo.DTO;

import java.time.LocalDate;

import org.apache.catalina.Engine;
import org.hibernate.annotations.ColumnTransformer;

import com.example.demo.Enums.EngineTypeEnum;
import com.example.demo.Enums.WheelTypeEnum;

import jakarta.persistence.Enumerated;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class SearchParametrs {


    private String model;
    private String colour;
    private WheelTypeEnum wheel;
    private EngineTypeEnum engine;
    private Boolean accident;

    private String sortParamName;
    private Boolean isAsc;
}
