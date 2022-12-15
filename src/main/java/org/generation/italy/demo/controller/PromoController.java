package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promo;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promos")
public class PromoController {

	@Autowired
	private PromoService promoService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getPromos(Model model) {
		
		List<Promo> promos = promoService.findPromos();
		model.addAttribute("promos", promos);
		
		return "promos";
	}
	
	@GetMapping("/create")
	public String createPromo(Model model) {
		
		Promo promo = new Promo();
		model.addAttribute("promo", promo);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "create-promo";
	}
	
	@PostMapping("/store")
	public String storePromo(@Valid Promo promo) {
		
		List<Pizza> pizzaPromo = promo.getPizzas();
		
		for (Pizza pizza : pizzaPromo) {
			
			pizza.setPromo(promo);
		}
		
		promoService.save(promo);
		
		return "redirect:/promos";
	}
}
