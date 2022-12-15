package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promo;
import org.generation.italy.demo.repository.PromoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromoService {

	@Autowired
	private PromoRepository promoRepo;
	
	public void save(Promo promo) {
		
		promoRepo.save(promo);
	}
	
	public List<Promo> findPromos() {
		
		return promoRepo.findAll();
	}
	
	public Optional<Promo> getPromoById(int id) {
		return promoRepo.findById(id);
	}
	
	public void deletePromoById(int id) {
		promoRepo.deleteById(id);
	}
	
	@Transactional
	public List<Promo> findPromosWPizza() {
		List<Promo> promosList = promoRepo.findAll();
		
		for (Promo pr : promosList) {
			Hibernate.initialize(pr.getPizzas());
		}
		
		return promosList;
		
	}
}
