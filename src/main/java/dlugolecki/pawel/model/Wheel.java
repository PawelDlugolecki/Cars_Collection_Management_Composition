package dlugolecki.pawel.model;
import dlugolecki.pawel.model.enums.TyreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wheel {

    private String model;
    private int size;
    private TyreType type;
}
