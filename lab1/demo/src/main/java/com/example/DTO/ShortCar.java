package com.example.DTO;

import java.time.LocalDate;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ShortCar {
    private Long adNumber;
    private String model;
    private Long price;
    private LocalDate year_of_release;
    private Long mileage;
}
