package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created by srinath on 9/4/2015.
 */
public class Distributor {

    public static void display(String title, String message,int i, CarObject... car ){
        Stage window = new Stage();
        Button closeButton = new Button("close the widow");

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

        comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                objecttoString(i, car));
        /*comboBox.setPromptText("What is your Type you want?");*/




        Button yes = new Button("Yes");

        yes.setOnAction(
                e -> {
                    String userName = comboBox.getValue();
                    CarObject carObject =  searchName(userName,car);
                    System.out.println("the customer is  "+carObject.customerName());
                    
                    window.close();
                }
        );

        Label label1 = new Label();
        /*closeButton.setOnAction(e -> window.close());*/
        label1.setText(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll( typelabel, comboBox, yes);
        layout.setAlignment(Pos.CENTER);



        Scene scene = new Scene(layout);
        window.setTitle("new title");
        window.setScene(scene);
        window.showAndWait();

    }

    private static String[] objecttoString( int num, CarObject... args){
        String[] values = new String[num];
        int i =0;
        for (CarObject value : args){
            /*for (i; i<num; i++){*/

            if ((value != null) /*&& */) {
                if ((Objects.equals(value.typeSell(), "Distributor"))) {


                    System.out.println(value.typeSell());
                    System.out.println(value.customerName());
                    values[i] = value.customerName();
                    i++;

                }
            }
        }

        return values;
    }

    public static <T> String val(T message){
        return ""+message;
    }

    private static CarObject searchName(String searchName, CarObject... args ){
        int i=0;
        CarObject str = null;
        for (CarObject value : args){
            i++;
            /*for (i; i<num; i++){*/
            if (value.customerName() == searchName) {
                System.out.println(value.customerName());
                return value;
            }
        }

        return str;
    }

}
