package com.example.demo.DTO;

import com.example.demo.Enums.EngineTypeEnum;
import com.example.demo.Enums.WheelTypeEnum;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

@Data
public class SearchParametrs {

    private String brand;
    private String model;
    
    private String color;
    private String wheel;
    private String engine;
    private Boolean accident;
    private Long minPrice;
    private Long maxPrice;
    private String sorting;
    private Boolean asc;
}
