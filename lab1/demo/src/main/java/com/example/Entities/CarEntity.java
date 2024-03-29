package main.java.com.example.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import main.java.com.example.Enums.EngineTypeEnum;
import main.java.com.example.Enums.WheelTypeEnum;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @Column(name="id", columnDefinition = "serial")
    private Integer id;
    @Column(name="model")
    private String model;
    @Column(name="adNumber", nullable = false, unique = true)
    private Long adNumber;
    @Column(name = "year_of_release", columnDefinition = "DATE", nullable = false)
    private LocalDate year_of_release;
    @Enumerated(EnumType.STRING)
    @Column(name = "colour")
    private String colour;
    @ColumnTransformer(write = "?::wheelType")
    @Column(name = "wheel", columnDefinition = "wheelType", nullable = false)
    private WheelTypeEnum wheel;
    @ColumnTransformer(write = "?::engineType")
    @Column(name = "engine", columnDefinition = "engineType", nullable = false)
    private WheelTypeEnum engine;
    @Column(name = "engineVolume")
    private Float engineVolume;
    @Column(name = "mileage")
    private Long mileage;
    @Column(name = "accident")
    private Boolean accident;
}