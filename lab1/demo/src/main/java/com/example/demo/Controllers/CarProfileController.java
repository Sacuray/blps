package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CarProfile;
import com.example.demo.Services.CarProfileService;

@RestController
@RequestMapping("/carprofile")
@CrossOrigin
public class CarProfileController {
    @Autowired
    CarProfileService carProfileService;


    @GetMapping("/{adNumber}")
    public ResponseEntity getProfileInfo(@PathVariable Long adNumber) {
        try {
            CarProfile data = carProfileService.carProfile(adNumber);
            if(data == null){
                return ResponseEntity.badRequest().body("Объявление под таким номером не найдено");
            }else{
                return ResponseEntity.ok(data);
            }
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка во время выполнения запроса");
        }
    }
    @GetMapping("/")
    public ResponseEntity lol() {
        return ResponseEntity.ok("data");
    }
}
