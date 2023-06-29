import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores an ArrayList of Coffee objects along with date and time as of creation. Coffee objects are stored,
 * saved and printed from CoffeeOrder, whether be from within the class itself, or the class being used for this
 * function elsewhere i.e. Main().
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */
public class CoffeeOrder {

    /**
     * ArrayList that contains Coffee objects to be accessed throughout CoffeeOrder
     */
    private List<Coffee> coffees;

    /**
     * LocalDateTime variable that stores the current date and time of the order on creation
     */
    private LocalDateTime orderDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd KK:mma");

    /**
     * Constructor method initializes ArrayList "coffees" and LocalDateTime "orderDate"
     */
    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    /**
     * Adds a Coffee object to the "coffees" ArrayList
     * @param c  Coffee object to be added
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    /**
     * Returns "coffees" ArrayList when called
     * @return List  "coffees" arrayList storing Coffee objects
     */
    public List<Coffee> getCoffees() {
        return coffees;
    }

    /**
     * Returns "orderDate" LocalDateTime when called
     * @return LocalDateTime  "orderDate" LocalDateTime storing time of CoffeeOrder creation
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Adds together the cost of all Coffee objects contained within "coffees" ArrayList and returns total
     * @return double   returns the total cost of all objects within ArrayList
     */
    public double getTotal() {
        double total = 0;
        for (Coffee count : coffees) {
            total += count.getCost();
        }
        return total;
    }

    /**
     * Calls the printCoffee() method of all stored Coffee objects within "coffees".  Along with formatting the results
     * in a receipt fashion.
     * @return String   returns a string containing all the "coffees" ArrayList's contents in receipt fashion
     */
    public String printOrder() {
        StringBuilder out = new StringBuilder("ORDER RECEIPT\n" + "Timestamp: " + getOrderDate().format(formatter)
                + "\n");
        int i = 1;
        for (Coffee count : coffees) {
            out.append("Item " + i + ": " + count.printCoffee() + " - " + String.format("%.2f", count.getCost())
                    + "\n");
            i++;
        }
        out.append("TOTAL = " + getTotal());
        return out.toString();
    }
}
