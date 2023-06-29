import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements interface Coffee and acts as one of two different base Coffee objects used during the creation
 * and addition of decorators. Put short, everything that calls or stores a Coffee object will make its way back here
 * at some point.
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */
public class Espresso implements Coffee {

    public Espresso() {
    }

    /**
     * Returns the initial price of an Espresso for all decorators to be added onto.
     * @return double  Returns price of an Espresso
     */
    @Override
    public double getCost() {
        return 1.75;
    }

    /**
     * Creates and returns an arrayList storing "Espresso" Later instances of the arrayList "ingredients" is pulled from
     * here, so that the base of the order is always included
     * @return List  Returns a list of Strings containing only "Espresso"
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Espresso");
        return ingredients;
    }

    /**
     * Returns a String containing only "An espresso" to be added onto by decorators or used independently based on user
     * choice
     * @return String  Returns a string containing "An espresso"
     */
    @Override
    public String printCoffee() {
        return "An espresso";
    }

}
