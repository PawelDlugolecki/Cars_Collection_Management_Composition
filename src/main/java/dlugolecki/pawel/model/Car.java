package dlugolecki.pawel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    private String model;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheels;

    @Override
    public String toString() {
        return model + " " +
                price + " " +
                mileage + " " +
                engine + " " +
                carBody + " " +
                wheels;
    }
}
