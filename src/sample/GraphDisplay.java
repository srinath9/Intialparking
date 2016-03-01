package sample;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

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
        final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

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
           // if (batteryArray[i] >0 )
                series3.getData().add(new XYChart.Data(""+i, batteryArray[i]));
        }


        Scene scene  = new Scene(lineChart,800, 600);
     //   bc.getStyle("  -fx-background-color: aqua;");

        lineChart.getData().addAll(series1, series2, series3);
       // Scene scene1  = new Scene(bc, 800,600);

        bc.getData().addAll(series3);



        //scene.getStylesheets().clear();
        String workingDirectory = System.getProperty("user.dir");
        String css = workingDirectory + File.separator +"src"+'\\' +"sample"+'\\' + "style.css";

        //scene.getStylesheets().add("csv_data/charts.css");
        stage.setScene(scene);
       // stage.getScene().getStylesheets().add("style.css");
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
        for (int i=0;i<96;i++) {
            if (plant[i].getEnergyAvailable() - plant[i].getInitialEnergy() != 0) {
                series3.getData().add(new XYChart.Data("" + i, plant[i].getEnergyAvailable()));
            }
        }


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("price Set ");

        //Transaction.printPowerPlant();
        for (int i = 0; i < 96;i++){
            series2.getData().add(new XYChart.Data(""+i, plant[i].getPrice()));
        }

        bc.setTitle("Power Monitoring");

        Scene scene  = new Scene(lineChart,800,600);

        lineChart.getData().addAll(series1, series3,series2 );

        bc.getData().addAll(series1, series3 );

        stage.setScene(scene);
        stage.show();

        NumberFormat format = new DecimalFormat();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });


    }
    public static void predictiongraph(double[] actual, double[] predicted, double[][] trainInput,String title){

        Stage stage = new Stage();
       // final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final NumberAxis xAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

      //  final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
      //  BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        NumberFormat format = new DecimalFormat();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        XYChart.Series series1 = new XYChart.Series();
        //XYChart.Series serie = new XYChart.Series();
        XYChart.Series[] seri = new XYChart.Series[4];

        series1.setName("Actual values ");
        System.out.println("Actual val");
        for (int i =0;i<96;i++){

            series1.getData().add(new XYChart.Data(i, actual[i]*4440));
            System.out.print(actual[i] * 4440 + "\t");
        }

       // serie.getData().add(new XYChart.Data("" + 1, 1000));
       // for(Series<String,Double> item:seri) {


        XYChart.Series series3 = new XYChart.Series();
        System.out.println("\npredicted ");
        series3.setName("Predicted values ");
        for (int i = 0; i < 96; i++) {
            series3.getData().add(new XYChart.Data(i, predicted[i]*4440));
            System.out.print(predicted[i] + "\t");
        }


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("price Set ");
      //  bc.setTitle("Power Monitoring");

        Scene scene  = new Scene(lineChart, 800,600);
      //  lineChart.setData(getChartData());

        double val = 0;
        System.out.println();
        System.out.println("Input values");
        for (int num=0; num< 4;num++){
            XYChart.Series serie = new XYChart.Series();
            for(int slot=0;slot<96;slot++){
                // item[i] = series1;
               // val = trainInput[num][slot] * 4440;
                val = trainInput[slot][num]* 4440;
                serie.getData().add(new XYChart.Data(slot,val ));
            //    System.out.print(val+"\t");

            }
            serie.setName("Input values " + num);
            System.out.println();
            lineChart.getData().add(serie);
            serie = null;
        }

       // System.out.println("actual vals");

        //for (int k=0;k<96;k++)


        lineChart.getData().addAll(series1, series3);
        //lineChart.


        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();


    }


    public static void scatterGraph(double[][] values,String title,double[][] mean,double[][] std){
        Stage stage = new Stage();
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Slots");
        yAxis.setLabel("Price");
        sc.setTitle("Power Price ");
        int week=0,weekday=0,slot=0;
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Mean Values");
        series2.setName("Std Values");
        XYChart.Series series;
        NumberFormat format = new DecimalFormat();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        Rectangle rect = new Rectangle(1,1);
        rect.setVisible(false);

           // series = new XYChart.Series();
     //   System.out.print("title"+title);

            week=0;
            for (;week<21;week++) {
                slot=0;
                series = new XYChart.Series();
                for (; slot < 96; slot++) {
                    XYChart.Data data = new XYChart.Data(slot, values[week][slot]);
                    data.setNode(rect);
                    series.getData().add(data);
/*
                    if (title =="3" || title=="4" || values[week][slot] <600 ){
                        System.out.print(title+" "+values[week][slot]+"\t");
                    }
*/
                }
                series.setName("slot week "+week);

                lineChart.getData().add(series);
              //  System.out.println("week " + week);
                series = null;

            }

        for (slot=0; slot < 96; slot++) {
            XYChart.Data data =new XYChart.Data(slot,mean[weekday][slot]);
          //  data.setNode(rect);
            series1.getData().add(data);
        }
        for (slot=0; slot < 96; slot++){
            XYChart.Data data =new XYChart.Data(slot,std[weekday][slot]);
          //  data.setNode(rect);
            series2.getData().add(data);
        }


        lineChart.getData().addAll(series1, series2);

        //lineChart.setNode(rect);

        String css = "csv_data\\charts.css";

        String workingDirectory = System.getProperty("user.dir");

        //****************//

        String day = "";

        //absoluteFilePath = workingDirectory + System.getProperty("file.separator") + filename;
        day = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + css;


        Scene scene  = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("\n" +
                ".root{\n" +
                "    -fx-background-color: aqua;\n" +
                "}");

    //    String css = DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
        scene.getStylesheets().clear();
    //    scene.getStylesheets().add("http://www.jpedal.org/simon/dynamiccss/webcss.css");
        scene.getStylesheets().add("styling.css");
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

      //  for (int k=0;k<5;k++) everySlot(values,k);


    }

    public static void testErrorGraph(PolynomialFunction fitted, double[] actualValues, int polyStartIndex,double newOuput) {
        Stage stage = new Stage();
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        // final NumberAxis yAxis =  new NumberAxis(0,5000,500);
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Slots");
        yAxis.setLabel("Price");
        xAxis.setVisible(false);
        xAxis.setTickMarkVisible(true);
        yAxis.setTickMarkVisible(true);
        System.out.println(xAxis.tickMarkVisibleProperty() + "  :     axis property ");
        //     yAxis.setVisible(false);
        //  yAxis.setTickLabelFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        //    xAxis.setTickLabelFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        //  xAxis.setTickLabelFill(Paint.valueOf("rgb(102,102,204)"));

        //   NumberFormat format = new DecimalFormat("#.#E0");
        NumberFormat format = new DecimalFormat();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        int week=0,weekday=0,slot=0;
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Actual Values");
        series2.setName("Testing Values");
        //  slot = polyStartIndex;
        for (; slot < 52; slot++) {
            XYChart.Data data =new XYChart.Data(slot,actualValues[slot]);
            series1.getData().add(data);
        }
        series1.getData().add(new XYChart.Data(slot,newOuput));


    }

    public static void patternPrediction(ArrayList<ArrayList<Double>> plotPatAr ){
        Stage stage = new Stage();
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        // final NumberAxis yAxis =  new NumberAxis(0,5000,500);
        final NumberAxis yAxis =  new NumberAxis();

        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Weeks");
        yAxis.setLabel("Percentage change in Price");

        System.out.println("inside  graph");
        NumberFormat format = new DecimalFormat();

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        int num =0;
        System.out.print("\n\t "+plotPatAr);
        for (ArrayList<Double> eachPattern : plotPatAr) {
      //      System.out.print("\n\t showing data");

            XYChart.Series serie = new XYChart.Series();
            for (int i = 0; i < eachPattern.size(); i++) {
                serie.getData().add(new XYChart.Data(i, eachPattern.get(i)));

       //         System.out.print(eachPattern.get(i)+"\t\t");
            }
            serie.setName("pat "+num);
            num++;
            lineChart.getData().add(serie);
            serie = null;

        }

        System.out.print("\n\t showing data");

        Scene scene  = new Scene(lineChart, 800, 600);
        scene.getStylesheets().clear();

        //  stage.setTitle(""+fitted.degree());
        stage.setScene(scene);
        stage.show();


    }


    public static void annpredict(double[] actualValues, double[] predictVal,int title ,int weekday, double[][][] trainInputs, double[] previous){
        Stage stage = new Stage();
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
       // final NumberAxis yAxis =  new NumberAxis(0,5000,500);
        final NumberAxis yAxis =  new NumberAxis();

        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("weeks");
        yAxis.setLabel("Price");


        NumberFormat format = new DecimalFormat();
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return format.format(number.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        sc.setTitle("Power Price ");
        int week=0,slot=0;
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Actual Values");
        series2.setName("Predicted Values");
        series3.setName("Previous year Values");
      //  slot = polyStartIndex;
        for (week=0; week < 48; week++) {
            XYChart.Data data = new XYChart.Data(week, actualValues[week]);
            series1.getData().add(data);

            series2.getData().add( new XYChart.Data(week, predictVal[week]));

            series3.getData().add(new XYChart.Data(week, previous[week]));
            //      System.out.print("fitted values \n"+fitted.value(slot)+"\t");

        }
        lineChart.getData().addAll(series1, series2,series3);


      //  System.out.println("error values");
     //   for (int j=0;j<52;j++) System.out.println((actualValues[j]-predictVal[j])/actualValues[j]);

        double val;






     //   System.out.println("Input values");
        /*    for ( int num =0; num<3;num++){
                int i=0;
                XYChart.Series serie = new XYChart.Series();
                for ( week = 2;week<52;week++){
                    val = NewNeuralNetwork.denormalizeValue((Double) trainInputs[weekday][week-num][title]);
                    serie.getData().add(new XYChart.Data(week, val));
                }
                i++;
                serie.setName("Input values " + num);
                System.out.println();
                lineChart.getData().add(serie);
                serie = null;
            }


*/
        Scene scene  = new Scene(lineChart, 800, 600);
        stage.setTitle(""+title);
        scene.getStylesheets().clear();

      //  stage.setTitle(""+fitted.degree());
        stage.setScene(scene);
        stage.show();

    }


}
