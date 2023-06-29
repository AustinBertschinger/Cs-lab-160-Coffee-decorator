/**
 * GUI launcher, run this instead of the main class to load the GUI for the ordering menu
 *
 * @author Austin Bertschinger
 * @version 1.0
 * @since SDK17.0.4
 */

//imports for GUI

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderGui extends Application {

    Stage primaryStage = new Stage();
    static OrderController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // load fxml file for main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/OrderingMenu.fxml"));

        Parent root = loader.load();

        // creates new scene and controller
        Scene scene = new Scene(root);
        controller = loader.getController();

        // sets title of stage and sets scene to loaded main menu
        primaryStage.setTitle("Coffee Designer");
        primaryStage.setScene(scene);

        // makes gui not resizable
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
