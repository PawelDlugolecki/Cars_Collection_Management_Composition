package dlugolecki.pawel.model;

import dlugolecki.pawel.model.enums.CarBodyColour;
import dlugolecki.pawel.model.enums.CarBodyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarBody {

    private CarBodyColour colour;
    private CarBodyType type;
    private List<String> components;
}
