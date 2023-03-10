package group.lab1;

import group.lab1.Model.Beer;
import group.lab1.Repo.BeerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Lab1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@Autowired
	private BeerRepo repo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		Beer beer1 = new Beer("Ursus Retro","Ursus","white",5,7);
		Beer beer2 = new Beer("Heineken Silver","Heineken","white",4,6);
		Beer beer3 = new Beer("Ursus Premium","Ursus","white",5,8);
		Beer beer4 = new Beer("Bucur Bruna","Bucur","black",3,10);
		Beer beer5 = new Beer("Indigen","Klausen","white",5,12);

		repo.save(beer1);
		repo.save(beer2);
		repo.save(beer3);
		repo.save(beer4);
		repo.save(beer5);
	}
}
