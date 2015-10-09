
package sample;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.beans.property.*;
import javafx.util.*;
import java.lang.String;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javafx.util.*;

import static java.sql.DriverManager.getConnection;
import java.sql.ResultSet;

/**
 * Created by srinath on 8/28/2015.
 */
public class NewCar/*extends StringConverter<String>*/  {
    static boolean answer;
    private static int i=0;
    static String result;
    static CarObject resultCarDetails;


    public static void display(String title, String message, CarObject car1){



        final Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);

        ComboBox<String> comboBox;
        TextField carname = new TextField();
        TextField batterylife = new TextField();
        TextField addBattery = new TextField();
        int randnum = RandomClass.integerValue(0);

        carname.setText(RandomClass.stringValue());
        batterylife.setText(String.valueOf(randnum));
        addBattery.setText(String.valueOf(RandomClass.integerValue(randnum)));



        Label carlabel = new Label();
        Label batterylabel = new Label();
        Label addBatteryLabel = new Label();
        Label typelabel = new Label();
        carlabel.setText("give the name");
        batterylabel.setText("minimum battery level");
        addBatteryLabel.setText("required ammount of battery");



        /*batterylife.setText();*/

        Boolean active;
        active = false;

        /*comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                "Distributor",
                "Buyer"
        );*/

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton();
        rb1.setText("Seller");
        RadioButton rb2 = new RadioButton("Buyer");
        RadioButton rb3 = new RadioButton("Both");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);

        /*comboBox.setPromptText("What is your Type you want?");*/

       /* comboBox.setOnAction(e -> System.out.print(comboBox.getValue()));*/
        Button yes = new Button("Go to add price preference");
        Button close = new Button("Close");
        close.setOnAction(e-> window.close());
        /*Button no = new Button("NO");*/

        yes.setOnAction(e -> {
            final CarObject car;
            if(rb3.isSelected()){
                System.out.println(carname.getText() + " " + batterylife.getText() + " Both" + " add battery " + batterylife.getText());
                car = new CarObject(val(carname.getText()),  "Both");
            }
            else if(rb1.isSelected()) {
           //     System.out.println(carname.getText() + " " + batterylife.getText() + " Seller");
                  car = new CarObject(val(carname.getText()), "Seller");
            }
            else{
           //     System.out.println(carname.getText() + " " + batterylife.getText() + " Buyer");
                  car = new CarObject(val(carname.getText()), "Buyer");


            }


            car.setInitialBattery(Float.parseFloat(val(batterylife.getText())));
            car.setCriticalMinBattery(Float.parseFloat(val(addBattery.getText())));

            System.out.format("system details      %s, %s, %s  ,  %s\n", car.getCarName(), car.getType(), car.getInitialBattery(), car.getCriticalMinBattery());
            System.out.println("car min value : " + car.getBuyPrice());
            setTime(car);

            SettingPrice.display(carname.getText(), "Select the Seller ", i, car);
            //System.out.println("get the new window : result  "+ result);

            //resultCarDetails =  Distributor.searchName(result, car);


            // car.myData();
            window.close();

        });



        Label label1 = new Label();
        label1.setText(message);
        VBox layout = new VBox(10);
        HBox hb = new HBox(10);
        HBox hb1 = new HBox(10);
        HBox hb2 = new HBox(10);
        HBox hb3 = new HBox(10);

        VBox vb1 = new VBox(10);
        VBox vb2 = new VBox(10);
        VBox vb3 = new VBox(10);

        vb1.getChildren().addAll(carlabel,batterylabel,addBatteryLabel);
        vb1.setSpacing(20);
        vb2.getChildren().addAll(carname,batterylife,addBattery);

        hb.getChildren().addAll(rb1,rb2,rb3);
        hb1.getChildren().addAll(vb1,vb2);
/*        hb2.getChildren().addAll(batterylabel,batterylife);
        hb3.getChildren().addAll(addBatteryLabel,addBattery);*/
        hb.setAlignment(Pos.CENTER);
    //    hb2.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
  //      hb3.setAlignment(Pos.CENTER);
        carname.setMaxWidth(100);
        batterylife.setMaxWidth(100);
        addBattery.setMaxWidth(100);
        layout.getChildren().addAll(label1, hb1, typelabel, hb,yes,close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setTitle("new title");
        window.setScene(scene);
        window.showAndWait();

        /*return car;*/
        /*return resultCarDetails;*/

    }

    private static void setTime(CarObject car){
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp t1 = new java.sql.Timestamp(calendar.getTime().getTime());
        java.sql.Timestamp t2 = new java.sql.Timestamp(calendar.getTime().getTime());

        car.setPlugInTime(t1);
        car.setExistTime(t2);

    }

    private static int incVal(){
        return i++;
    }

    public static <T> String val(T message){
       return ""+message;
    }

    private static  void stringArray(CarObject... args ){

        System.out.println("Array list : ");

        for (CarObject value : args){
            if (value != null) {
                System.out.println(value.getCarName());
            }
        }
    }




}
