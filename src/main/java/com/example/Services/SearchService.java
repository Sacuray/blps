package com.example.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.DTO.SearchParametrs;
import com.example.DTO.ShortCar;
import com.example.Entities.CarEntity;
import com.example.Repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SearchService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    EntityManager entityManager;
    
    public List<CarEntity> search(SearchParametrs dto) {
        System.out.println("dto");
        System.out.println(dto.toString());
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarEntity> cq = cb.createQuery(CarEntity.class);

        Root<CarEntity> car = cq.from(CarEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dto.getModel() != null) {
            predicates.add(cb.like(car.get("model"), "%" + dto.getModel() + "%"));
        }
        if (dto.getAccident() != null) {
            predicates.add(cb.equal(car.get("accident"), dto.getAccident()));
        }
        if (dto.getColor() != null) {
            predicates.add(cb.like(car.get("colour"), "%" + dto.getColor() + "%"));
        }
        if (dto.getWheel() != null) {
            predicates.add(cb.like(car.get("wheel"), "%" + dto.getWheel() + "%"));
        }
        if (dto.getEngine() != null) {
            predicates.add(cb.like(car.get("engine"), "%" + dto.getEngine() + "%"));
        }
        if (dto.getMinPrice() != null || dto.getMaxPrice() != null) {
            predicates.add(cb.between(car.get("price"), Objects.requireNonNullElse(dto.getMinPrice(), 0L), Objects.requireNonNullElse(dto.getMaxPrice(), Long.MAX_VALUE)));
        }
        cq.where(cb.or(predicates.toArray(Predicate[]::new)));
        if (dto.getSorting() != null) {
            if (dto.getAsc()) {
                cq.orderBy(cb.asc(car.get(dto.getSorting())));
            } else {
                cq.orderBy(cb.desc(car.get(dto.getSorting())));
            }
        }
        System.out.println(predicates.toString());

        TypedQuery<CarEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
    }


    public List<ShortCar> carProfile(SearchParametrs searchParametrs) {
        try {
            List<ShortCar> shortCars = new ArrayList<>();
            
            List<CarEntity> cars = search(searchParametrs);
            System.out.println(cars.toString());
            for (CarEntity carEntity : cars) {
                ShortCar shortCarTemp = new ShortCar(carEntity.getAdNumber(), carEntity.getModel(), carEntity.getPrice(), carEntity.getYearOfRelease(), carEntity.getMileage());
                shortCars.add(shortCarTemp);
            }
            return shortCars;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
