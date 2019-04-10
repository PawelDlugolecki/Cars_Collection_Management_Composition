package dlugolecki.pawel.service;

import dlugolecki.pawel.exceptions.ExceptionCode;
import dlugolecki.pawel.exceptions.MyException;
import dlugolecki.pawel.json.impl.CarsConverter;
import dlugolecki.pawel.model.Car;
import dlugolecki.pawel.model.enums.CarBodyType;
import dlugolecki.pawel.model.enums.EngineType;
import dlugolecki.pawel.model.enums.SortingType;
import dlugolecki.pawel.model.enums.TyreType;
import lombok.Data;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class CarService {
    private final List<Car> cars;

    CarService(String filename) {
        this.cars = getCarsFromJson(filename);

    }

    private List<Car> getCarsFromJson(String filename) {
        return new CarsConverter("src/main/resources/" + filename + ".json")
                .fromJson()
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "CAR SERVICE FROM JSON EXCEPTION"));
    }

    List<Car> sort(SortingType type, boolean descending) {

        Stream<Car> carStream = null;

        switch (type) {
            case COMPONONENTS:
                carStream = cars.stream().sorted(Comparator.comparing(car -> car.getCarBody().getComponents().size()));
                break;
            case TYRE_SIZE:
                carStream = cars.stream().sorted(Comparator.comparing(car -> car.getWheels().getSize()));
                break;
            case ENGINE_POWER:
                carStream = cars.stream().sorted(Comparator.comparing(car -> car.getEngine().getPower()));
                break;
        }

        List<Car> sortedCars = carStream.collect(Collectors.toList());
        if (descending) {
            Collections.reverse(sortedCars);
        }

        return sortedCars;
    }

    List<Car> carsWithSpecifiedBodyTypeAndPriceBetween(CarBodyType type, BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice.compareTo(maxPrice) >= 0) {
            throw new MyException(ExceptionCode.INPUT_DATA, "Min price can not be greater than the max price");
        }
        return cars
                .stream()
                .filter(car -> car.getCarBody().getType().equals(type))
                .filter(car -> car.getPrice().compareTo(minPrice) > 0 && car.getPrice().compareTo(maxPrice) < 0)
                .collect(Collectors.toList());
    }

    List<Car> sortedCarModelForSpecifiedEngineType(EngineType engineType) {
        return cars
                .stream()
                .filter(e -> e.getEngine().getType().equals(engineType))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    void statistics() {
        System.out.println("- - - - PRICE - - - -");
        BigDecimalSummaryStatistics priceStatistics = cars
                .stream()
                .collect(Collectors2.summarizingBigDecimal(Car::getPrice));
        System.out.println("MAX = " + priceStatistics.getMax());
        System.out.println("MIN = " + priceStatistics.getMin());
        System.out.println("AVG = " + priceStatistics.getAverage());

        System.out.println("- - - - MILEAGE - - - - ");
        DoubleSummaryStatistics mileageStatistics = cars
                .stream()
                .collect(Collectors.summarizingDouble(Car::getMileage));
        System.out.println("MAX = " + mileageStatistics.getMax());
        System.out.println("MIN = " + mileageStatistics.getMin());
        System.out.println("AVG = " + mileageStatistics.getAverage());

        System.out.println("- - - - POWER_ENGINE - - - -");
        BigDecimalSummaryStatistics powerStatistics = cars
                .stream()
                .collect(Collectors2.summarizingBigDecimal(car -> car.getEngine().getPower()));
        System.out.println("MAX = " + powerStatistics.getMax());
        System.out.println("MIN = " + powerStatistics.getMin());
        System.out.println("AVG = " + powerStatistics.getAverage());
    }

    Map<Car, Integer> carsWithMileage() {
        return cars
                .stream()
                .sorted(Comparator.comparing(Car::getMileage).reversed())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Car::getMileage,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    Map<TyreType, List<Car>> sortedCarsForTyreType() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(
                        t -> t.getWheels().getType()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(tyre -> tyre.getValue().size(), Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        k -> k.getKey(),
                        k -> k.getValue(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    List<Car> carsWithSpecifiedComponent(List<String> components) {
        return cars
                .stream()
                .filter(car -> car.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel).reversed())
                .collect(Collectors.toList());
    }
}
