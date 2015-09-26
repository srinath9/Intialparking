package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Random;

/**
 * Created by srinath on 9/23/2015.
 */
public class UserInfo {


    public static void display( CarObject carList){

        final Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(500);
        window.setMinHeight(500);
        Button yes = new Button("Add the battery life");
        Button close = new Button("Save and Exist");
        close.setAlignment(Pos.CENTER);
        close.setOnAction(e-> window.close());

        yes.setOnAction(e -> {

        });


        TableColumn<CarObject, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));

        //Price column
        TableColumn<CarObject, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<CarObject, Float> priceMin = new TableColumn<>("Min Price");
        priceMin.setMinWidth(100);
        priceMin.setCellValueFactory(new PropertyValueFactory<>("minPrice"));

        //Price column
        TableColumn<CarObject, Float> priceMaxColumn = new TableColumn<>("Max Price");
        priceMaxColumn.setMinWidth(100);
        priceMaxColumn.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));

        TableColumn<CarObject, Date> timeEntry = new TableColumn<>("Entry Time");
        timeEntry.setMinWidth(200);
        timeEntry.setCellValueFactory(new PropertyValueFactory<>("entryTime"));

        //Price column
        TableColumn<CarObject, Date> existTime = new TableColumn<>("Exist Time");
        existTime.setMinWidth(200);
        existTime.setCellValueFactory(new PropertyValueFactory<>("estimatedOutTime"));

        TableView<CarObject> table;

        table = new TableView<>();
        table.setItems(getProduct(carList));
        table.getColumns().addAll(nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,close);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();

    }

    public static <T> String val(T message){
        return ""+message;
    }

    public static ObservableList<CarObject> getProduct(CarObject carList){
        ObservableList<CarObject> products = FXCollections.observableArrayList();
        products.add(carList);
        return products;
    }


}
