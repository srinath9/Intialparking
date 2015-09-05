
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






    public static void display(String title, String message, CarObject[] car){

        /*CarObject[] car = new CarObject[10];*/


        Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);

        ComboBox<String> comboBox;
        TextField carname = new TextField();
        TextField batterylife = new TextField();

        Label carlabel = new Label();
        Label batterylabel = new Label();
        Label typelabel = new Label();


        carlabel.setText("give the name");
        batterylabel.setText("give the integer battery level");


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
        Button yes = new Button("Yes");
        /*Button no = new Button("NO");*/



        yes.setOnAction(e -> {

            int index = i;
            incVal();
            stringArray(car);
            System.out.println("index value is: " + index);

            answer = true;
            
            if(rb2.isSelected()){
                System.out.println(carname.getText() + " " + batterylife.getText() + " Distributor");
                car[index] = new CarObject(val(carname.getText()), Float.parseFloat(val(batterylife.getText())), "Distributor");

                Distributor.display(carname.getText(),"Select the distributor ", i, car);
                System.out.println("get the new window");
            }
            else {
                System.out.println(carname.getText() + " " + batterylife.getText() + " Buyer");
                car[index] = new CarObject(val(carname.getText()), Float.parseFloat(val(batterylife.getText())), "Buyer");
            }

            window.close();
        });





        Label label1 = new Label();
        /*closeButton.setOnAction(e -> window.close());*/
        label1.setText(message);

        HBox layout = new HBox(10);
        layout.getChildren().addAll(label1, carlabel, carname, batterylabel, batterylife, typelabel,  rb1,rb2,yes);
        layout.setAlignment(Pos.CENTER);



        Scene scene = new Scene(layout);
        window.setTitle("new title");
        window.setScene(scene);
        window.showAndWait();

        /*return car;*/

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

    /*@Override
    public String toString() {
        return "";
    }

    @Override
    public String fromString(String string) {
        return null;
    }*/



}
