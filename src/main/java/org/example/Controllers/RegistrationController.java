package org.example.Controllers;


import org.example.DTO.LoginDTO;
import org.example.DTO.LoginResponse;
import org.example.DTO.UserRegistration;
import org.example.Entities.UserEntity;
import org.example.Repos.UserRepo;
import org.example.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;


    @Autowired
    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthService authService) {

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }


    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> register(@RequestBody UserRegistration dto){
        System.out.println("dsvf");
        boolean success = authService.saveUser(dto);
        if (success){
            return new ResponseEntity<>("User have been registered successfully.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Such user already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerAdmin")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> registerAdmin(@RequestBody UserRegistration dto){
        System.out.println("dsvf");
        boolean success = authService.saveAdmin(dto);
        if (success){
            return new ResponseEntity<>("User have been registered successfully.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Such user already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto){
        LoginResponse success = authService.login(dto);
        if (success!=null){
            return new ResponseEntity<>(success.getJwt(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Wrong credentials.", HttpStatus.BAD_REQUEST);
        }
    }
}
