package org.example.Repos;

import org.example.Entities.CarEntity;
import org.example.Entities.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    UserEntity getByUsername(String username);

}