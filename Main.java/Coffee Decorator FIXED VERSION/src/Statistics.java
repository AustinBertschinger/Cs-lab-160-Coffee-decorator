import java.time.LocalDateTime;
import java.util.List;

/**
 * A class that stores Statistics across all previous and current CoffeeOrders. Statistics including average cost across
 * orders, most popular order and total profits based on time intervals.
 * @author Austin Bertschinger
 * @since SDK17.0.4
 */

public class Statistics {
    /**
     * List of CoffeeOrder objects to save values frequently needed
     */
    private List<CoffeeOrder> orders;

    /**
     * Constructor method that initializes "orders" variable
     * @param orders  Takes in an ArrayList of CoffeeOrder objects
     */
    public Statistics(List<CoffeeOrder> orders) {
        this.orders = orders;
    }

    /**
     * Returns combined cost of all orders placed within a given time period
     * @param start    Takes in a start date
     * @param end      Takes in an end date
     * @return double  Returns a double value containing total profit
     */
    public double getTotal(LocalDateTime start, LocalDateTime end) {
        return 0.0;
    }

    /**
     * Returns the average cost of all orders within the "orders" ArrayList taken in the constructor
     * @return double  Returns a double value containing the average cost of all orders
     */
    public double getAvgCost() {
        return 0.0;
    }

    /**
     * Calculates and returns the most popular recurring combinations within the "orders" ArrayList
     * @param maxRank  Takes in an int value determining the amount of combinations shown from most recurring to least
     * @return String[]  Returns an Array of Strings containing the most popular combinations
     */
    public String[] getPopularOrders(int maxRank) {
        return null;
    }

    /**
     * Prints all values gathered and stored in previous methods
     * @return String  Returns a string containing all the previously gathered statistics
     */
    public String printStatistics() {
        return "";
    }
}
