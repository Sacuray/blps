package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "myuser")
public class UserEntity {
    @Id
    @Column(name="userid", columnDefinition = "serial")
    private Integer userId;
    @Column(name="username", unique = true)
    private String username;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="phonenumber")
    private String phoneNumber;
    @Column(name = "registrationdate", columnDefinition = "DATE", nullable = false)
    private LocalDate registrationDate;
}