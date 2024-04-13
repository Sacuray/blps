package com.example.demo.DTO;

import java.time.LocalDate;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ShortCar {
    private Long adNumber;
    private String brand;
    private String model;
    private Long price;
    private LocalDate year_of_release;
    private Long mileage;
}
