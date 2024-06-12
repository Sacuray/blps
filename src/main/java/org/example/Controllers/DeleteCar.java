package org.example.Controllers;

import org.example.Services.DeleteService;
import org.example.Services.PurchaseService;
import org.example.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.Services.CarProfileService;
import org.example.DTO.CarProfile;

@RestController
@RequestMapping("/delete")
@CrossOrigin
public class DeleteCar {
    @Autowired
    DeleteService deleteService;
    private final JwtUtil jwt;

    public DeleteCar(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{adNumber}")
    public ResponseEntity deleteCar(@PathVariable Long adNumber) {
        boolean result = deleteService.delete(adNumber);
        if (result) {
            return ResponseEntity.ok("Success");
        }else{
            return new ResponseEntity<>("Wrong credentials.", HttpStatus.BAD_REQUEST);
        }
    }
}
