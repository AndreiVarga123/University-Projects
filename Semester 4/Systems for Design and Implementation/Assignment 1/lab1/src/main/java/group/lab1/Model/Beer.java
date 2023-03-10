package group.lab1.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Beers")
public class Beer {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "producer")
    private String producer;

    @Column(name = "color")
    private String color;

    @Column(name = "alcohol level")
    private Integer alcoholLvl;

    @Column(name = "price")
    private Integer price;

    public Beer() {
    }

    public Beer(String name, String producer, String color, Integer alcoholLvl, Integer price) {
        this.name = name;
        this.producer = producer;
        this.color = color;
        this.alcoholLvl = alcoholLvl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

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

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAlcoholLvl(Integer alcoholLvl) {
        this.alcoholLvl = alcoholLvl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj)
            return true;
        if( !(obj instanceof Beer))
            return false;
        Beer beer = (Beer) obj;
        return Objects.equals(this.id,beer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", color='" + color + '\'' +
                ", alcoholLvl=" + alcoholLvl +
                ", price=" + price +
                '}';
    }
}
