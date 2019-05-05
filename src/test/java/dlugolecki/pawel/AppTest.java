package dlugolecki.pawel;

import dlugolecki.pawel.exceptions.ExceptionCode;
import dlugolecki.pawel.exceptions.MyException;
import dlugolecki.pawel.model.Car;
import dlugolecki.pawel.model.CarBody;
import dlugolecki.pawel.model.Engine;
import dlugolecki.pawel.model.Wheel;
import dlugolecki.pawel.model.enums.*;
import dlugolecki.pawel.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppTest {
    private CarService carService;

    @BeforeEach
    public void beforeEach() {
        carService = new CarService("cars2test");
    }

    @Test
    @DisplayName("Check if sort by components size - desc")
    public void test1() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.COMPONONENTS, true);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("AUDI", "MERCEDES", "BMW"));
    }

    @Test
    @DisplayName("Check if sort by components size - asc")
    public void test2() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.COMPONONENTS, false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("BMW", "MERCEDES", "AUDI"));
    }

    @Test
    @DisplayName("Check if sort by tyre_size size - desc")
    public void test3() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.TYRE_SIZE, true);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("BMW", "AUDI", "MERCEDES"));
    }

    @Test
    @DisplayName("Check if sort by tyre_size size - asc")
    public void test4() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.TYRE_SIZE, false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("MERCEDES", "AUDI", "BMW"));
    }

    @Test
    @DisplayName("Check if sort by type size - desc")
    public void test5() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.ENGINE_POWER, true);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("BMW", "MERCEDES", "AUDI"));
    }

    @Test
    @DisplayName("Check if sort by tyre_size size - asc")
    public void test6() {

        // WHEN
        List<Car> cars = carService.sort(SortingType.ENGINE_POWER, false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertLinesMatch(models, Arrays.asList("AUDI", "MERCEDES", "BMW"));
    }

    @Test
    @DisplayName("Check cars with tyre_type and with price between correct price range")
    public void test7() {

        // WHEN
        final BigDecimal minPrice = new BigDecimal(350000);
        final BigDecimal maxPrice = new BigDecimal(600000);

        List<Car> cars = carService.carsWithSpecifiedBodyTypeAndPriceBetween(CarBodyType.HATCHBACK, minPrice, maxPrice);
        List<String> model = cars.stream().map(Car::getModel).collect(Collectors.toList());

        // THEN
        Assertions.assertEquals(1, cars.size());
    }

    @Test
    @DisplayName("Check cars with tyre_type and with price between incorrect price range")
    public void test8() {

        // WHEN
        final BigDecimal minPrice = new BigDecimal(600000);
        final BigDecimal maxPrice = new BigDecimal(350000);

        MyException e = Assertions.assertThrows(
                MyException.class,
                () -> carService.carsWithSpecifiedBodyTypeAndPriceBetween(CarBodyType.HATCHBACK, minPrice, maxPrice));
        System.out.println(e.getExceptionInfo().getMessage());
        Assertions.assertEquals(ExceptionCode.INPUT_DATA, e.getExceptionInfo().getExceptionCode());
        Assertions.assertEquals("Min price can not be greater than the max price", e.getExceptionInfo().getMessage());
    }

    @Test
    @DisplayName("Check cars sorted by tyre_type")
    public void test9() {

        //WHEN
        Map<TyreType, List<Car>> cars = carService.sortedCarsForTyreType();

        Car car1 = Car.builder()
                .model("AUDI")
                .price(new BigDecimal(500000))
                .mileage(12000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(210))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.BLACK)
                        .type(CarBodyType.HATCHBACK)
                        .components(Arrays.asList("ABS", "AIR CONDITIONING", "NITRO", "RADIO"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.SUMMER)
                        .model("DEBICA")
                        .size(20)
                        .build())
                .build();

        Car car2 = Car.builder()
                .model("BMW")
                .price(new BigDecimal(600000))
                .mileage(14000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(240))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.RED)
                        .type(CarBodyType.SEDAN)
                        .components(Arrays.asList("ABS", "RADIO"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.WINTER)
                        .model("STEEL")
                        .size(22)
                        .build())
                .build();

        Car car3 = Car.builder()
                .model("MERCEDES")
                .price(new BigDecimal(350000))
                .mileage(10000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(215))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.SILVER)
                        .type(CarBodyType.SEDAN)
                        .components(Arrays.asList("ABS", "BLUETOOTH", "SPORT_PACKAGE"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.SUMMER)
                        .model("ALLOY")
                        .size(18)
                        .build())
                .build();

        Map<TyreType, List<Car>> expectedMap = new HashMap<>();

        expectedMap.put(TyreType.SUMMER, Arrays.asList(car1, car3));
        expectedMap.put(TyreType.WINTER, Arrays.asList(car2));

        // THEN
        Assertions.assertEquals(cars, expectedMap);
    }

    @Test
    @DisplayName("Check cars sorted by engine_type")
    public void test10() {

        List<Car> cars = carService.sortedCarModelForSpecifiedEngineType(EngineType.DIESEL);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());

        Assertions.assertEquals(models, Arrays.asList("AUDI", "BMW", "MERCEDES"));
    }

    @Test
    @DisplayName("Check cars sorted by mileage")
    public void test11() {

        Map<Car, Integer> cars = carService.carsWithMileage();

        Car car1 = Car.builder()
                .model("AUDI")
                .price(new BigDecimal(500000))
                .mileage(12000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(210))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.BLACK)
                        .type(CarBodyType.HATCHBACK)
                        .components(Arrays.asList("ABS", "AIR CONDITIONING", "NITRO", "RADIO"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.SUMMER)
                        .model("DEBICA")
                        .size(20)
                        .build())
                .build();

        Car car2 = Car.builder()
                .model("BMW")
                .price(new BigDecimal(600000))
                .mileage(14000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(240))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.RED)
                        .type(CarBodyType.SEDAN)
                        .components(Arrays.asList("ABS", "RADIO"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.WINTER)
                        .model("STEEL")
                        .size(22)
                        .build())
                .build();

        Car car3 = Car.builder()
                .model("MERCEDES")
                .price(new BigDecimal(350000))
                .mileage(10000)
                .engine(Engine
                        .builder()
                        .type(EngineType.DIESEL)
                        .power(new BigDecimal(215))
                        .build())
                .carBody(CarBody
                        .builder()
                        .colour(CarBodyColour.SILVER)
                        .type(CarBodyType.SEDAN)
                        .components(Arrays.asList("ABS", "BLUETOOTH", "SPORT_PACKAGE"))
                        .build())
                .wheels(Wheel
                        .builder()
                        .type(TyreType.SUMMER)
                        .model("ALLOY")
                        .size(18)
                        .build())
                .build();

        Map<Car, Integer> expectedMap = new HashMap<>();

        expectedMap.put(car1, 12000);
        expectedMap.put(car2, 14000);
        expectedMap.put(car3, 10000);

        Assertions.assertEquals(cars, expectedMap);
    }
}

