package com.example.Services;


import com.example.DTO.LoginDTO;
import com.example.DTO.LoginResponse;
import com.example.DTO.UserRegistration;
import com.example.Entities.UserEntity;
import com.example.Repos.UserRepo;

import com.example.security.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.security.auth.login.CredentialException;
import java.io.*;

@Slf4j
@Service

public class AuthService {



    private final UserRepo userRepository;

    private final UserRepo userRepo;
    private final TransactionTemplate transactionTemplate;

    public AuthService(UserRepo userRepository, UserRepo userRepo, PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;

        this.userRepo = userRepo;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public boolean saveUser(UserRegistration dto) {



            UserEntity check = userRepo.getByUsername(dto.getUsername());
            if (check == null) {
                UserEntity token = new UserEntity();
                token.setUsername(dto.getUsername());
                token.setPassword(dto.getPassword());
                token.setRole(Role.ROLE_USER);
                token.setUsername(dto.getUsername());
                token.setEmail(dto.getEmail());
                token.setPhoneNumber(dto.getPhoneNumber());
                token.setWallet(999999999999999999l);
                userRepository.saveAndFlush(token);
            } else {
                return false;
            }
            return true;


    }

    public boolean saveAdmin(UserRegistration dto) {



            UserEntity check = userRepo.getByUsername(dto.getUsername());
            if (check == null) {
                UserEntity token = new UserEntity();
                token.setUsername(dto.getUsername());
                token.setPassword(dto.getPassword());
                token.setRole(Role.ROLE_ADMIN);
                token.setUsername(dto.getUsername());
                token.setEmail(dto.getEmail());
                token.setPhoneNumber(dto.getPhoneNumber());
                token.setWallet(999999999999999999l);
                userRepository.saveAndFlush(token);
            } else {
                return false;
            }
            return true;


    }


    public LoginResponse login(LoginDTO token) {
        try {
            UserEntity check = userRepo.getByUsername(token.getUsername());
            if (token.getPassword().equals( check.getPassword())) {

                return new LoginResponse(token.getUsername(), check.getRole().toString());
            }
            throw new CredentialException("");
        } catch (CredentialException e) {
            return null;
        }
    }


}