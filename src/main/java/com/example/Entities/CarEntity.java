package com.example.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.example.Enums.EngineTypeEnum;
import com.example.Enums.WheelTypeEnum;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "mycar")
public class CarEntity {
    @Id
    @Column(name="id", columnDefinition = "serial")
    private Long id;
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="adnumber", nullable = false, unique = true)
    private Long adNumber;
    @Column(name = "yearofrelease", columnDefinition = "DATE", nullable = false)
    private LocalDate yearOfRelease;
    @Column(name = "colour")
    private String colour;
    @Enumerated(EnumType.STRING)
    @Column(name = "wheel", nullable = false)
    private WheelTypeEnum wheel;
    @Enumerated(EnumType.STRING)
    @Column(name = "engine", nullable = false)
    private EngineTypeEnum engine;
    @Column(name = "enginevolume")
    private Float engineVolume;
    @Column(name = "mileage")
    private Long mileage;
    @Column(name = "accident")
    private Boolean accident;
    @Column(name = "price")
    private Long price;


}