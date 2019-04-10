package dlugolecki.pawel.service;

import dlugolecki.pawel.exceptions.ExceptionCode;
import dlugolecki.pawel.exceptions.MyException;
import dlugolecki.pawel.model.enums.CarBodyType;
import dlugolecki.pawel.model.enums.EngineType;
import dlugolecki.pawel.model.enums.SortingType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDataService {

    private Scanner sc = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("\\d+")) {
            throw new MyException(ExceptionCode.VALIDATION, "Value is not digit: " + text);
        }
        return Integer.parseInt(text);
    }

    public boolean isOrderDescending() {
        System.out.println("Sort cars descending ?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        String text = sc.nextLine();
        if (!text.matches("[1-2]")) {
            throw new MyException(ExceptionCode.VALIDATION, "Sort type option number is not valid: " + text);
        }
        return text.equals("1");
    }

    public SortingType getSortingType() {
        System.out.println("Choose sorting type:");
        System.out.println("1 - numbers_components");
        System.out.println("2 - engine_power");
        System.out.println("3 - tyre_size");

        String text = sc.nextLine();
        if (!text.matches("[1-3]")) {
            throw new MyException(ExceptionCode.INPUT_DATA, "Sorting type option is not valid: " + text);
        }
        return SortingType.values()[Integer.parseInt(text) - 1];
    }

    public CarBodyType getCarBodyType() {
        System.out.println("Choose Car_Body type");
        System.out.println("1. HATCHBACK");
        System.out.println("2. KOMBII");
        System.out.println("3. SEDAN");

        String text = sc.nextLine();
        if (!text.matches("[1-3]")) {
            throw new MyException(ExceptionCode.INPUT_DATA, "Car_Body type is not valid: " + text);
        }
        return CarBodyType.values()[Integer.parseInt(text) - 1];
    }

    public EngineType getEngineType() {
        System.out.println("Choose Engine_Type");
        System.out.println("1. DIESEL");
        System.out.println("2. GASOLINE");
        System.out.println("3. LPG");

        String text = sc.nextLine();
        if (!text.matches("[1-3]")) {
            throw new MyException(ExceptionCode.INPUT_DATA, "Engine_Type is not valid: " + text);
        }
        return EngineType.values()[Integer.parseInt(text) - 1];
    }

    public List<String> components() {
        List<String> componentsFromUser = new ArrayList<>();
        String anotherComp = null;
        do {
            System.out.println("Do you want to add another component");
            System.out.println("YES/NO");
            anotherComp = sc.nextLine();
            if (anotherComp.equalsIgnoreCase("NO")) {
                break;
            }
            System.out.println("Enter component");
            componentsFromUser.add(sc.nextLine());

        } while (anotherComp.equalsIgnoreCase("YES"));

        return componentsFromUser;
    }

    public BigDecimal getBigDecimal(String message) {
        System.out.println(message);
        String text = sc.nextLine();

        if (!text.matches("(\\d+\\.)*\\d+")) {
            throw new MyException(ExceptionCode.VALIDATION, "Value is not a decimal: " + text);
        }
        return new BigDecimal(text);
    }

    public void close() {
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }
}
