package group.lab1.Repo;

import group.lab1.Model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepo extends JpaRepository<Brewery,Long> {
}
