package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created by srinath on 9/4/2015.
 */
public class SettingPrice {
    static private String resultValue;

    public static void display(String title, String message, int i, CarObject car){
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
        final float sellvalue = RandomClass.sellingPrice();
        Label selllabel = new Label("this is the selling price available now : "+ sellvalue);

        Label minPricelabel = new Label("set the min price below to start charge");
        Label maxPricelabel = new Label("Set the max prices value above which you want to sell");

        float minvalue = (float) RandomClass.integerValue(0);

        TextField minPrice = new TextField(String.valueOf(minvalue/10));
        TextField maxPrice = new TextField(String.valueOf(RandomClass.greaterthanmin(minvalue)));
     //   System.out.println("min price is : " + minPrice.getText() + " max price is : " + maxPrice.getText());
        minPrice.setMaxWidth(100);
        maxPrice.setMaxWidth(100);

        batterylabel.setText("Present Parking lot purchase Price is : " + sellvalue);



        Boolean active;
        active = false;

        comboBox = new ComboBox<String>();
       // comboBox.getItems().addAll(
      //          objecttoString( car));
        /*comboBox.setPromptText("What is your Type you want?");*/




        Button yes = new Button("Sell ");
        Button no = new Button("Dont sell");

        no.setOnAction(e-> window.close());


        yes.setOnAction(
                e -> {
                   // userName[0] = comboBox.getValue();
                    // CarObject carObject =  searchName(userName[0],car);
                   // changeValue(userName[0]);
                   // //System.out.println("the customer is  "+carObject.customerName());

                    // int sellvalue = RandomClass.sellingPrice();
                 //   car.selling(sellvalue);
                //    car.getPrice();
                    car.setMinPrice(Float.parseFloat(val(minPrice.getText())));
                    car.setMaxPrice(Float.parseFloat(val(maxPrice.getText())));


                    System.out.println("min price " + car.getMinPrice() + " max price" + car.getMaxPrice());

                    UserInfo.display(car);

                    
                    window.close();
                }
        );


        Label label1 = new Label();
        /*closeButton.setOnAction(e -> window.close());*/
        label1.setText(message);

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
        layout2.getChildren().addAll(minPricelabel,maxPricelabel);

        layout.setAlignment(Pos.CENTER);

        layout5.getChildren().addAll(layout2, layout3);
        layout5.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(batterylabel, layout5, layout4);
        Scene scene = new Scene(layout);
        window.setTitle("Adding from Seller");
        window.setScene(scene);

        System.out.println("before closing " + resultValue);
        window.showAndWait();

        //return resultValue;



    }

    private static void objecttoString(CarObject... args){
        String[] values = new String[10];
        int i =0;
        for (CarObject value : args){
            /*for (i; i<num; i++){*/

            if ((value != null) /*&& */) {
                if ((Objects.equals(value.typeSell(), "Seller"))) {


                    System.out.println("type of selling process : "+value.typeSell() + " custoimer to " + value.getCarName());

                    values[i] = value.getCarName();
                    i++;

                }
            }
        }

        String[] result = new String[i];

        for (int init = 0; init < i; init++){
            result[init] =values[init];
        }

        //return result;
    }

    public static <T> String val(T message){
        return ""+message;
    }

    /*public static CarObject searchName(String searchName, CarObject... args){
        int i=0;
        CarObject str = null;
        for (CarObject value : args){
            i++;

            if (value.customerName() == searchName) {
                System.out.println("searching name  "+value.customerName());
                return value;
            }
        }

        return str;
    }*/

    private static void changeValue(String result){
        resultValue = result;

    }

}
