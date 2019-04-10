package dlugolecki.pawel.service;
import dlugolecki.pawel.exceptions.MyException;

public class MenuService {

    private final CarService carService;
    private final UserDataService userDataService;

    public MenuService(String filename) {
        carService = new CarService(filename);
        userDataService = new UserDataService();
    }

    public void menu() {

        while (true) {
            try {
                printMenu();
                int fromUser = userDataService.getInt("Choose option");

                switch (fromUser) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
                        break;
                    case 4:
                        option4();
                        break;
                    case 5:
                        option5();
                        break;
                    case 6:
                        option6();
                        break;
                    case 7:
                        option7();
                        break;
                    case 8:
                        option8();
                        break;
                    case 9:
                        option9();
                        break;
                }

            } catch (MyException e) {
                System.out.println("\n****************************** EXCEPTIONS *************************************");
                System.out.println(e.getExceptionInfo().getMessage());
                System.out.println(e.getExceptionInfo().getExceptionCode());
                System.out.println(e.getExceptionInfo().getTimeOfException());
                System.out.println("*******************************************************************************\n");
            }
        }
    }

    private void printMenu() {

        System.out.println("1. Show cars");
        System.out.println("2. Sorted cars");
        System.out.println("3. Show cars with specified Body_Type and price between");
        System.out.println("4. Show cars with specified Engine_Type and sorted by model");
        System.out.println("5. Show statistics");
        System.out.println("6. Show cars with sorted mileage");
        System.out.println("7. Show sorted cars by Tyre_Type");
        System.out.println("8. Show cars with specified components");
        System.out.println("9. Exit");
    }

    private void option1() {
        carService.getCars().forEach(System.out::println);
        System.out.println("--------------------------");
    }

    private void option2() {
        carService
                .sort(userDataService.getSortingType(), userDataService.isOrderDescending())
                .forEach(System.out::println);
        System.out.println("--------------------------");

    }

    private void option3() {
        carService
                .carsWithSpecifiedBodyTypeAndPriceBetween(userDataService.getCarBodyType(), userDataService.getBigDecimal("Enter min price"), userDataService.getBigDecimal("Enter max price"))
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
    }

    private void option4() {
        carService
                .sortedCarModelForSpecifiedEngineType(userDataService.getEngineType())
                .forEach(e -> System.out.println(e));
        System.out.println("--------------------------");
    }

    private void option5() {
        carService
                .statistics();
        System.out.println("--------------------------");
    }

    private void option6() {
        carService
                .carsWithMileage()
                .forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("--------------------------");
    }

    private void option7() {
        carService
                .sortedCarsForTyreType()
                .forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("--------------------------");
    }

    private void option8() {
        carService
                .carsWithSpecifiedComponent(userDataService.components())
                .forEach(k -> System.out.println(k));
        System.out.println("--------------------------");
    }

    private void option9() {
        userDataService
                .close();
    }
}
