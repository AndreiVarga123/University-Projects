package group.lab1.Model.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import group.lab1.Model.Beer;
import group.lab1.Model.BeerBrewery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeerBrewerySerializer extends StdSerializer<List<BeerBrewery>> {

    protected BeerBrewerySerializer(Class<List<BeerBrewery>> t) {
        super(t);
    }

    @Override
    public void serialize(List<BeerBrewery> beerBreweries, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Long> ids = new ArrayList<>();
        for (BeerBrewery beerBrewery : beerBreweries) {
            ids.add(beerBrewery.getId());
        }
        generator.writeObject(ids);
    }

}
