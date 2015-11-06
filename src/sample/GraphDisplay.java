package sample;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

/**
 * Created by srinath on 10/12/2015.
 */
public class GraphDisplay {
    static PowerPlant[] plants = RandomDetails.setPlantDetails();

    public static void userChargeGraph(float[] chargeArray, float[] disChargeArray,float[] batteryArray, int title, CarObject car) {
        Stage stage = new Stage();
        stage.setTitle("Line Chart Sample of " + title);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Car Charging and Discharging");

        XYChart.Series series1 = new XYChart.Series();
        float sum = 0;
        series1.setName("Money spent");
        for (int i=0;i<96;i++){
            sum += car.getSpentMoneyGraph()[i];
            series1.getData().add(new XYChart.Data(""+i, sum));
        }


        /*
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));*/

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Money Earned ");
        float earn =  0;
        for (int i=0;i<96;i++) {
            earn +=car.getEarnedMoneyGraph()[i];
            series2.getData().add(new XYChart.Data(""+i, earn));
        }

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Battery Levels");
        for (int i=0;i<96;i++){
       //     System.out.print(" battery level : "+batteryArray[i] +"\t ");
            series3.getData().add(new XYChart.Data(""+i, batteryArray[i]));
        }


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series3,series1,series2);

        stage.setScene(scene);
        stage.show();
    }

    public  static void companyProfitGraph(CompanyProfit[]  companyDetails){
        Stage stage = new Stage();
        stage.setTitle( "Company profits");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Company Details Monitoring");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Company Profits");
        float profit = 0;
        for (int i=0;i<96;i++){
            profit +=  companyDetails[i].getProfit();
            series1.getData().add(new XYChart.Data(""+i,profit));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);

        stage.setScene(scene);
        stage.show();


    }

    public static void powerInfoGraph(PowerPlant[] plant){
        Stage stage = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Initial Energy Available ");
        for (int i =0;i<96;i++) series1.getData().add(new XYChart.Data(""+i, plant[i].getInitialEnergy()));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Final Energy Available ");
        for (int i=0;i<96;i++) series3.getData().add(new XYChart.Data(""+i, plant[i].getEnergyAvailable()));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("price Set ");

        //Transaction.printPowerPlant();
        for (int i = 0; i < 96;i++){
            series2.getData().add(new XYChart.Data(""+i, plant[i].getPrice()));
        }

        bc.setTitle("Power Monitoring");

        Scene scene  = new Scene(lineChart,800,600);

        lineChart.getData().addAll(series1, series3,series2 );


        stage.setScene(scene);
        stage.show();


    }




}
