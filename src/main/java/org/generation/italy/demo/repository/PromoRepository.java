package org.generation.italy.demo.repository;

import org.generation.italy.demo.pojo.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {

}