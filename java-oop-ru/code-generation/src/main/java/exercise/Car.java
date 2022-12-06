package exercise;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@AllArgsConstructor()
@Getter
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    private static final ObjectMapper mapper = new ObjectMapper();

    // BEGIN
    public String serialize() throws JsonProcessingException {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(this);
    }

    public static Car unserialize(String jsonCar) throws IOException {
        return mapper.readValue(jsonCar, Car.class);
    }
    // END
}
