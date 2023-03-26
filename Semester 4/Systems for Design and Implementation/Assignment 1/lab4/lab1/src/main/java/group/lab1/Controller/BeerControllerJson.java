package group.lab1.Controller;

import group.lab1.Model.*;
import group.lab1.Service.BeerBreweryService;
import group.lab1.Service.BeerService;
import group.lab1.Service.BreweryService;
import group.lab1.Service.ProducerService;
import group.lab1.Validator.BeerValidator;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class BeerControllerJson {
    private BeerService beerService;
    private BreweryService breweryService;
    private ProducerService producerService;
    private BeerBreweryService beerBreweryService;
    private BeerValidator beerValidator;

    public BeerControllerJson(BeerService beerService, BreweryService breweryService, ProducerService producerService, BeerBreweryService beerBreweryService) {
        this.beerService = beerService;
        this.breweryService = breweryService;
        this.producerService = producerService;
        this.beerBreweryService = beerBreweryService;
        this.beerValidator = new BeerValidator();
    }

    @GetMapping("/beers")
    public List<Long> listBeers(){
        return beerService.getAll();
    }

    @GetMapping("/beers/{id}")
    public Beer listBeer(@PathVariable Long id){
        return beerService.getById(id);
    }

    @PostMapping("/beers")
    public Object addBeer( @RequestBody Beer beer){
        Errors errors = new BeanPropertyBindingResult(beer,"beer");
        beerValidator.validate(beer,errors);
        if(errors.hasErrors())
            return errors.getAllErrors().stream().map(e->e.getCode()).collect(Collectors.toList());
        return beerService.save(beer);
    }

    @PutMapping("/beers/{id}")
    public Object updateBeer(@RequestBody Beer beer, @PathVariable Long id){
        Errors errors = new BeanPropertyBindingResult(beer,"beer");
        beerValidator.validate(beer,errors);
        if(errors.hasErrors())
            return errors.getAllErrors().stream().map(e->e.getCode()).collect(Collectors.toList());

        Beer oldBeer = beerService.getById(id);
        oldBeer.setName(beer.getName());
        oldBeer.setColor(beer.getColor());
        oldBeer.setAlcoholLvl(beer.getAlcoholLvl());
        oldBeer.setPrice(beer.getPrice());
        oldBeer.setPackaging(beer.getPackaging());

        return beerService.save(oldBeer);
    }

    @DeleteMapping("/beers/{id}")
    public void deleteBeer(@PathVariable Long id){
        beerService.delete(id);
    }

    @GetMapping("/breweries")
    public List<Long> listBreweries(){
        return breweryService.getAll();
    }

    @GetMapping("/breweries/{id}")
    public Brewery listBrewery(@PathVariable Long id){
        return breweryService.getById(id);
    }

    @PostMapping("/breweries")
    public Brewery addBrewery(@RequestBody Brewery brewery){
        return breweryService.save(brewery);
    }

    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@RequestBody Brewery brewery, @PathVariable Long id){
        Brewery oldBrewery = breweryService.getById(id);
        oldBrewery.setName(brewery.getName());
        oldBrewery.setLocation(brewery.getLocation());
        oldBrewery.setDescr(brewery.getDescr());
        oldBrewery.setYear(brewery.getYear());
        oldBrewery.setWebsite(brewery.getWebsite());

        return breweryService.save(oldBrewery);
    }

    @DeleteMapping("/brewery/{id}")
    public void deleteBrewery(@PathVariable Long id){
        breweryService.delete(id);
    }

    @GetMapping("/beers/filter")
    public List<Beer> filter(@RequestBody Integer nr){
        return beerService.filter(nr);
    }

    @GetMapping("/producers")
    public List<Long> listProducers(){
        return producerService.getAll();
    }

    @GetMapping("/producers/{id}")
    public Producer listProducer(@PathVariable Long id){
        return producerService.getById(id);
    }

    @PostMapping("/producers")
    public Producer addProducer(@RequestBody Producer producer){
        return producerService.save(producer);
    }

    @PutMapping("/producers/{id}")
    public Producer updateProducer(@RequestBody Producer producer, @PathVariable Long id){
        Producer oldProducer = producerService.getById(id);
        oldProducer.setName(producer.getName());
        oldProducer.setCountry(producer.getCountry());
        oldProducer.setDescr(producer.getDescr());
        oldProducer.setFounding_year(producer.getFounding_year());
        oldProducer.setNrOfBreweries(producer.getNrOfBreweries());

        return producerService.save(oldProducer);
    }

    @DeleteMapping("/producers/{id}")
    public void deleteProducer(@PathVariable Long id){
        producerService.delete(id);
    }

    @GetMapping("/beer_breweries")
    public List<Long> listBeerBreweries(){
        return beerBreweryService.getAll();
    }

    @GetMapping("/beer_breweries/{id}")
    public BeerBrewery listBeerBrewery(@PathVariable Long id){
        return beerBreweryService.getById(id);
    }

    @PostMapping("/beer_breweries")
    public BeerBrewery addBrewery(@RequestBody BeerBrewery beerBrewery){
        return beerBreweryService.save(beerBrewery);
    }

    @PutMapping("/beer_breweries/{id}")
    public BeerBrewery updateBrewery(@RequestBody BeerBrewery beerBrewery, @PathVariable Long id){
        BeerBrewery oldBeerBrewery = beerBreweryService.getById(id);
        oldBeerBrewery.setQuantity(beerBrewery.getQuantity());
        oldBeerBrewery.setTested(beerBrewery.getTested());

        return beerBreweryService.save(oldBeerBrewery);
    }

    @DeleteMapping("/beer_breweries/{id}")
    public void deleteBeerBrewery(@PathVariable Long id){
        beerBreweryService.delete(id);
    }

    @GetMapping("/beers/stats")
    @ResponseBody
    public List<BeerDTO> getStats(){
       return beerService.getStatsByFoundingYear();
    }

    @GetMapping("/beers/stats2")
    @ResponseBody
    public List<BeerDTO> getStats2(){
        return beerService.getStatsByBreweryNr();
    }
}
