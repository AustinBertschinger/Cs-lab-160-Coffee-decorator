import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Coffee Designer
 * An interface that takes in user choices from given options to generate a coffeeOrder object which is then
 * added to an arraylist which is then saved in a log file prior to finishing the program.
 *
 * @author Austin Bertschinger
 * @version 1.0
 * @since SDK17.0.4
 */

public class CoffeeMain {
    /**
     * TreeMap storing the contents of a .txt file to be edited and saved back to given .txt file
     */
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    /**
     * ArrayList storing CoffeeOrder objects to be read, appended to, and saved in given .txt file
     */
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    /**
     * String holding the file name/directory to be used in later methods, OrderLog
     */
    private static String logFile = "OrderLog.txt";
    /**
     * String holding the file name/directory to be used in later methods, Inventory
     */
    private static String inventoryFile = "Inventory.txt";

    public static void main(String[] args) {
        inventory = readInventory(inventoryFile);
        System.out.println("Welcome to Java Coffee Co.!");
        try (Scanner input = new Scanner(System.in)) {
            boolean addOrder;
            start: do {
                String yn;
                System.out.println("\nPlease select an option:");
                System.out.println("\n\t1. New Order");
                System.out.println("\n\t2. Reload Inventory");
                System.out.println("\n\t3. Update Inventory");
                System.out.println("\n\t4. Update Order Log");
                System.out.println("\n\t5. Exit");
                int option = input.nextInt();
                if (option < 1 || option > 5) {
                    System.out.println("\nPlease select a valid option.");
                    option = input.nextInt();
                }
                switch (option) {
                    case 1:
                        CoffeeOrder order = buildOrder();
                        if (order == null)
                            break start;
                        orders.add(order);
                        System.out.println(order.printOrder());
                        break;
                    case 2:
                        inventory = readInventory(inventoryFile);
                        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        break;
                    case 3:
                        writeInventory(inventoryFile);
                        System.out.println("Inventory updated.");
                        break;
                    case 4:
                        writeOrderLog(logFile);
                        System.out.println("Order log updated.");
                        break;
                    case 5:
                        writeInventory(inventoryFile);
                        break start;

                }


                System.out.println("\nWould you like to enter another order (Y or N)?");
                yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addOrder = !yn.equalsIgnoreCase("N");
            } while (addOrder);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (orders.size() > 0) writeOrderLog(logFile);
        writeInventory(inventoryFile);
    }

    /**
     * Prompts user to select from given options and takes user's input to create a CoffeeOrder object
     *
     * @return CoffeeOrder  object storing user's coffee selection, total price, time of order and total ingredients
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {
                // prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine(); // nextInt() doesn't consume newline
                if (!isInInventory("Espresso") && !isInInventory("Black Coffee")) {
                    System.out.println("Sorry, there is currently no coffee available to order.");
                    return null;
                }
                /*
                * note: this line acts a default, ordinarily the code will never reach this line or will
                * immediately overwrite it, this is just to enable later lines as I do not have a default
                * outcome specifically designated in the following lines
                */
                coffee = new BlackCoffee();
                if (option == 2) {
                    // Espresso is a specific case
                    if (isInInventory("Espresso")) {
                        coffee = new Espresso();
                        inventory.replace("Espresso", inventory.get("Espresso") - 1);
                    }
                    else {
                        System.out.println("Sorry, there are no more Espressos, you must choose another option.");
                    }
                } else {
                    // make BlackCoffee the default case
                    if (isInInventory("Black Coffee")) {
                        coffee = new BlackCoffee();
                        inventory.replace("Black Coffee", inventory.get("Black Coffee") - 1);
                    } else {
                        System.out.println("Sorry, there are no more Black Coffees, you mus choose another option.");
                    }
                }


                // prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    coffee = switch (option) {
                        case 1: {
                            if (!isInInventory("CARAMEL Syrup")) {
                                System.out.println("Sorry, we are out of Caramel, you must pick a different option");
                            }
                            if (!isInInventory("MOCHA Syrup")) {
                                System.out.println("Sorry, we are out of Mocha, you must pick a different option");
                            }
                            if (!isInInventory("VANILLA Syrup")) {
                                System.out.println("Sorry, we are out of Vanilla, you must pick a different option");
                            }
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            option = 1;
                            inventory.replace(flavor.toString() + " Syrup", inventory.get(flavor.toString() + " Syrup") - 1);
                            yield new WithFlavor(coffee, flavor);
                        }
                        case 2:
                            if (!isInInventory("Hot Water")) {
                                System.out.println("Sorry, there is no hot water to add.");
                            }
                            else {
                                inventory.replace("Hot Water", inventory.get("Hot Water") - 1);
                                yield new WithHotWater(coffee);
                            }

                        case 3:
                            if (!isInInventory("Milk")) {
                                System.out.println("Sorry, there is no milk to add.");
                            }
                            else {
                                inventory.replace("Milk", inventory.get("Milk") - 1);
                                yield new WithMilk(coffee);
                            }

                        case 4:
                            if (!isInInventory("Sugar")) {
                                System.out.println("Sorry, there is no sugar to add.");
                            }
                            else {
                                inventory.replace("Sugar", inventory.get("Sugar") - 1);
                                yield new WithSugar(coffee);
                            }

                        case 5:
                            if (!isInInventory("Whipped Cream")) {
                                System.out.println("Sorry, there is no whipped cream to add.");
                            }
                            else {
                                inventory.replace("Whipped Cream", inventory.get("Whipped Cream") - 1);
                                yield new WithWhippedCream(coffee);
                            }
                        default: {
                            if (option != 0) System.out.println("Please enter valid option.");
                            yield coffee;
                        }
                    };
                }
                order.addCoffee(coffee);

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }
    /**
     * Reads provided .txt file and stores its contents within a Treemap for later use
     *
     * @param filePath   The .txt file which is read and stored in the Treemap
     * @return Map       A Treemap to be stored and used in later methods
     */
    private static Map<String, Integer> readInventory(String filePath) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String ingredient = parts[0].trim();
                String amount = parts[1].trim();
                if (!ingredient.equals("") && !amount.equals("")) {
                    map.put(ingredient, Integer.parseInt(amount));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading inventory log: " + e.getMessage());
        }
        return map;
    }

    /**
     * Takes in a .txt file and overwrites its currently saved contents
     *
     * @param filePath   The .txt file to be overwritten/filled
     */
    private static void writeInventory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
               writer.write(entry.getKey() + ": " + entry.getValue());
               writer.newLine();
            }
            System.out.println("Inventory Saved.");
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Takes in a .txt file to be read and saved to an ArrayList
     *
     * @param filePath    The .txt file to be read and saved
     * @return ArrayList  An ArrayList filled with coffeeOrder objects to be used in later methods
     */
    private static List<CoffeeOrder> readOrderLog(String filePath) {
        return null;
    }

    /**
     * Takes in a .txt file to be appended with the currently stored ArrayList
     *
     * @param filePath    The .txt file to be appended to
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Checks the TreeMap stored in "inventory" for if the given input:
     * 1. Exists in the map
     * 2. Contains a value greater then zero
     * @param i         String to be checked against inventory for whether it exists, and it's current value.
     * @return boolean  Returns true if both conditions are met, false if otherwise.
     */
    private static boolean isInInventory(String i) {
        if (inventory.containsKey(i)) {
            if (inventory.get(i) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Appends current OrderLog ArrayList with new CoffeeOrder objects
     */
    private static void updateOrderLog() {}

    /**
     * Public version of isInInventory made to accommodate the GUI class
     * @param i         String to be checked against inventory for whether it exists, and it's current value.
     * @return boolean  Returns true if both conditions are met, false if otherwise.
     */
    public boolean pubIsInInventory(String i) {
        return isInInventory(i);
    }

    /**
     * Public version of writeInventory to accommodate the GUI class
     */
    public void pubWriteInventory() {
        writeInventory(inventoryFile);
    }

    /**
     * Public version of readInventory to accommodate the GUI class
     */
    public Map<String, Integer> pubReadInventory() {
        return readInventory(inventoryFile);
    }

    /**
     * Public version of writeOrderLog to accommodate the GUI class
     */
    public void pubWriteOrderLog() {
        writeOrderLog(logFile);
    }

    public static void setInventory(Map<String, Integer> map) {
        inventory = map;
    }

    public static Map<String, Integer> getInventory() {
        return inventory;
    }

    public static void setOrders(CoffeeOrder order) {
        orders.add(order);
    }
}