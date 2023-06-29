import java.util.List;
/**
 * Child class of abstract CoffeeDecorator, adds "Sugar" to current Coffee object along with entailing
 * specifications, i.e. cost and ingredients
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */

public class WithSugar extends CoffeeDecorator {

    /**
     * Constructor class, takes in Coffee object with which its specifications will be added onto.
     * @param c   Coffee object to be added onto.
     */
    public WithSugar (Coffee c) {
        super(c);
    }

    /**
     * Returns the specified cost of this decorator to be added to the Coffee object's total
     * @return double   A double value storing this decorators cost
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    /**
     * Returns this decorator's ingredients to be added to an ingredients ArrayList in the Coffee object
     * @return List   Returns a list of string objects
     */
    @Override
    public List<String> getIngredients() {
        super.ingredients.add("Sugar");
        return super.ingredients;
    }
    /**
     * Adds this decorator's name to the Coffee class's printCoffee() string for the associated CoffeeOrder object
     * @return String   Calls the parent class's printCoffee method, along with adding its own identifier string
     */

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}