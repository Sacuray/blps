package main.java.com.example.Repos;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<CarEntity, Long> {
    CarEntity findById(Integer id);

}
