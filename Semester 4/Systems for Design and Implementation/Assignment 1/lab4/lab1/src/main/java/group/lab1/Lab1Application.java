package group.lab1;

import group.lab1.Model.*;
import group.lab1.Repo.BeerBreweryRepo;
import group.lab1.Repo.BeerRepo;
import group.lab1.Repo.BreweryRepo;
import group.lab1.Repo.ProducerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Lab1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Autowired
	private BeerRepo beerRepo;

	@Autowired
	private BreweryRepo breweryRepo;

	@Autowired
	private ProducerRepo producerRepo;

	@Autowired
	private BeerBreweryRepo beerBreweryRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

//		Producer producer1 = new Producer("Tuborg","Denmark",1932,"Large producer in Denmark",130);
//		Producer producer2 = new Producer("Klausen","Romania",1980,"Small producer in Romania",1);
//		Producer producer3 = new Producer("Carlsberg","Denmark",1921,"Large producer in Denmark",120);
//		Producer producer4 = new Producer("Ursus","Romania",1963,"Large producer in Romania",115);
//		Producer producer5 = new Producer("Heineken","Netherlands",1965,"Large producer in the Netherlands",200);
//		Producer producer6 = new Producer("Peroni","Italy",1967,"Large producer in Italy",235);
//		Producer producer7 = new Producer("Bucur","Romania",1964,"Large producer in Romania",150);
//		Producer producer8 = new Producer("Carlsberg","Czechia",1962,"Large producer in Czechia",100);
//		Producer producer9 = new Producer("Budweiser","Czechia",1933,"Large producer in Czechia",250);
//		Producer producer10 = new Producer("Weihenstephan","Germany",1919,"Large producer in Germany",80);
//
//
//		producerRepo.save(producer1);
//		producerRepo.save(producer2);
//		producerRepo.save(producer3);
//		producerRepo.save(producer4);
//		producerRepo.save(producer5);
//		producerRepo.save(producer6);
//		producerRepo.save(producer7);
//		producerRepo.save(producer8);
//		producerRepo.save(producer9);
//		producerRepo.save(producer10);
//
//		Brewery brewery1 = new Brewery("Klausen brewery","Cluj-Napoca",1980,"Small brewery in Cluj","klausen.ro");
//		Brewery brewery2 = new Brewery("Tuborg Copenhaga brewery","Copenhaga",1999,"Large brewery in Copenhagen","tuborg_copenhaga.dk");
//		Brewery brewery3 = new Brewery("Carlsberg Odense brewery","Odense",2005,"Medium brewery in Odense","carlsberg_odense.dk");
//		Brewery brewery4 = new Brewery("Heineken Amsterdam brewery","Amsterdam",2002,"Huge brewery in Amsterdam","heineken_amsterdam.nl");
//		Brewery brewery5 = new Brewery("Heineken Rotterdam brewery","Rotterdam",2002,"Small brewery in Rotterdam","heineken_rotterdam.nl");
//		Brewery brewery6 = new Brewery("Ursus Galati brewery","Galati",1989,"Large brewery in Galati","ursus_galati.ro");
//		Brewery brewery7 = new Brewery("Ursus Suceava brewery","Suceava",1989,"Huge brewery in Suceava","ursus_suceava.ro");
//		Brewery brewery8 = new Brewery("Weihenstephan Munich brewery","Munich",2010,"Medium brewery in Munich","weihenstephan_munich.de");
//		Brewery brewery9 = new Brewery("Peroni Milan brewery","Milan",1981,"Small brewery in Milan","peroni_milan.it");
//		Brewery brewery10 = new Brewery("Budweiser Prague brewery","Prague",1983,"Large brewery in Prague","budweiser_prague.cz");
//
//		breweryRepo.save(brewery1);
//		breweryRepo.save(brewery2);
//		breweryRepo.save(brewery3);
//		breweryRepo.save(brewery4);
//		breweryRepo.save(brewery5);
//		breweryRepo.save(brewery6);
//		breweryRepo.save(brewery7);
//		breweryRepo.save(brewery8);
//		breweryRepo.save(brewery9);
//		breweryRepo.save(brewery10);
//
//		Beer beer1 = new Beer("Ursus Retro",producer4,"white",5,7, "can");
//		Beer beer2 = new Beer("Heineken Silver",producer5,"white",4,6, "bottle");
//		Beer beer3 = new Beer("Ursus Premium",producer4,"white",5,8, "bottle");
//		Beer beer4 = new Beer("Bucur Bruna",producer7,"black",3,10, "can");
//		Beer beer5 = new Beer("Indigen",producer2,"white",5,12, "bottle");
//		Beer beer6 = new Beer("Heineken Original",producer5,"white",5,6, "can");
//		Beer beer7 = new Beer("Weihenstephaer Hefe Weissbier",producer10,"white",4,8, "bottle");
//		Beer beer8 = new Beer("Weihenstephaer Hefe Weissbier Dunkel",producer10,"black",4,8, "can");
//		Beer beer9 = new Beer("Budweiser Budvar",producer9,"white",6,11, "bottle");
//		Beer beer10 = new Beer("Budweiser Budvar Dark",producer9,"black",6,11, "bottle");
//
//		beerRepo.save(beer1);
//		beerRepo.save(beer2);
//		beerRepo.save(beer3);
//		beerRepo.save(beer4);
//		beerRepo.save(beer5);
//		beerRepo.save(beer6);
//		beerRepo.save(beer7);
//		beerRepo.save(beer8);
//		beerRepo.save(beer9);
//		beerRepo.save(beer10);
//
//
//		BeerBrewery beerBrewery1 = new BeerBrewery(beer1,brewery6,100,true);
//		BeerBrewery beerBrewery2 = new BeerBrewery(beer2,brewery4,200,false);
//		BeerBrewery beerBrewery3 = new BeerBrewery(beer3,brewery6,500,false);
//		BeerBrewery beerBrewery4 = new BeerBrewery(beer1,brewery7,300,true);
//		BeerBrewery beerBrewery5 = new BeerBrewery(beer3,brewery7,700,false);
//		BeerBrewery beerBrewery6 = new BeerBrewery(beer6,brewery4,450,true);
//		BeerBrewery beerBrewery7 = new BeerBrewery(beer9,brewery10,900,true);
//		BeerBrewery beerBrewery8 = new BeerBrewery(beer10,brewery10,1000,true);
//		BeerBrewery beerBrewery9 = new BeerBrewery(beer2,brewery5,1200,false);
//		BeerBrewery beerBrewery10 = new BeerBrewery(beer6,brewery5,1500,true);
//
//		beerBreweryRepo.save(beerBrewery1);
//		beerBreweryRepo.save(beerBrewery2);
//		beerBreweryRepo.save(beerBrewery3);
//		beerBreweryRepo.save(beerBrewery4);
//		beerBreweryRepo.save(beerBrewery5);
//		beerBreweryRepo.save(beerBrewery6);
//		beerBreweryRepo.save(beerBrewery7);
//		beerBreweryRepo.save(beerBrewery8);
//		beerBreweryRepo.save(beerBrewery9);
//		beerBreweryRepo.save(beerBrewery10);
	}
}
