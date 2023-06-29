import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public class OrderController {
    OrderController controller = OrderGui.getController();
    CoffeeMain main = new CoffeeMain();
    @FXML
    private Button button;

    @FXML
    private Label Label;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ToggleButton BlackCoffee;
    @FXML
    private ToggleButton Espresso;
    @FXML
    private ToggleButton Milk;
    @FXML
    private ToggleButton HotWater;
    @FXML
    private ToggleButton WhippedCream;
    @FXML
    private ToggleButton Sugar;
    @FXML
    private ToggleButton Caramel;
    @FXML
    private ToggleButton Mocha;
    @FXML
    private ToggleButton Vanilla;
    @FXML
    private Button nextPage;
    @FXML
    private Label Error01;
    @FXML
    private Button FinalizeCheckOut;
    @FXML
    private TextArea CurrentOrder;
    private Map<String, Integer> inventory;
    CoffeeOrder order = new CoffeeOrder();
    @FXML
    void finalizeDecorator(ActionEvent event) throws IOException {
        this.inventory = CoffeeMain.getInventory();
        Coffee coffee;

        if (BlackCoffee.isSelected()) {
            coffee = new BlackCoffee();
            inventory.replace("Black Coffee", inventory.get("Black Coffee") - 1);
        }
        else {
            coffee = new Espresso();
            inventory.replace("Espresso", inventory.get("Espresso") - 1);
        }
        if (Milk.isSelected()) {
            coffee = new WithMilk(coffee);
            inventory.replace("Milk", inventory.get("Milk") - 1);
        }
        if (Sugar.isSelected()) {
            coffee = new WithSugar(coffee);
            inventory.replace("Sugar", inventory.get("Sugar") - 1);
        }
        if (WhippedCream.isSelected()) {
            coffee = new WithWhippedCream(coffee);
            inventory.replace("Whipped Cream", inventory.get("Whipped Cream") - 1);
        }
        if (HotWater.isSelected()) {
            coffee = new WithHotWater(coffee);
            inventory.replace("Hot Water", inventory.get("Hot Water") - 1);
        }
        if (Caramel.isSelected()) {
            coffee = new WithFlavor(coffee, WithFlavor.Syrup.CARAMEL);
            inventory.replace("CARAMEL Syrup", inventory.get("CARAMEL Syrup") - 1);
        }
        if (Mocha.isSelected()) {
            coffee = new WithFlavor(coffee, WithFlavor.Syrup.MOCHA);
            inventory.replace("MOCHA Syrup", inventory.get("MOCHA Syrup") - 1);
        }
        if (Vanilla.isSelected()) {
            coffee = new WithFlavor(coffee, WithFlavor.Syrup.VANILLA);
            inventory.replace("VANILLA Syrup", inventory.get("VANILLA Syrup") - 1);
        }
        order.addCoffee(coffee);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CheckOut.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
        this.controller = loader.getController();
        CurrentOrder.setText(order.printOrder());

    }
    @FXML
    void finalizeCheckOut() {

    }

    @FXML
    void loadMenu(ActionEvent event) throws IOException {
        CoffeeMain.setInventory(main.pubReadInventory());
        this.inventory = main.pubReadInventory();
        if (!main.pubIsInInventory("Espresso") && !main.pubIsInInventory("Black Coffee")) {
            Error01.setText("Sorry, there is no coffee to order right now.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CoffeeDecorator.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
            this.controller = loader.getController();
            this.inventory = main.pubReadInventory();
        }
        if (!main.pubIsInInventory("Espresso")) {
            Espresso.setText("Out of Stock");
            Espresso.setDisable(true);
        }
        if (!main.pubIsInInventory("Black Coffee")) {
            BlackCoffee.setText("Out of Stock");
            BlackCoffee.setDisable(true);
        }
        if (!main.pubIsInInventory("Sugar")) {
            Sugar.setText("Out of Stock");
            Sugar.setDisable(true);
        }
        if (!main.pubIsInInventory("Milk")) {
            Milk.setText("Out of Stock");
            Milk.setDisable(true);
        }
        if (!main.pubIsInInventory("Whipped Cream")) {
            WhippedCream.setText("Out of Stock");
            WhippedCream.setDisable(true);
        }
        if (!main.pubIsInInventory("Hot Water")) {
            HotWater.setText("Out of Stock");
            HotWater.setDisable(true);
        }
        if (!main.pubIsInInventory("CARAMEL Syrup")) {
            Caramel.setText("Out of Stock");
            Caramel.setDisable(true);
        }
        if (!main.pubIsInInventory("MOCHA Syrup")) {
            Mocha.setText("Out of Stock");
            Mocha.setDisable(true);
        }
        if (!main.pubIsInInventory("VANILLA Syrup")) {
            Vanilla.setText("Out of Stock");
            Vanilla.setDisable(true);
        }

    }
    void finalizeCheckOut(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Cart.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
        this.controller = loader.getController();
        this.inventory = CoffeeMain.getInventory();
        CoffeeMain.setInventory(inventory);
        main.pubWriteInventory();
        CoffeeMain.setOrders(order);
        main.pubWriteOrderLog();
    }

}
