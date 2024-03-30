package com.example.demo.Repos;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entities.CarEntity;
import java.util.List;


public interface CarRepo extends JpaRepository<CarEntity, Long> {
    CarEntity findById(Integer id);
    CarEntity findByAdNumber(Long adNumber);
}
