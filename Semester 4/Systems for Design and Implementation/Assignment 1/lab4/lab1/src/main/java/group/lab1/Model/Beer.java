package group.lab1.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import javax.swing.text.View;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Beers")
public class Beer {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @JsonIgnoreProperties("beers")
    private Producer producer;

    @Column(name = "color")
    private String color;

    @Column(name = "alcohol_level")
    private Integer alcoholLvl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "packaging")
    private String packaging;

    @OneToMany(mappedBy = "beer")
    @JsonIgnoreProperties("beer")
    private List<BeerBrewery> beerBreweries;

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer=" + producer.getName() +
                ", color='" + color + '\'' +
                ", alcoholLvl=" + alcoholLvl +
                ", price=" + price +
                ", packaging='" + packaging + '\'' +
                '}';
    }

    //    @ManyToOne
//    @JoinColumn(name="brewery_id")
//    private Brewery brewery;

    public Beer() {
    }

    public Beer(String name, Producer producer, String color, Integer alcoholLvl, Integer price, String packaging) {
        this.name = name;
        this.producer = producer;
        this.color = color;
        this.alcoholLvl = alcoholLvl;
        this.price = price;
//        this.brewery = brewery;
        this.packaging = packaging;
    }

//    public Brewery getBrewery() {
//        return brewery;
//    }
//
//    public void setBrewery(Brewery brewery) {
//        this.brewery = brewery;
//    }

    public List<BeerBrewery> getBeerBreweries() {
        return beerBreweries;
    }

    public void setBeerBreweries(List<BeerBrewery> beerBreweries) {
        this.beerBreweries = beerBreweries;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String getProducer() {
//        return producer;
//    }

    public String getColor() {
        return color;
    }

    public Integer getAlcoholLvl() {
        return alcoholLvl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setProducer(String producer) {
//        this.producer = producer;
//    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAlcoholLvl(Integer alcoholLvl) {
        this.alcoholLvl = alcoholLvl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
