package com.example.Repos;

import com.example.Entities.CarEntity;
import com.example.Entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    UserEntity getByUsername(String username);

}