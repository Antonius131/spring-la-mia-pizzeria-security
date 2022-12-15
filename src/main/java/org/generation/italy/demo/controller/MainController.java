package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promo;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired 
	private PromoService promoService;
	
	@Autowired 
	private IngredientService ingService;
	
	@GetMapping("/")
	public String getPizzas(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("pizzas", pizzas);
		
		return "home";
	}
	
	@GetMapping("/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promo> promos = promoService.findPromos();
		model.addAttribute("promos", promos);
		
		List<Ingredient> ingredients = ingService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "create";
	}
	
	@PostMapping("/create")
	public String storePizza(@Valid Pizza pizza,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
				
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/create";
		}
		
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "edit";
	}
	
	@PostMapping("/edit")
	public String updatePizza(@Valid Pizza pizza,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/edit/" + pizza.getId();
		}
	
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		pizzaService.deletePizzaById(id);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/search")
	public String searchPizza(Model model,
			@RequestParam(name = "q", required = false) String query) {
		
		List<Pizza> pizzas = query == null
				? pizzaService.findAll()
				: pizzaService.findByName(query);
				
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("query", query);
		
		return "search";
	}
}