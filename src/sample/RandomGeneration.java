package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javafx.scene.control.TextField;

import javax.swing.text.LabelView;

/**
 * Created by srinath on 9/21/2015.
 */

public class RandomGeneration {
    static private String resultValue;
    static TableView<CarObject> table;
    TextField nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime;
    static TextField carNameInput, minPriceInput, maxPriceInput;
    static CarObject[] carList = RandomDetails.setCarDetails();

    private static ObservableList<CarObject> productsList = FXCollections.observableArrayList();





    public static CarObject[] display() {
        productsList.removeAll(carList);
        Stage window = new Stage();

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


        Button yes = new Button("Sell ");
        Button no = new Button("Dont sell");

        no.setOnAction(e -> window.close());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        //hBox.getChildren().addAll(nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime, addButton, deleteButton);

        table = new TableView<>();
        table = tableDisplay(carList);


        TextField carNameInput = new TextField();
        carNameInput.setPromptText("Name");
        carNameInput.setMinWidth(100);

        //Price input
        TextField minPriceInput = new TextField();
        minPriceInput.setPromptText("Buying Price");

        //Quantity input
        TextField maxPriceInput = new TextField();
        maxPriceInput.setPromptText("Selling Price");

        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
         //   addButtonClicked(carNameInput.getText(),Float.parseFloat(minPriceInput.getText()),Float.parseFloat(maxPriceInput.getText()));
        });
        Button deleteButton = new Button("Delete");
     //   deleteButton.setOnAction(e -> deleteButtonClicked());


        TextField search = new TextField("4");
        Label searchlabel = new Label("Give the Id number to Change Details");
        Button searchDeatilsButton = new Button("Search details of Specific User");
        searchDeatilsButton.setOnAction(e -> {
            int index = Integer.parseInt(search.getText());
            carList[index] = savedDetails(carList[index]);
            window.close();


           // tableDisplay(carList);
            //  window.close();

        });

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(carNameInput, minPriceInput, maxPriceInput, addButton, deleteButton);

        search.setMaxWidth(100);

        VBox vBox0 = new VBox();
        vBox0.getChildren().addAll(table,searchlabel,search,searchDeatilsButton);
        vBox0.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox0);
        window.setScene(scene);
        window.show();


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

    public static TableView<CarObject> tableDisplay(CarObject[] carList){
        deleteAll();
        TableColumn<CarObject, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));

        //Price column
        TableColumn<CarObject, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(50);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<CarObject, Float> priceMin = new TableColumn<>("Buy Price");
        priceMin.setMinWidth(75);
        priceMin.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));

        //Price column
        TableColumn<CarObject, Float> priceMaxColumn = new TableColumn<>("Selling Price");
        priceMaxColumn.setMinWidth(75);
        priceMaxColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        TableColumn<CarObject, Date> plugInTime = new TableColumn<>("Plug In Time");
        plugInTime.setMinWidth(150);
        plugInTime.setCellValueFactory(new PropertyValueFactory<>("plugInTime"));

        //Price column
        TableColumn<CarObject, Date> existTime = new TableColumn<>("Exist Time");
        existTime.setMinWidth(150);
        existTime.setCellValueFactory(new PropertyValueFactory<>("existTime"));

        TableColumn<CarObject, Float> plugInDuration = new TableColumn<>("Plug In Duration");
        plugInDuration.setMaxWidth(80);
        plugInDuration.setCellValueFactory(new PropertyValueFactory<>("plugInDuration"));

        TableColumn<CarObject, Float> carId = new TableColumn<>("Car Id");
        carId.setMaxWidth(50);
        carId.setCellValueFactory(new PropertyValueFactory<>("carId"));

        TableColumn<CarObject, Float> batteryLevel = new TableColumn<>("Final Battery Level");
        batteryLevel.setMinWidth(100);
        batteryLevel.setCellValueFactory(new PropertyValueFactory<>("batteryLevel"));

        TableColumn<CarObject, Float> chargingType = new TableColumn<>("Charging Type");
        chargingType.setMaxWidth(60);
        chargingType.setCellValueFactory(new PropertyValueFactory<>("chargingType"));

        TableColumn<CarObject, Float> initialBattery = new TableColumn<>("Inital Battery");
        initialBattery.setMinWidth(100);
        initialBattery.setCellValueFactory(new PropertyValueFactory<>("initialBattery"));

        TableColumn<CarObject, Float> criticalMin = new TableColumn<>("Critical minimum Battery");
        criticalMin.setMinWidth(100);
        criticalMin.setCellValueFactory(new PropertyValueFactory<>("criticalMinBattery"));

        TableColumn<CarObject, Float> moneyEarned = new TableColumn<>("Money Earned");
        moneyEarned.setMinWidth(100);
        moneyEarned.setCellValueFactory(new PropertyValueFactory<>("moneyEarned"));

        TableColumn<CarObject, Float> spentMoney = new TableColumn<>("Money Spent");
        spentMoney.setMinWidth(100);
        spentMoney.setCellValueFactory(new PropertyValueFactory<>("moneyRequired"));

        TableColumn<CarObject, Float> chargeRate = new TableColumn<>("Charge Rate");
        chargeRate.setMinWidth(100);
        chargeRate.setCellValueFactory(new PropertyValueFactory<>("chargingRate"));

        TableColumn<CarObject, Float> disChargeRate = new TableColumn<>("Discharge Rate");
        disChargeRate.setMinWidth(100);
        disChargeRate.setCellValueFactory(new PropertyValueFactory<>("disChargingRate"));

        TableView<CarObject> table  = new TableView<>();

        table.setItems(getProduct(carList));
        table.getColumns().addAll(carId, nameColumn, typeColumn, priceMin, priceMaxColumn, plugInTime, existTime, plugInDuration,chargingType,initialBattery,criticalMin,batteryLevel,moneyEarned,spentMoney,chargeRate,disChargeRate);
        return table;
    }

    public static void addButtonClicked(CarObject car){
      /*  CarObject product = new CarObject(carName,"Buyer");
      //  System.out.println(carNameInput.getText());
        product.setCarName(carName);*/
        //Float.parseFloat(minPriceInput.getText()
      //  product.setBuyPrice(minPrice);
    //    product.setSellingPrice(maxPrice);
        System.out.println("adding car list");
        productsList.add(car);
        /*carNameInput.clear();
        minPriceInput.clear();
        maxPriceInput.clear();*/
    }

    public static void deleteRow(){
/*

        TableView.TableViewSelectionModel selectionModel = productsList.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        Object val = tablePosition.getTableColumn().getCellData(newValue);
        System.out.println("Selected Value" + val);*/

        productsList.removeAll(carList);

    }
    public static void deleteAll(){
        productsList.removeAll(carList);

    }




    public static CarObject savedDetails(CarObject car){
        final Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
        //    window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);


        TextField carname = new TextField(""+car.getCarName());
        TextField minBattery = new TextField(""+car.getInitialBattery());
        TextField maxBattery = new TextField(""+car.getCriticalMinBattery());


        Label carlabel = new Label();
        Label minBatterylabel = new Label("Initial battery level");
        Label maxBatterylabel = new Label("Critical Battery level");

        Label typelabel = new Label();
        carlabel.setText("give the name");


        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton();
        rb1.setText("Seller");
        RadioButton rb2 = new RadioButton("Buyer");
        RadioButton rb3 = new RadioButton("Both");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);

        Label minPricelabel = new Label("set the min price below to start charge");
        Label maxPricelabel = new Label("Set the max prices value above which you want to sell");


        TextField minPrice = new TextField(""+car.getBuyPrice());
        TextField maxPrice = new TextField(""+car.getSellingPrice());

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
        //    System.out.println("before saving details "+ updateType);
            car.setCarName(carname.getText());
            //  car.setSellingPrice(Float.parseFloat(maxPrice.getText()));
            car.setBuyPrice(Float.parseFloat(minPrice.getText()));
            car.setCriticalMinBattery(Float.parseFloat(minBattery.getText()));
            car.setInitialBattery(Float.parseFloat(maxBattery.getText()));
            int index = car.getCarId();
        //    car.myData();
            deleteRow();
        //    addButtonClicked(car);
            window.close();
            display();

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

        VBox layout7 = new VBox(10);
        HBox hb = new HBox(10);
        HBox hb1 = new HBox(10);

        VBox vb1 = new VBox(10);
        VBox vb2 = new VBox(10);

        vb1.getChildren().addAll(carlabel, minBatterylabel, maxBatterylabel);
        vb1.setSpacing(20);

        vb2.getChildren().addAll(carname,minBattery,maxBattery);

        hb.getChildren().addAll(rb1,rb2,rb3);
        hb1.getChildren().addAll(vb1, vb2);

        hb.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);

        carname.setMaxWidth(100);
        minBattery.setMaxWidth(100);
        maxBattery.setMaxWidth(100);

        layout7.getChildren().addAll(hb1, typelabel, hb);
        layout7.setAlignment(Pos.CENTER);

        layout.getChildren().addAll( layout7, layout5, layout4, yes, close);
        Scene scene = new Scene(layout);
        window.setTitle("Adding from Seller");

        window.setScene(scene);
        window.showAndWait();

        return car;
    }




/*

    public void addButtonClicked(){
        CarObject product = new CarObject();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }
*/


    public static ObservableList<CarObject> getProduct(CarObject[] carList){
       // ObservableList<CarObject> products = FXCollections.observableArrayList();
        int i;
     //  System.out.println("carlist lenth : "+carList.length);
        for(i =0; carList[i]!=null; i++){
        //    System.out.println(i);
            productsList.add(carList[i]);
        //    System.out.println(carList[i].getCarName());
        }

        return productsList;
    }


}
