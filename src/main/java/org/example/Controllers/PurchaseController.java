package org.example.Controllers;

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
@RequestMapping("/purchase")
@CrossOrigin
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    private final JwtUtil jwt;

    public PurchaseController(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{adNumber}")
    public ResponseEntity purchase(@RequestHeader("Authorization") String auth, @PathVariable Long adNumber) {
        String username = jwt.subjectFromToken(auth.substring(7));
        boolean result = purchaseService.purchaseCar(username, adNumber);
        if (result) {
            return ResponseEntity.ok("Success");
        }else{
            return new ResponseEntity<>("Wrong credentials.", HttpStatus.BAD_REQUEST);
        }
    }
}
