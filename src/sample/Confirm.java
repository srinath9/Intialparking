
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
import javafx.util.*;
/**
 * Created by srinath on 8/28/2015.
 */
public class Confirm /*extends StringConverter<String>*/  {
    static boolean answer;
    private static int i=0;
    static String result;
    static CarObject resultCarDetails;


    public static void display(String title, String message, CarObject[] car){


        Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);

        ComboBox<String> comboBox;
        TextField carname = new TextField();
        TextField batterylife = new TextField();
        TextField addBattery = new TextField();

        Label carlabel = new Label();
        Label batterylabel = new Label();
        Label addBatteryLabel = new Label();
        Label typelabel = new Label();
        carlabel.setText("give the name");
        batterylabel.setText("integer battery level");
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
        rb1.setText("Distributor");
        RadioButton rb2 = new RadioButton("Buyer");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        /*comboBox.setPromptText("What is your Type you want?");*/

       /* comboBox.setOnAction(e -> System.out.print(comboBox.getValue()));*/
        Button yes = new Button("Add the battery life");
        /*Button no = new Button("NO");*/

        yes.setOnAction(e -> {
            int index = i;
            incVal();
            stringArray(car);
            System.out.println("index value is: " + index);

            answer = true;
            
            if(rb2.isSelected()){
                System.out.println(carname.getText() + " " + batterylife.getText() + " Buyer" + " add battery " + batterylife.getText());
                car[index] = new CarObject(val(carname.getText()), Float.parseFloat(val(batterylife.getText())), "Buyer",Float.parseFloat(val(addBattery.getText())));

                result = Distributor.display(carname.getText(), "Select the distributor ", i, car);
                System.out.println("get the new window : result  "+ result);

                resultCarDetails =  Distributor.searchName(result, car);

                car[index].addBatteryLife(Float.parseFloat(val(addBattery.getText())));

                car[index].myData();




            }
            else {
                System.out.println(carname.getText() + " " + batterylife.getText() + " Distributor");
                car[index] = new CarObject(val(carname.getText()), Float.parseFloat(val(batterylife.getText())), "Distributor",Float.parseFloat(val(addBattery.getText())));
            }
            window.close();
        });

        Label label1 = new Label();
        label1.setText(message);
        VBox layout = new VBox(10);
        HBox hb = new HBox(10);
        HBox hb1 = new HBox(10);
        HBox hb2 = new HBox(10);
        HBox hb3 = new HBox(10);
        hb.getChildren().addAll(rb1,rb2);
        hb1.getChildren().addAll(carlabel,carname);
        hb2.getChildren().addAll(batterylabel,batterylife);
        hb3.getChildren().addAll(addBatteryLabel,addBattery);
        hb.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        carname.setMaxWidth(100);
        batterylife.setMaxWidth(100);
        addBattery.setMaxWidth(100);
        layout.getChildren().addAll(label1, hb1,hb2,hb3, typelabel, hb,yes);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setTitle("new title");
        window.setScene(scene);
        window.showAndWait();

        /*return car;*/
        /*return resultCarDetails;*/

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
                System.out.println(value.customerName());
            }
        }
    }




}
