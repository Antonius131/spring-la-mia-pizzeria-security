package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promo;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private PromoService promoService;
	@Autowired
	private IngredientService ingService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		// creating and adding ingredients in Lists
		Ingredient ing1 = new Ingredient("basilico");
		Ingredient ing2 = new Ingredient("pomodoro");
		Ingredient ing3 = new Ingredient("mozzarella");
		Ingredient ing4 = new Ingredient("olio");
		
		ingService.saveIngredient(ing1);
		ingService.saveIngredient(ing2);
		ingService.saveIngredient(ing3);
		ingService.saveIngredient(ing4);
		
		List<Ingredient> ingList1 = new ArrayList<>();
		ingList1.add(ing1);
		ingList1.add(ing2);
		ingList1.add(ing3);
		
		List<Ingredient> ingList2 = new ArrayList<>();
		ingList2.add(ing1);
		ingList2.add(ing2);
		
		List<Ingredient> ingList3 = new ArrayList<>();
		ingList3.add(ing1);
		ingList3.add(ing4);
		
		
		//creating new promos
		Promo pr1 = new Promo("Christmas Promo", LocalDate.now(), LocalDate.now().plusDays(15));
		Promo pr2 = new Promo("Halloween Promo", LocalDate.now(), LocalDate.now().plusDays(21));
		
		promoService.save(pr1);
		promoService.save(pr2);
		
		
		//creating pizzas with promos and ingredients
		Pizza p1 = new Pizza("Margherita", "buona", 10, null, ingList1);
		Pizza p2 = new Pizza("Diavola", "buonissima", 8, pr2, ingList2);
		Pizza p3 = new Pizza("Capricciosa", "non buona", 4, null, null);
		Pizza p4 = new Pizza("4 formaggi", "wow che buona", 8, pr2, ingList3);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		
		
		//creating drinks
		Drink d1 = new Drink("Gin tonic", "e vabbè..", 4);
		Drink d2 = new Drink("Gin lemon", "drink dei bimbi", 2);
		Drink d3 = new Drink("Negroni sbagliato", "e sì..", 5);
		
		drinkService.saveDrinks(d1);
		drinkService.saveDrinks(d2);
		drinkService.saveDrinks(d3);
	}
}











