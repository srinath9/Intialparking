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
        close.setOnAction(e -> window.close());

        yes.setOnAction(e -> {

        });
        TableView<CarObject> table = tableList(carList);



        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, close);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();

    }

    private static TableView<CarObject> tableList(CarObject carList){

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
        return table;
    }


    public void changeDetails(CarObject carInfo){

    }

    public static <T> String val(T message){
        return ""+message;
    }

    public static void noResult(){
        Stage window = null;

        VBox vbox = new VBox(10);
        Label noresult = new Label("there is  no such index");
        Button close = new Button("close");
        vbox.getChildren().addAll(noresult, close);

        Scene scene = new Scene(vbox);

        window.setScene(scene);
        window.show();

    }


    public static ObservableList<CarObject> getProduct(CarObject carList){
        ObservableList<CarObject> products = FXCollections.observableArrayList();
        products.add(carList);
        return products;
    }

    public static void savedDeatials(CarObject car, String updateType, int carId){
        final Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
    //    window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);


        TextField carname = new TextField(""+car.getCarName());
        TextField minBattery = new TextField(""+car.getMinBattery());
        TextField maxBattery = new TextField(""+car.getMaxBattery());
        TextField addBattery = new TextField();
        int randnum = RandomClass.integerValue(0);


        //batterylife.setText(""+car.get));
        addBattery.setText(""+car.getMaxBattery());



        Label carlabel = new Label();
        Label minBatterylabel = new Label("min battery level");
        Label maxBatterylabel = new Label("max battery level");
        Label addBatteryLabel = new Label("energy level you want to add");
        Label typelabel = new Label();
        carlabel.setText("give the name");

        addBatteryLabel.setText("required ammount of battery");


        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton();
        rb1.setText("Seller");
        RadioButton rb2 = new RadioButton("Buyer");
        RadioButton rb3 = new RadioButton("Both");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);



        final float sellvalue = RandomClass.sellingPrice();

        Label minPricelabel = new Label("set the min price below to start charge");
        Label maxPricelabel = new Label("Set the max prices value above which you want to sell");


        TextField minPrice = new TextField(""+car.getMinPrice());
        TextField maxPrice = new TextField(""+car.getMaxPrice());

        if (car.getType() == "Buyer"){
            rb2.setSelected(true);
            maxPrice.setText("");
            maxPrice.setDisable(true);
        }
        else if (car.getType() == "Seller"){
            rb1.setSelected(true);
            minPrice.setText("");
            minPrice.setDisable(true);
        }
        else{
            rb3.setSelected(true);
        }


        /*comboBox.setPromptText("What is your Type you want?");*/

       /* comboBox.setOnAction(e -> System.out.print(comboBox.getValue()));*/
        Button yes = new Button("Save Details");
        Button close = new Button("Close");

        yes.setOnAction(e->{
            System.out.println("before saving details "+ updateType);
            car.setCarName(carname.getText());
            car.setMaxPrice(Float.parseFloat(maxPrice.getText()));
            car.setMinPrice(Float.parseFloat(minPrice.getText()));
            car.setMinBattery(Float.parseFloat(minBattery.getText()));
            car.setMaxBattery(Float.parseFloat(maxBattery.getText()));
            car.setAddBattery(Float.parseFloat(addBattery.getText()));
            car.myData();


            if (updateType == "save"){
                System.out.println("before saving details "+ updateType);
                DatabaseConnection.saveDetials(car);
            }
            else if (carId !=0)DatabaseConnection.updateDeatils(car,carId);
            window.close();
        });
        close.setOnAction(e-> window.close());


        VBox layout = new VBox(10);
        VBox layout2 = new VBox(10);
        VBox layout3 = new VBox(10);
        HBox layout4 = new HBox(10);
        HBox layout5 = new HBox(10);

      //  layout4.getChildren().addAll(yes, no);
        layout4.setAlignment(Pos.CENTER);
        layout3.setAlignment(Pos.CENTER_RIGHT);
        layout2.setAlignment(Pos.CENTER_LEFT);
        layout3.getChildren().addAll(minPrice, maxPrice);
        layout2.getChildren().addAll(minPricelabel, maxPricelabel);

        layout.setAlignment(Pos.CENTER);

        layout5.getChildren().addAll(layout2, layout3);
        layout5.setAlignment(Pos.CENTER);

        Label label1 = new Label("Check the details");



        VBox layout7 = new VBox(10);
        HBox hb = new HBox(10);
        HBox hb1 = new HBox(10);
        HBox hb2 = new HBox(10);
        HBox hb3 = new HBox(10);

        VBox vb1 = new VBox(10);
        VBox vb2 = new VBox(10);
        VBox vb3 = new VBox(10);

        vb1.getChildren().addAll(carlabel, minBatterylabel,maxBatterylabel,addBatteryLabel);
        vb1.setSpacing(20);

        vb2.getChildren().addAll(carname,minBattery,maxBattery,addBattery);

        hb.getChildren().addAll(rb1,rb2,rb3);
        hb1.getChildren().addAll(vb1,vb2);
/*        hb2.getChildren().addAll(batterylabel,batterylife);
        hb3.getChildren().addAll(addBatteryLabel,addBattery);*/
        hb.setAlignment(Pos.CENTER);
        //    hb2.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
        //      hb3.setAlignment(Pos.CENTER);
        carname.setMaxWidth(100);
        minBattery.setMaxWidth(100);
        maxBattery.setMaxWidth(100);
        addBattery.setMaxWidth(100);
        layout7.getChildren().addAll(hb1, typelabel, hb);
        layout7.setAlignment(Pos.CENTER);

        layout.getChildren().addAll( layout7, layout5, layout4, yes, close);
        Scene scene = new Scene(layout);
        window.setTitle("Adding from Seller");

        window.setScene(scene);
        window.showAndWait();
    }


}
