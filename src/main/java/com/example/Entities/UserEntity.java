package com.example.Entities;

import jakarta.persistence.*;
import lombok.*;
import com.example.security.Role;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "myuser")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid", columnDefinition = "serial")
    private Long userId;
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
    @Column(name="wallet")
    private Long wallet;
    @Enumerated(STRING)
    @Column(name="role", nullable = false)
    private Role role;


}