import java.util.List;

/**
 * Child class of abstract CoffeeDecorator, adds a flavor to the current Coffee object from a list of enums along with
 * other specifications, i.e. cost and ingredients
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */
public class WithFlavor extends CoffeeDecorator {

    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }

    /**
     * Private variable of type Syrup to store an enum for later use
     */
    private Syrup flavor;

    /**
     * Constructor method, dictates enum to be stored and Coffee object to be added on
     * @param c   Coffee object to be added on
     * @param s   Syrup enum to be saved and called for string writing and saving
     */
    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    /**
     * Returns the specified cost of this decorator to be added to the Coffee object's total
     * @return double  A double value storing this decorators cost
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    /**
     * Returns this decorator's ingredients to be added to an ingredients ArrayList in the Coffee object
     * @return List   Returns a list of string objects
     */
    @Override
    public List<String> getIngredients() {
        super.ingredients.add(flavor.toString() + " Syrup");
        return super.ingredients;
    }
    /**
     * Adds this decorator's name to the Coffee class's printCoffee() string for the associated CoffeeOrder object
     * @return String   Calls the parent class's printCoffee method, along with adding its own identifier string
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
