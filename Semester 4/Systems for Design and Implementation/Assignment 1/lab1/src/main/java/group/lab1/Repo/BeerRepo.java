package group.lab1.Repo;

import group.lab1.Model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepo extends JpaRepository<Beer,Long> {
}
