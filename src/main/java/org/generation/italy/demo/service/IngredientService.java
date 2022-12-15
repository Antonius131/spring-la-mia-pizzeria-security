package org.generation.italy.demo.service;

import java.util.List;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.repository.IngredientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingRepo;
	
	
	public void saveIngredient(Ingredient ingredient) {
		
		ingRepo.save(ingredient);
	}
	
	public List<Ingredient> findAll() {
		
		return ingRepo.findAll();
	}
	
	public List<Ingredient> findAllWPizzas() {
		
		List<Ingredient> ingredients = ingRepo.findAll();
		
		for (Ingredient ing: ingredients) {
			
			Hibernate.initialize(ing.getPizzas());
		}
		
		return ingredients;
	}
	
	public Ingredient getIngredientById(int id) {
		
		return ingRepo.findById(id).get();
	}
	
	public void deleteIngredientById(int id) {
		
		ingRepo.deleteById(id);
	}
}
