package org.example.Repos;

import org.example.Entities.CarEntity;
import org.example.Entities.UserCarEntity;
import org.example.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepo extends JpaRepository<UserCarEntity, Long> {
    void deleteByCar_id(Long car_id);
}
