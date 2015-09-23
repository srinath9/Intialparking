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

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by srinath on 9/21/2015.
 */
public class RandomGeneration {
    static private String resultValue;



    public static CarObject[] display() {
        Stage window = new Stage();
        CarObject[] carList = new CarObject[1000];
        Button closeButton = new Button("close the widow");

        window.initModality(Modality.APPLICATION_MODAL);
       // window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);

        ComboBox<String> comboBox;
        TextField carname = new TextField();
        TextField batterylife = new TextField();

        Label carlabel = new Label();
        Label batterylabel = new Label();
        Label typelabel = new Label();
        final float sellvalue = RandomClass.sellingPrice();
        Label selllabel = new Label("this is the selling price available now : " + sellvalue);

        Label minPricelabel = new Label("set the min price below to start charge");
        Label maxPricelabel = new Label("Set the max prices value above which you want to sell");

        float minvalue = (float) RandomClass.integerValue(0);
        TextField minPrice = new TextField(String.valueOf(minvalue / 10));

        TextField maxPrice = new TextField(String.valueOf(RandomClass.greaterthanmin(minvalue)));

      //  System.out.println("min price is : " + minPrice.getText() + " max price is : " + maxPrice.getText());

        minPrice.setMaxWidth(100);
        maxPrice.setMaxWidth(100);

        batterylabel.setText("Present Parking lot purchase Price is : " + sellvalue);


        Boolean active;
        active = false;

        comboBox = new ComboBox<String>();


        for (int j=0;j<100;j++){
            carList[j] = new CarObject(RandomClass.stringValue(),RandomClass.integerValue(30),"Buyer",RandomClass.integerValue(0),(float) RandomClass.integerValue(10),(float) RandomClass.integerValue(3));


            Random r =new Random();
            long unixtime=(long) (1293861599+r.nextDouble()*60*60*24*365);
            Date d1 = new Date(unixtime);

            Random r2 =new Random();
            long unixtime2=(long) (1293861599+r2.nextDouble()*60*60*24*365);
            Date d2 = new Date(unixtime2);


           // System.out.println("date and time " +d1 + " : " + unixtime);

            carList[j].setEntryTime(d1);
            carList[j].setExistTime(d2);

         //   carList[j].setEntryTime((int) entry);
          // carList[j].setExistTime((int) end);



            //carList[j].myData();

        }


        TableColumn<CarObject, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));

        //Price column
        TableColumn<CarObject, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<CarObject, Float> priceMin = new TableColumn<>("Min Price");
        priceMin.setMinWidth(200);
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
        existTime.setMinWidth(100);
        existTime.setCellValueFactory(new PropertyValueFactory<>("estimatedOutTime"));

        TableView<CarObject> table;

        table = new TableView<>();
        table.setItems(getProduct(carList));
        table.getColumns().addAll(nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();









        Button yes = new Button("Sell ");
        Button no = new Button("Dont sell");

        no.setOnAction(e -> window.close());


        yes.setOnAction(
                e -> {
/*
                    car.selling(sellvalue);
                    car.getPrice();
                    window.close();*/
                }
        );


        Label label1 = new Label();
        /*closeButton.setOnAction(e -> window.close());*/
     //   label1.setText(message);

        VBox layout = new VBox(10);
        VBox layout2 = new VBox(10);
        VBox layout3 = new VBox(10);
        HBox layout4 = new HBox(10);
        HBox layout5 = new HBox(10);

        layout4.getChildren().addAll(yes, no);
        layout4.setAlignment(Pos.CENTER);
        layout3.setAlignment(Pos.CENTER_RIGHT);
        layout2.setAlignment(Pos.CENTER_LEFT);
        layout3.getChildren().addAll(minPrice, maxPrice);
        layout2.getChildren().addAll(minPricelabel, maxPricelabel);

        layout.setAlignment(Pos.CENTER);

        layout5.getChildren().addAll(layout2, layout3);
        layout5.setAlignment(Pos.CENTER);

     //   layout.getChildren().addAll(batterylabel, layout5, layout4);
     //   Scene scene = new Scene(layout);
      //  window.setTitle("Adding from Seller");
      //  window.setScene(scene);

      //  System.out.println("before closing " + resultValue);
      //  window.showAndWait();

        return carList;

    }


    public static ObservableList<CarObject> getProduct(CarObject[] carList){
        ObservableList<CarObject> products = FXCollections.observableArrayList();

        for(int i =0; i<100; i++){
            products.add(carList[i]);
        }

        return products;
    }


}
