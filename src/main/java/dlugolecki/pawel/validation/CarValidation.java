package dlugolecki.pawel.validation;

import dlugolecki.pawel.model.Car;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValidation {

    private Map<String, String> errors = new HashMap<>();

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Map<String, String> validate (Car car) {

        errors.clear();

        if (car == null) {
            errors.put("car", "object is null");
        }

        if (!isModelValid(car.getModel())) {
            errors.put("model", "model is not valid: " + car.getModel());
        }

        if (!isPriceValid(car.getPrice())) {
            errors.put("price", "price is not valid: " + car.getPrice());
        }

        if (!isMileageValid(car.getMileage())) {
            errors.put("mileage", "mileage is not valid: " + car.getMileage());
        }

        if (!isSizeValid(car.getWheels().getSize())) {
            errors.put("size", "size is not valid: " + car.getMileage());
        }

        if (!isComponentValid(car.getCarBody().getComponents())) {
            errors.put("equipment", "equipment is not valid: " + car.getCarBody().getComponents());
        }
        return errors;
    }

    private boolean isModelValid (String model) {
        return model != null && model.matches("[A-Z ]*");
    }

    private boolean isPriceValid(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isMileageValid(int mileage) {
        return mileage >= 0;
    }

    private boolean isSizeValid(int size) {
        return size >= 0;
    }

    private boolean isComponentValid(List<String> components) {
        return components == null || components.size() == 0 || !components.stream()
                .allMatch(component -> component.matches(""));
    }


}
