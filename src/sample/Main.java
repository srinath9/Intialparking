package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;


public class Main extends Application {
    static float battery_left = 1000;

    Stage window;
    Scene scene1, scenen2;

    @Override
    public void start(Stage primaryStage) throws Exception{

        CarObject[] car = new CarObject[10];

        window  = primaryStage;
        Label label1 = new Label("welcome ");

        Button button1 = new Button("go to scene 2");
        button1.setText("hi hello");
        button1.setOnAction(e -> window.setScene(scenen2));

        Button button2 = new Button("to back to scenen1");
        button2.setOnAction(e -> window.setScene(scene1));
        VBox layout1 = new VBox(20);


        scene1 = new Scene(layout1,500,600);

        Button button = new Button("Add new car");
        int i=0;

        button.setOnAction(e->{

             /*carResult =*/
            Confirm.display("cnonfirmatiuon","confirm it",car);
            /*System.out.print(carResult);*/

        });

        layout1.getChildren().addAll(label1, button1,button);

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);

        scenen2 = new Scene(layout2,650,350);


        window.setScene((scene1));
        window.setTitle("scene1");
        window.show();



    }


    public static void main(String[] args) {
        launch(args);
    }



}
