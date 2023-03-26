package group.lab1.Repo;

import group.lab1.Model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepo extends JpaRepository<Producer,Long> {
}
