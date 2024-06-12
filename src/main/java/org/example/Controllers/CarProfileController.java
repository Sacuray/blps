package org.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.Services.CarProfileService;
import org.example.DTO.CarProfile;


@RestController
@RequestMapping("/carprofile")
@CrossOrigin
public class CarProfileController {
    @Autowired
    CarProfileService carProfileService;

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{adNumber}")
    @CrossOrigin
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/")
    public ResponseEntity lol() {
        return ResponseEntity.ok("data");
    }
}
