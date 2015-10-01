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

/**
 * Created by srinath on 9/21/2015.
 */
public class RandomGeneration {
    static private String resultValue;
    static TableView<CarObject> table;
    TextField nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime;
    static TextField carNameInput, minPriceInput, maxPriceInput;






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

            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp t1 = new java.sql.Timestamp(calendar.getTime().getTime());
            java.sql.Timestamp t2 = new java.sql.Timestamp(calendar.getTime().getTime());


            carList[j].setEntryTime(t1);
            carList[j].setExistTime(t2);



        }




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
        minPriceInput.setPromptText("min Price");

        //Quantity input
        TextField maxPriceInput = new TextField();
        maxPriceInput.setPromptText("Max Price");



        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            System.out.println();

            addButtonClicked(carNameInput.getText(),Float.parseFloat(minPriceInput.getText()),Float.parseFloat(maxPriceInput.getText()));
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(carNameInput, minPriceInput, maxPriceInput, addButton, deleteButton);







        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox1);

        Scene scene = new Scene(vBox);
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



        table = new TableView<>();
        table.setItems(getProduct(carList));
        table.getColumns().addAll(nameColumn, typeColumn, priceMin, priceMaxColumn, timeEntry, existTime);



        return table;



    }

    public static void addButtonClicked(String carName, Float minPrice, Float maxPrice){
        CarObject product = new CarObject();
      //  System.out.println(carNameInput.getText());
        product.setCarName(carName);
        //Float.parseFloat(minPriceInput.getText()
        product.setMinPrice(minPrice);
        product.setMaxPrice(maxPrice);
        table.getItems().add(product);
        /*carNameInput.clear();
        minPriceInput.clear();
        maxPriceInput.clear();*/
    }

    public static void deleteButtonClicked(){
        ObservableList<CarObject> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
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
        ObservableList<CarObject> products = FXCollections.observableArrayList();
        int i;
     //  System.out.println("carlist lenth : "+carList.length);
        for(i =0; carList[i]!=null; i++){

            products.add(carList[i]);
        //    System.out.println(carList[i].getCarName());
        }

        return products;
    }


}
