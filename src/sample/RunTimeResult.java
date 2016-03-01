package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by srinath on 10/8/2015.
 */
public class RunTimeResult {
    static TableView table;
    public static void simulate() throws InterruptedException {

        Transaction R1 = new Transaction( "Charging");
        R1.run();



        Transaction R2 = new Transaction( "Discharging");
        //R2.start();

        while (R1.getExistCode()/* || R2.getExistCode()*/) {  Thread.sleep(500); }
        System.out.println("main object");
        CarObject[] carList = R1.getCar();
        table = new TableView<>();
        table = RandomGeneration.tableDisplay(carList);

        Stage window = new Stage();
        Button close = new Button("close");
        close.setOnAction(e -> window.close());
        TextField search = new TextField("4");
        Label searchlabel = new Label("Give the Id number to see graph");
        Button searchDeatilsButton = new Button("Show graph");
        searchDeatilsButton.setOnAction(e -> {
            int index = Integer.parseInt(search.getText());
            GraphDisplay.userChargeGraph(R1.getChargeArray(index), R1.getDisChargeArray(index), R1.getBatteryArray(index), index,carList[index]);
        });
        Button showAll = new Button("Show curves for all cars");
        Button powerInfo = new Button("Get Graph of Plant price and energy");

        powerInfo.setOnAction(event1 -> {

        });
        showAll.setOnAction(event -> {
            for (int i=0; carList[i] != null;i++) GraphDisplay.userChargeGraph(R1.getChargeArray(i), R1.getDisChargeArray(i), R1.getBatteryArray(i),i,carList[i]);
        });
        CompanyProfit[] companyDetails = R1.getCompanyDetails();

        Button companyProfitsButton = new Button("Graph of profits");
        companyProfitsButton.setOnAction(event -> {

            GraphDisplay.companyProfitGraph(companyDetails);
        });
        Button powerPLant = new Button("Power Plant Details");
        powerPLant.setOnAction(event -> {
            GraphDisplay.powerInfoGraph(R1.getPowerPlants());
        });

        Button spentMoneyButton = new Button("Spent Money Graph");


        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);
        HBox hBox1 = new HBox(10);
        hBox.getChildren().addAll(searchlabel,search,searchDeatilsButton);
        hBox1.getChildren().addAll(showAll, companyProfitsButton,powerPLant);
        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 10, 0, 12));
        hBox1.setPadding(new Insets(0, 10, 10, 12));
        vBox.getChildren().addAll(table,hBox,hBox1);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
       // RandomGeneration.deleteRow();
        window.show();

    }
}
