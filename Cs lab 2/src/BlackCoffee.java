import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements interface Coffee and acts as one of two different base Coffee objects used during the creation
 * and addition of decorators. Put short, everything that calls or stores a Coffee object will make its way back here
 * at some point.
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */
public class BlackCoffee implements Coffee {

    public BlackCoffee() {
    }

    /**
     * Returns the initial price of a Black Coffee for all decorators to be added onto.
     * @return double  Returns price of a Black Coffee
     */
    @Override
    public double getCost() {
        return 1.0;
    }

    /**
     * Creates and returns an arrayList storing "Black Coffee" Later instances of the arrayList "ingredients" is pulled
     * from here, so that the base of the order is always included
     * @return List  Returns a list of Strings containing only "Espresso"
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Black Coffee");
        return ingredients;
    }

    /**
     * Returns a String containing only "A black coffee" to be added onto by decorators or used independently based on
     * user choice
     * @return String  Returns a string containing "A black coffee"
     */
    @Override
    public String printCoffee() {
        return "A black coffee";
    }

}
