package com.example.demo.Repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Entities.CarEntity;
import com.example.demo.DTO.SearchParametrs;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search {
    @Autowired
    EntityManager entityManager;

    public List<CarEntity> search(SearchParametrs dto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarEntity> cq = cb.createQuery(CarEntity.class);

        Root<CarEntity> car = cq.from(CarEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dto.getModel() != null) {
            predicates.add(cb.like(car.get("model"), "%" + dto.getModel() + "%"));
        }
        if (dto.getAccident() != null) {
            predicates.add(cb.like(car.get("accident"), "%" + dto.getAccident() + "%"));
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


        TypedQuery<CarEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}