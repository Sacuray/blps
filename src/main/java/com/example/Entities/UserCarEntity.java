package com.example.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "myusercar")
public class UserCarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usercarid", columnDefinition = "serial")
    private Long userCarId;
    @Column(name="userid")
    private Long userId;
    @Column(name="carid")
    private Long carId;
}