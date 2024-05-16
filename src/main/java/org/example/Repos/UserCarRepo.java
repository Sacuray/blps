package org.example.Repos;

import org.example.Entities.UserCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepo extends JpaRepository<UserCarEntity, Long> {
    void deleteByCarId(Long carId);
}
