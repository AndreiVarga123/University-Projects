package group.lab1.Repo;

import group.lab1.Model.BeerBrewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerBreweryRepo extends JpaRepository<BeerBrewery, Long> {
}
