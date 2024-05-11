package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.example.Enums.EngineTypeEnum;
import org.example.Enums.WheelTypeEnum;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name="userId", columnDefinition = "serial")
    private Integer user_id;
    @Column(name="username", unique = true)
    private String username;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="phone_number")
    private String phone_number;
    @Column(name = "registration_date", columnDefinition = "DATE", nullable = false)
    private LocalDate registrationDate;
}