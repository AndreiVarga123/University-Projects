package group.lab1.Service;

import group.lab1.Model.Beer;
import group.lab1.Repo.BeerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService implements IBeerService{
    private BeerRepo repo;

    public BeerService(BeerRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Beer> getAllBeers() {
        return repo.findAll();
    }

    @Override
    public Beer saveBeer(Beer beer) {
        return repo.save(beer);
    }

    @Override
    public Beer getBeerById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Beer updateBeer(Beer beer) {
        return repo.save(beer);
    }

    @Override
    public void deleteBeer(Long id) {
        repo.deleteById(id);
    }
}
