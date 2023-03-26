package group.lab1.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Producers")
public class Producer {

    public Producer() {
    }

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @Column(name="founding_year")
    private Integer founding_year;

    @Column(name="description")
    private String descr;

    @Column(name="nr_of_breweries")
    private Integer nrOfBreweries;

    @OneToMany(mappedBy = "producer",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("producer")
    private List<Beer> beers;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    public Producer(String name, String country, Integer founding_year, String descr, Integer nrOfBreweries) {
        this.name = name;
        this.country = country;
        this.founding_year = founding_year;
        this.descr = descr;
        this.nrOfBreweries = nrOfBreweries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFounding_year() {
        return founding_year;
    }

    public void setFounding_year(Integer founding_year) {
        this.founding_year = founding_year;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getNrOfBreweries() {
        return nrOfBreweries;
    }

    public void setNrOfBreweries(Integer nrOfBreweries) {
        this.nrOfBreweries = nrOfBreweries;
    }
}
