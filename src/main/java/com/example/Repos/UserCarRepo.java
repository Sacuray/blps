package com.example.Repos;

import com.example.Entities.UserCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepo extends JpaRepository<UserCarEntity, Long> {
    void deleteByCarId(Long carId);
    UserCarEntity findByCarId(Long carId);
}
