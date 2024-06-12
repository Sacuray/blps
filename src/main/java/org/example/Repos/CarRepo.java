package org.example.Repos;


import org.example.Entities.CarEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarRepo extends JpaRepository<CarEntity, Long> {
    CarEntity findById(Integer id);
    CarEntity findByAdNumber(Long adNumber);
    CarEntity findFirstByOrderByPriceAsc();
}


