package dlugolecki.pawel.model;
import dlugolecki.pawel.model.enums.TyreType;
import lombok.Data;

@Data
public class Wheel {

    private String model;
    private int size;
    private TyreType type;
}
