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

public class CarProfile {


    private String model;
    private Long adNumber;
    private LocalDate year_of_release;
    private String colour;
    private WheelTypeEnum wheel;
    private EngineTypeEnum engine;
    private Float engineVolume;
    private Long mileage;
    private Boolean accident;
    private Long price;
}
