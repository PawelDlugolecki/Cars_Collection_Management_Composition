package dlugolecki.pawel.json.impl;

import dlugolecki.pawel.json.JsonConverter;
import dlugolecki.pawel.model.Car;

import java.util.List;

public class CarsConverter extends JsonConverter<List<Car>> {

    public CarsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
