package group.lab1.Controller;

import group.lab1.Model.Beer;
import group.lab1.Service.BeerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BeerController {
    private BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/beers")
    public String listBeers(Model model){
        model.addAttribute("beers",beerService.getAllBeers());

        return "beers";
    }

    @GetMapping("/beers/new")
    public String createBeer(Model model){
        Beer beer = new Beer();
        model.addAttribute("beer",beer);

        return "create_beer";
    }

    @PostMapping("/beers")
    public String saveBeer(@ModelAttribute("beer") Beer beer){
        beerService.saveBeer(beer);

        return "redirect:/beers";
    }

    @GetMapping("/beers/edit/{id}")
    public String editBeer(@PathVariable Long id,Model model){
        model.addAttribute("beer",beerService.getBeerById(id));

        return "edit_beer";
    }

    @PostMapping("/beers/{id}")
    public String updateBeer(@PathVariable Long id, @ModelAttribute("Beer") Beer newBeer, Model model){
        Beer beer = beerService.getBeerById(id);
        beer.setName(newBeer.getName());
        beer.setProducer(newBeer.getProducer());
        beer.setColor(newBeer.getColor());
        beer.setAlcoholLvl(newBeer.getAlcoholLvl());
        beer.setPrice(newBeer.getPrice());

        beerService.updateBeer(beer);

        return "redirect:/beers";
    }

    @GetMapping("/beers/{id}")
    public String deleteBeeer(@PathVariable Long id){
        beerService.deleteBeer(id);
        return "redirect:/beers";
    }
}
