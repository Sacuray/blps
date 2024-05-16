package org.example.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name="usercarid", columnDefinition = "serial")
    private Integer userCarId;
    @Column(name="userid")
    private Integer userId;
    @Column(name="carid")
    private Integer carId;
    @Column(name="status")
    private String status;
}