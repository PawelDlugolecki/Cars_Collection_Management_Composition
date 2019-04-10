package dlugolecki.pawel.model;

import dlugolecki.pawel.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engine {

    private EngineType type;
    private BigDecimal power;
}
