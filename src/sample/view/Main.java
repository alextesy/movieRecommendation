package sample.view;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.MainViewHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
        Parent root = mainViewLoader.load();
        MainViewHandler controller = mainViewLoader.getController();
        controller.setViewController(this);
        controller.setUp();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
