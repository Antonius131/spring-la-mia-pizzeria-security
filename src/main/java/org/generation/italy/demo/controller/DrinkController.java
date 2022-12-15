package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.service.DrinkService;
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
@RequestMapping("/drinks")
public class DrinkController {

	@Autowired
	private DrinkService drinkService;
	
	@GetMapping
	public String findDrinks(Model model) {
		
		List <Drink> drinks = drinkService.findAll(); 
		
		model.addAttribute("drinks", drinks);
		return "drink-home";
	}
	
	@GetMapping("/create")
	public String createDrink(Model model) {
		
		Drink drink = new Drink(); 
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	
	@PostMapping("/create")
	public String storeDrink(@Valid Drink drink,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
				
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/create";
		}
		
		drinkService.saveDrinks(drink);
		return "redirect:/drinks";
	}
	
	@GetMapping("/edit/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.getDrinkById(id);
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "edit-drink";
	}
	
	@PostMapping("/edit")
	public String updatePizza(@Valid Drink drink,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/edit" + drink.getId();
		}
	
		drinkService.saveDrinks(drink);
		return "redirect:/drinks";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id, Model model) {
		
		drinkService.deleteDrinkById(id);
		return "redirect:/drinks";
	}
	
	@GetMapping("/search")
	public String searchDrink(Model model,
			@RequestParam(name = "q", required = false) String query) {
		
		List<Drink> drinks = query == null
				? drinkService.findAll()
				: drinkService.findByName(query);
		
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "search-drink";
	}
}
