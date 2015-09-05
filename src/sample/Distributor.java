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
    static private String resultValue;

    public static String display(String title, String message, int i, CarObject... car){
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
                objecttoString( car));
        /*comboBox.setPromptText("What is your Type you want?");*/




        Button yes = new Button("Add Battery Life");
        final String[] userName = {new String()};

        yes.setOnAction(
                e -> {
                    userName[0] = comboBox.getValue();
                    CarObject carObject =  searchName(userName[0],car);
                    changeValue(userName[0]);
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
        window.setTitle("Adding from distributor");
        window.setScene(scene);

        System.out.println("before closing " + resultValue);
        window.showAndWait();

        return resultValue;



    }

    private static String[] objecttoString( CarObject... args){
        String[] values = new String[10];
        int i =0;
        for (CarObject value : args){
            /*for (i; i<num; i++){*/

            if ((value != null) /*&& */) {
                if ((Objects.equals(value.typeSell(), "Distributor"))) {


                    System.out.println("type of selling process : "+value.typeSell() + " custoimer to " + value.customerName());

                    values[i] = value.customerName();
                    i++;

                }
            }
        }

        String[] result = new String[i];

        for (int init = 0; init < i; init++){
            result[init] =values[init];
        }

        return result;
    }

    public static <T> String val(T message){
        return ""+message;
    }

    public static CarObject searchName(String searchName, CarObject... args){
        int i=0;
        CarObject str = null;
        for (CarObject value : args){
            i++;
            /*for (i; i<num; i++){*/
            if (value.customerName() == searchName) {
                System.out.println("searching name  "+value.customerName());
                return value;
            }
        }

        return str;
    }

    private static void changeValue(String result){
        resultValue = result;

    }

}
