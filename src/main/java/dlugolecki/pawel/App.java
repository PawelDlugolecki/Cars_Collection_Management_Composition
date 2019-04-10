package dlugolecki.pawel;
import dlugolecki.pawel.service.MenuService;

/**
 * @author Paweł Długołęcki
 * "Cars_Collection_Management_Composition"
 *
 */
public class App 
{
    public static void main( String[] args ) {

        String filename = "cars2";
        new MenuService(filename).menu();
    }
}
