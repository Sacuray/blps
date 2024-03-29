package com.example.Repos;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.CarEntity;

public interface CarRepo extends JpaRepository<CarEntity, Long> {
    CarEntity findById(Integer id);

}
