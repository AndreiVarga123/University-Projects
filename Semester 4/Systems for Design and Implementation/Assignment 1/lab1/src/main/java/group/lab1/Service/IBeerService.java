package group.lab1.Service;

import group.lab1.Model.Beer;

import java.util.List;

public interface IBeerService {
    List<Beer> getAllBeers();

    Beer saveBeer(Beer beer);

    Beer getBeerById(Long id);

    Beer updateBeer(Beer beer);

    void deleteBeer(Long id);
}
