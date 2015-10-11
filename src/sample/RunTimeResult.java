package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by srinath on 10/8/2015.
 */
public class RunTimeResult {
    static TableView table;
    public static void magic() throws InterruptedException {

        Transaction R1 = new Transaction( "Charging");
        R1.run();


        Transaction R2 = new Transaction( "Discharging");
        //R2.start();

        while (R1.getExistCode()/* || R2.getExistCode()*/) {  Thread.sleep(500); }
        System.out.println("main object");
        CarObject[] carList = R1.getCar();
        table = new TableView<>();
        table = RandomGeneration.tableDisplay(carList);

        Stage window = new Stage();
        Button close = new Button("close");
        close.setOnAction(e -> window.close());

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(table);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
       // RandomGeneration.deleteRow();
        window.show();

    }
}
