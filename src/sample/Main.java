package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    static float battery_left = 1000;

    Stage window;
    Scene scene1, scenen2;

    @Override
    public void start(Stage primaryStage) throws Exception{

        CarObject[] carList = new CarObject[100];


        window  = primaryStage;
        Label label1 = new Label("welcome ");

        Button button1 = new Button("go to scene 2");
        button1.setText("hi hello");
        button1.setOnAction(e -> window.setScene(scenen2));

        Button button2 = new Button("to back to scenen1");
        button2.setOnAction(e -> window.setScene(scene1));

        Button showdatabase = new Button("Show saved users");
        showdatabase.setOnAction(e -> ShowDetails.userCarDetails());
        VBox layout1 = new VBox(20);


        scene1 = new Scene(layout1,300,300);

        Button button = new Button("Add new car");
        Button rangenButton = new Button("Generate random 100 cars list");
        Button buyingPrice = new Button("present buying price");
        Button sellingPrice = new Button("present Selling price");
        Button transaction = new Button("Simulation");
        int i=0;



        button.setOnAction(e -> {

             /*carResult =*/
            NewCar.display("Battery Energy", "Add the Detials", carList[0]);
            /*System.out.print(carResult);*/

        });


        rangenButton.setOnAction(e -> {
            CarObject[] randomCars = RandomGeneration.display();

        });

        buyingPrice.setOnAction(e->{
          //  System.out.println("asdkfjbsdfkbsd asfkufhsdiuf hsdiuf ");
            SettingPrice.buyingprice();
        });
        transaction.setOnAction(e -> {
            try {

                RunTimeResult.simulate();

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        Button powerDetailsButton = new Button("Graph of Power plants");
        //powerDetailsButton.setOnAction(e-> GraphDisplay.powerInfoGraph());

        NewNeuralNetwork.NeuralNetwork();

      //  ScatterPoints.runData();

      // PolynomialCurve.makecurve();

   //     PatternRecognization.startPatternRecog();

        Button predictionButton = new Button("Prediction");
        predictionButton.setOnAction(e-> PredictionClass.intiate());

        sellingPrice.setOnAction(e-> SettingPrice.sellingPrice());


        layout1.setAlignment(Pos.CENTER);

        layout1.getChildren().addAll( button,rangenButton,transaction,predictionButton);

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);

        scenen2 = new Scene(layout2,400,350);


        window.setScene((scene1));
        window.setTitle("scene1");
       // PredictionClass.intiate();

     //   graph();
   //     window.show();



    }


    public static void main(String[] args) {
        launch(args);
    }


}
