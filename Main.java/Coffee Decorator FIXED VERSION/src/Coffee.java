
import java.util.List;

/**
 * Interface class that acts as the template on which both the base Coffee classes and the CoffeeDecorator class are
 * based upon. Acts as a template for uniformity and organization. As such, all methods here are overwritten elsewhere.
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */
public interface Coffee {
    public double getCost();
    public List<String> getIngredients();
    public String printCoffee();
}
