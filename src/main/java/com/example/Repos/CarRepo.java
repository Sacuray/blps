package com.example.Repos;


import com.example.Entities.CarEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CarRepo extends JpaRepository<CarEntity, Long> {
    CarEntity findById(Integer id);
    CarEntity findByAdNumber(Long adNumber);
    CarEntity findFirstByOrderByPriceAsc();
}


