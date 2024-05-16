package org.example.DTO;

import java.time.LocalDate;


import org.example.Enums.EngineTypeEnum;
import org.example.Enums.WheelTypeEnum;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class CarProfile {


    private String model;
    private Long adNumber;
    private LocalDate yearOfRelease;
    private String colour;
    private WheelTypeEnum wheel;
    private EngineTypeEnum engine;
    private Float engineVolume;
    private Long mileage;
    private Boolean accident;
    private Long price;
}
