package group.lab1.Model.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import group.lab1.Model.Beer;
import group.lab1.Model.Producer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProducerSerializer extends StdSerializer<List<Producer>> {

    protected ProducerSerializer(Class<List<Producer>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Producer> producers, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Long> ids = new ArrayList<>();
        for (Producer producer : producers) {
            ids.add(producer.getId());
        }
        generator.writeObject(ids);
    }

}
