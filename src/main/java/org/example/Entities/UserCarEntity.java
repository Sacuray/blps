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
    @Column(name="user_car_id", columnDefinition = "serial")
    private Long user_car_id;
    @Column(name="user_id")
    private Long user_id;
    @Column(name="car_id")
    private Long car_id;
}
