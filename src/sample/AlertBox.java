package sample;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * Created by srinath on 8/28/2015.
 */
public class AlertBox {

    public static void display(String title, String message){
        Stage window = new Stage();
        Button closeButton = new Button("close the widow");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label1 = new Label();
        closeButton.setOnAction(e -> window.close());
        label1.setText(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setTitle("new title");
        window.setScene(scene);
        window.showAndWait();

    }

}
