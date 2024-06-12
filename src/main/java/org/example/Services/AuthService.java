package org.example.Services;


import org.example.DTO.LoginDTO;
import org.example.DTO.LoginResponse;
import org.example.DTO.UserRegistration;
import org.example.Entities.UserEntity;
import org.example.Repos.UserRepo;
import org.example.security.JwtUtil;
import org.example.security.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final TransactionTemplate transactionTemplate;

    public AuthService(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil, UserRepo userRepo, PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public boolean saveUser(UserRegistration dto) {

        return transactionTemplate.execute(status -> {

            UserEntity check = userRepo.getByUsername(dto.getUsername());
            if (check == null) {
                UserEntity token = new UserEntity();
                token.setUsername(dto.getUsername());
                token.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
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
        });

    }

    public boolean saveAdmin(UserRegistration dto) {

        return transactionTemplate.execute(status -> {

            UserEntity check = userRepo.getByUsername(dto.getUsername());
            if (check == null) {
                UserEntity token = new UserEntity();
                token.setUsername(dto.getUsername());
                token.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
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
        });

    }


    public LoginResponse login(LoginDTO token) {
        try {
            UserEntity check = userRepo.getByUsername(token.getUsername());
            System.out.println(bCryptPasswordEncoder.matches(token.getPassword(), check.getPassword()));
            if (bCryptPasswordEncoder.matches(token.getPassword(), check.getPassword())) {
                System.out.println(token.getUsername());
                System.out.println(jwtUtil.generateToken(token.getUsername()).isEmpty());
                return new LoginResponse(token.getUsername(), jwtUtil.generateToken(token.getUsername()));
            }
            throw new CredentialException("");
        } catch (CredentialException e) {
            return null;
        }
    }


}