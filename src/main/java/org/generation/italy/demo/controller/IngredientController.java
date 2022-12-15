package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredients(Model model) {
		
		List<Ingredient> ingredients = ingService.findAllWPizzas();
		model.addAttribute("ingredients", ingredients);
		
		return "ingredients";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "create-ingredient";
	}
	
	@PostMapping("/create")
	public String storeIngredient(@Valid Ingredient ingredient) {
		
		List<Pizza> pizzaIng = ingredient.getPizzas();
		
		for (Pizza pizza : pizzaIng) {
			
			pizza.getIngredients().add(ingredient);
		}
		
		ingService.saveIngredient(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@GetMapping("/edit/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model) {
		
		Ingredient ingredient = ingService.getIngredientById(id);
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "edit-ingredient";
	}
	
	@PostMapping("/edit/{id}")
	public String updateIngredient(
			@PathVariable("id") int id,
			@Valid Ingredient ingredient
	) {
		
		Ingredient ing = ingService.getIngredientById(id);
		
		for (Pizza pizza : ing.getPizzas()) {
			
			pizza.removeIngredients(ing);
		}
		
		for (Pizza pizza : ingredient.getPizzas()) {
			
			pizza.addIngredients(ingredient);
		}
		
		ingService.saveIngredient(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {
		
		Ingredient ing = ingService.getIngredientById(id);
		
		for (Pizza pizza : ing.getPizzas()) {
			
			pizza.removeIngredients(ing);
		}
		
		ingService.deleteIngredientById(id);
		
		return "redirect:/ingredients";
	}
	
}