package com.example.DTO;

import java.time.LocalDate;

import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ShortCar {
    private Long adNumber;
    private String model;
    private Long price;
    private LocalDate yearOfRelease;
    private Long mileage;
}
