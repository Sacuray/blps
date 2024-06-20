package com.example.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRegistration {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
