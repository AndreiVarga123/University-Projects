//package group.lab1.Controller;
//
//import group.lab1.Model.Beer;
//import group.lab1.Model.Brewery;
//import group.lab1.Service.BeerService;
//import group.lab1.Service.BreweryService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class BeerController {
//    private BeerService beerService;
//    private BreweryService breweryService;
//
//    public BeerController(BeerService beerService, BreweryService breweryService) {
//        this.beerService = beerService;
//        this.breweryService = breweryService;
//    }
//
//    @GetMapping("/beers")
//    public String listBeers(Model model){
//        model.addAttribute("beers",beerService.getAll());
//        model.addAttribute("breweries",breweryService.getAll());
//        model.addAttribute("filter","0");
//
//        return "beers";
//    }
//
//    @GetMapping("/beers/new/{id}")
//    public String createBeer(Model model,@PathVariable Long id){
//        Beer beer = new Beer();
//        Brewery brewery = breweryService.getById(id);
//        beer.setBrewery(brewery);
//
//        model.addAttribute("beer",beer);
//
//        return "create_beer";
//    }
//
//    @PostMapping("/beers")
//    public String saveBeer(@ModelAttribute("beer") Beer beer){
//        beerService.save(beer);
//
//        return "redirect:/beers";
//    }
//
//    @GetMapping("/beers/edit/{id}")
//    public String editBeer(@PathVariable Long id,Model model){
//        model.addAttribute("beer",beerService.getById(id));
//
//        return "edit_beer";
//    }
//
//    @PostMapping("/beers/{id}")
//    public String updateBeer(@PathVariable Long id, @ModelAttribute("Beer") Beer newBeer){
//        Beer beer = beerService.getById(id);
//        beer.setName(newBeer.getName());
//        beer.setProducer(newBeer.getProducer());
//        beer.setColor(newBeer.getColor());
//        beer.setAlcoholLvl(newBeer.getAlcoholLvl());
//        beer.setPrice(newBeer.getPrice());
//
//        beerService.update(beer);
//
//        return "redirect:/beers";
//    }
//
//    @GetMapping("/beers/{id}")
//    public String deleteBeeer(@PathVariable Long id){
//        beerService.delete(id);
//
//        return "redirect:/beers";
//    }
//
//    @GetMapping("/beers/breweries/new")
//    public String createBrewery(Model model){
//        Brewery brewery = new Brewery();
//        model.addAttribute("brewery",brewery);
//
//        return "create_brewery";
//    }
//
//    @PostMapping("/beers/breweries")
//    public String saveBrewery(@ModelAttribute("brewery") Brewery brewery){
//        breweryService.save(brewery);
//
//        return "redirect:/beers";
//    }
//
//    @GetMapping("/beers/breweries/edit/{id}")
//    public String editBrewery(@PathVariable Long id,Model model){
//        model.addAttribute("brewery",breweryService.getById(id));
//
//        return "edit_brewery";
//    }
//
//    @PostMapping("/beers/breweries/{id}")
//    public String updateBrewery(@PathVariable Long id,@ModelAttribute("brewery") Brewery newBrewery){
//        Brewery brewery = breweryService.getById(id);
//        brewery.setName(newBrewery.getName());
//        brewery.setLocation(newBrewery.getLocation());
//        brewery.setYear(newBrewery.getYear());
//        brewery.setDescr(newBrewery.getDescr());
//        brewery.setWebsite(newBrewery.getWebsite());
//
//        breweryService.update(brewery);
//
//        return "redirect:/beers";
//    }
//
//    @GetMapping("/beers/breweries/{id}")
//    public String deleteBrewery(@PathVariable Long id){
//        breweryService.delete(id);
//
//        return "redirect:/beers";
//    }
//
//    @PostMapping("/filter")
//    public String setFilter(@ModelAttribute("filter") String filter)
//    {
//        try {
//            Integer filterNr = Integer.valueOf(filter);
//            ((BeerService) beerService).setFilterNr(filterNr);
//        } catch (Exception e){
//            System.out.println("cannot convert");
//            ((BeerService) beerService).setFilterNr(0);
//        }finally {
//            return "redirect:/beers";
//        }
//    }
//}
