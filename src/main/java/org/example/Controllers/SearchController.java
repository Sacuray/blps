package org.example.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.Services.CarProfileService;
import org.example.DTO.*;
import org.example.Services.SearchService;

@RestController
@RequestMapping("/catalog")
@CrossOrigin
public class SearchController {
    @Autowired
    CarProfileService carProfileService;
    @Autowired
    SearchService searchService;


    @PostMapping("/")
    public ResponseEntity getProfileInfo(@RequestBody SearchParametrs searchParametrs) {
        try {
            System.out.println("SEAAAAARCH");
            List<ShortCar> shortCars = searchService.carProfile(searchParametrs);
            System.out.println(shortCars.toString());
            return ResponseEntity.ok(shortCars);   
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.badRequest().body("Произошла ошибка во время выполнения запроса");
        }
    }
}
