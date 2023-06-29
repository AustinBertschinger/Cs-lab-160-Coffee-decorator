import java.util.List;
/**
 * Abstract class that implements Coffee Interface, stores object of type Coffee and ArrayList of ingredient strings.
 * This class operates as the base that all other decorator classes call back to with numerous super. calls.
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */

public abstract class CoffeeDecorator implements Coffee {
    /**
     * Private variable of type Coffee, stores the coffee object to be added onto for all decorators.
     */
    private Coffee coffee;
    /**
     * ArrayList of Strings storing all the ingredients of the stored Coffee Object.
     */
    List<String> ingredients;

    /**
     * Constructor method that dictates the Coffee object to be added to and initialized the ingredients list, pulling
     * from the Coffee object to start.
     * @param c    The Coffee object to be added on to.
     */
    public CoffeeDecorator (Coffee c) {
        this.coffee = c;
        this.ingredients = coffee.getIngredients();
    }

    /**
     * Calls the Coffee object's getCost() method, this method is almost always overwritten by the decorators
     * and is largely used in cases where no decorators are added.
     * @return double   Returns cost of the Coffee object
     */
    public double getCost() {
        return coffee.getCost();
    }

    /**
     * Returns the ingredients ArrayList when called
     * @return List    Returns ArrayList filled with ingredient Strings
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * Calls the Coffee object's printCoffee() method
     * @return String   Returns the Coffee object's print String.
     */
    public String printCoffee() {
        return coffee.printCoffee();
    }


}
