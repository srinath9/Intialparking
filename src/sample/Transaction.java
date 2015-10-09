package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by srinath on 10/8/2015.
 */
public class Transaction implements Runnable {
    private Thread t;
    private String threadName;
    private static CarObject[] carList = RandomDetails.setCarDetails();
    private static PowerPlant[] powerPlants = RandomDetails.setPlantDetails();
    private float[] array;

    TableView table;

    private static float[] tempMergArr;

    Transaction( String name){
        threadName = name;
       // System.out.println("Creating " +  threadName );
    }
    public void run() {
      //  printBatteryLevels();
        System.out.println("Running " +  threadName );
        try {
      //      System.out.println("before start");

            sort();
            chargeCar();
            //printPower();
            for(int i = 4; i > 0; i--) {
           //     System.out.println("Thread: " + threadName + ", " + i);

                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
            System.out.println("after start");
            printBatteryLevels();
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");

        table = new TableView<>();
        table = RandomGeneration.tableDisplay(carList);

        Stage window = new Stage();
        Button close = new Button("close");
        close.setOnAction(e-> window.close());

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(table);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public void start ()
    {
        System.out.println("Starting " + threadName);
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    private int findArrayLength() {
        int i;
        for (i = 0; carList[i] != null; i++) ;
        return i;
    }
    private void printBatteryLevels(){
        for (int i =0; carList[i] != null;i++)
            System.out.println(threadName+" :: "+carList[i].getCarId()+" : "+carList[i].getBatteryLevel());
    }


    public void sort() {
       /* if (threadName == "Charging")
             array = buyingArray(findArrayLength());
        else
             array = sellingArray(findArrayLength());*/

        bubblesort();
     //   printValues();
    }

    private void bubblesort(){
        CarObject temp ;

        if(threadName == "Charging") {
            for (int i = 0;i< findArrayLength()-1; i++)
                for (int j = 0; j<= findArrayLength()-i-2; j++) {
               //     System.out.println("j = "+j);
                    if (carList[j].getBuyPrice() > carList[j+1].getBuyPrice()) {
                        temp = carList[j];
                        carList[j] = carList[j+1];
                        carList[j+1] = temp;
                    }
                }
        }
        else {
            for (int i = 0;i< findArrayLength()-1; i++)
                for (int j = 0; j<= findArrayLength()-i-2; j++) {
                    if (carList[j].getBuyPrice() > carList[j+1].getBuyPrice()) {
                        temp = carList[j];
                        carList[j] = carList[j+1];
                        carList[j+1] = temp;
                    }
                }
        }

    }

    private void printValues(){
        if(threadName == "Charging") {
            for (int i = 0; carList[i] != null; i++) System.out.print(carList[i].getCarId()+" : "+carList[i].getBuyPrice()+ "\t");
            System.out.println("");
        }
        else {
            for (int i = 0; carList[i] != null; i++) System.out.print(carList[i].getSellingPrice()+ "\t");
            System.out.println("");
        }
    }

    private void chargeCar() throws InterruptedException {
        if (threadName == "Charging")
            for (int i=0; i<96;i++){
                setUserBuying(powerPlants[i].getPrice());
            //    System.out.println(" i :"+i);
                Thread.sleep(50);
            }
        else
            for (int i=0; i<96;i++) {
                setUserSelling(powerPlants[i].getPrice());
                Thread.sleep(50);
            }

    }

    private void setUserSelling(float priceLevel) throws InterruptedException {
       // System.out.println("selling");
        for(int i=0; carList[i] != null && carList[i].getSellingPrice() > priceLevel && carList[i].getBatteryLevel()>carList[i].getCriticalMinBattery();i++) {
            System.out.println("selling i : " + i);
            if (carList[i].getBatteryLevel() > 5) {
                carList[i].setDisCharge(5);
                System.out.println("selling i : " + i + " : " + carList[i].getBatteryLevel());
            }
            else carList[i].setBatteryLevel(0);
        }

    }
    private void setUserBuying(float priceLevel) throws InterruptedException {
     //   System.out.println("buying i : ");
        for(int i=0;carList[i] != null && carList[i].getBuyPrice() < priceLevel ;i++) {

            if (carList[i].getChargingType() == "slow") {
              //  System.out.print("buying i : " + i + " : " + carList[i].getBatteryLevel() + "\t");
                if (carList[i].getBatteryLevel() < 95) {
                    carList[i].setAddCharge(5);
                    System.out.println("buying i : " + i + " : " + carList[i].getBatteryLevel());
                }
                else carList[i].setBatteryLevel(100);
            }
            else {
                if (carList[i].getBatteryLevel() < 60) {
                    carList[i].setAddCharge(40);
                }
                carList[i].setBatteryLevel(100);
            }
        }
    }




}

