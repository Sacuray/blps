package org.example.DTO;

import org.example.Enums.EngineTypeEnum;
import org.example.Enums.WheelTypeEnum;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class SearchParametrs {


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