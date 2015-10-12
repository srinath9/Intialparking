package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by srinath on 10/8/2015.
 */
public class Transaction  {
    private Thread t;
    private String threadName;
    private static CarObject[] carList = RandomDetails.setCarDetails();
  /*  private static CarObject[] carList =carList;
    private static CarObject[] carList =carList;*/
    private CarObject[] car;
    private static PowerPlant[] powerPlants = RandomDetails.setPlantDetails();
    private float[] array;
    private boolean existCode;
    public static float[][] chargeArray = new float[100][96];
    public static float[][] disChargeArray = new float[100][96];
    public static float[][] batteryArray = new float[100][96];

    Transaction( String name){
        threadName = name;
        this.car = carList;
        this.existCode = true;
       // System.out.println("Creating " +  threadName );
    }
    public boolean getExistCode(){return this.existCode;}
    public void setExistCode(boolean code){this.existCode = code;}
    public CarObject[] getCar(){ return this.car;}
    public void run() {
      //  printBatteryLevels();
        System.out.println("Running " + threadName);
        chargeCar();
            //printPower();
        System.out.println("after start");
       // printBatteryLevels();

        System.out.println("Thread " + threadName + " exiting.");
        setExistCode(false);           // existing the thread
    }

    public float[] getDisChargeArray(int n){ return disChargeArray[n];}
    public float[] getChargeArray(int n){return chargeArray[n];}
    public float[] getBatteryArray(int n){return batteryArray[n];}

    private void chargeCar()  {
        Timestamp presentTime;
        int[] buyArray = buyBubblesort();
        int[] sellArray = sellBubblesort();



        for (int i = 0; i < 96; i++) {
            System.out.println("enter loop "+i);
            presentTime = slotTime(i);
            System.out.println(presentTime);
           /* setUserBuying(powerPlants[i].getPrice(),presentTime,buyArray);*/
            setUserBuying(powerPlants[i],buyArray,chargeArray);
            setUserSelling(powerPlants[i],sellArray,disChargeArray);
                //    System.out.println(" i :"+i);

            }
        //GraphDisplay.graph(chargeArray[1],disChargeArray[1],batteryArray[1]);
        System.out.println(" exist charging");
    }

    private void setUserSelling(PowerPlant plant, int[] sellArray, float[][] disChargeArray){
        int loopnum = plant.getId();
        float priceLevel = plant.getPrice();
        Timestamp timeSlot = slotTime(loopnum);
        System.out.println("selling price level : " + priceLevel);
       // System.out.println("selling");
        for(int k=0;k<sellArray.length;k++) {
            if (carList[sellArray[k]] != null) {
                int i = sellArray[k];

                float temp = carList[i].getBatteryLevel() - carList[i].getCriticalMinBattery();
                if (validTime(carList[i], timeSlot)) {
                    System.out.println("valid Time");
                    userSellEnergyAvailability(carList[i], plant,disChargeArray[i],batteryArray[i]);
                    System.out.println("selling i  : " + i + " : " + carList[i].getBatteryLevel() + " battery : " + carList[i].getCriticalMinBattery());
                }
            }
        }
        System.out.println("");
    }

    private void setUserBuying(PowerPlant plant, int[] buyArray, float[][] chargeArray) {
        int loopnum = plant.getId();
        float priceLevel = plant.getPrice();
        Timestamp timeSlot = slotTime(loopnum);
        System.out.println("buying price level : "+ priceLevel);
     //   for (int m=0; m<buyArray.length;m++) System.out.print(buyArray[m]+"\t");


        for(int k=0;k<buyArray.length;k++) {
            if (carList[buyArray[k]] != null) {
                int i = buyArray[k];
                if (validTime(carList[i], timeSlot)) {
                  //  System.out.println("valid Time");
                   // System.out.println("buying i : " + i + " : " + carList[i].getBatteryLevel() + " battery : " + carList[i].getCriticalMinBattery());
                    if (carList[i].getBuyPrice() > priceLevel) {
                        System.out.println("buying i  : " + i + " : " + carList[i].getBatteryLevel() + " battery : " + carList[i].getCriticalMinBattery());
                        userBuyEnergyAvailability(carList[i],plant,chargeArray[i],batteryArray[i]);
                        System.out.println("buying i  : " + i + " : " + carList[i].getBatteryLevel() + " battery : " + carList[i].getCriticalMinBattery());
                       /* if (carList[i].getChargingType() == "slow") {
                            //  System.out.print("buying i : " + i + " : " + carList[i].getBatteryLevel() + "\t");
                            if (carList[i].getBatteryLevel() < 95) {
                                //   System.out.println("before buying i : " + i + " : " + carList[i].getBatteryLevel());
                                carList[i].setAddCharge(5);
                                System.out.println("buying i : " + i + " : " + carList[i].getSellingPrice() + " : " + carList[i].getBatteryLevel());
                            } else carList[i].setBatteryLevel(100);
                        } */



                    }/*
                        else {
                            if (carList[i].getBatteryLevel() < 60) {
                                carList[i].setAddCharge(40);
                            }
                            carList[i].setBatteryLevel(100);
                        }*/
                }
            }
        }

        System.out.println("");
    }

    private void userBuyEnergyAvailability(CarObject car,PowerPlant plant, float[] chargeArray,float[] batterycharge){
        float energy = plant.getEnergyAvailable();
        int id = plant.getId();
        if(car.getChargingType() == "slow") {
            if (energy >= 5) {
                if (car.getBatteryLevel() < 95) {
                    System.out.println("plant energy available is " + energy);
                    car.setAddCharge(5);
                    plant.disChargeEnergy(5);
                    chargeArray[id] = 5;

                    System.out.println("plant energy available is " + plant.getEnergyAvailable());
                    //             System.out.println("buying i : "  + " : " + car.getSellingPrice() + " : " + car.getBatteryLevel());
                } else {
                    System.out.println("plant energy available is " + energy);
                    plant.disChargeEnergy(car.getBatteryLevel() - 95);
                    chargeArray[plant.getId()] = car.getBatteryLevel() - 95;
                    car.setBatteryLevel(100);

                }


            } else if ((energy > 0) && (energy < 5)) {
                System.out.println("plant energy available is " + energy);
                System.out.println("plant energy available is  completelety zero             ");
                car.setAddCharge(energy);
                chargeArray[plant.getId()] = plant.getEnergyAvailable();
                plant.setEnergyAvailable(0);

            }
        }
        else if(car.getChargingType() == "fast"){
            if (energy  >= 40) {
                plant.disChargeEnergy(40);
                car.setAddCharge(40);
                chargeArray[plant.getId()] = 40;

            }
            else if ((energy >0) && (energy < 40) ) {
                car.setAddCharge(energy);
                chargeArray[plant.getId()] = energy;
                plant.setEnergyAvailable(0);

                }
            }

        batterycharge[id] = car.getBatteryLevel();

    }

    private void userSellEnergyAvailability(CarObject car,PowerPlant plant, float[] disChargeArray,float[] batterycharge) {
        float priceLevel = plant.getPrice();
        int slotnum = plant.getId();

        //for (int i = 0; i<96;i++) System.out.print(disChargeArray[i]+"\t");
        float temp = car.getBatteryLevel() - car.getCriticalMinBattery();
        if (car.getSellingPrice() < priceLevel) {
            if (temp > 5) {
                System.out.println("valid selling i : " +car.getCarId()  + " : " + car.getBatteryLevel() + " battery : " + car.getCriticalMinBattery());
                if (car.getBatteryLevel() > 5) {
                    car.setDisCharge(5);
                    disChargeArray[slotnum] = -5;

                 //   System.out.println("selling i : " + i + " : " + car.getBuyPrice() + " : " + car.getBatteryLevel());
                } else car.setBatteryLevel(0);
            } else if (temp > 0 && temp < 5) {
                System.out.println("valid selling less amount i : " +car.getCarId()  + " : " + car.getBatteryLevel() + " battery : " + car.getCriticalMinBattery());
                car.setBatteryLevel(car.getCriticalMinBattery());
                disChargeArray[slotnum] = -temp;
            }
        }
        batterycharge[slotnum] = car.getBatteryLevel();


    }

    public Timestamp slotTime(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        int currntTimeAdd = 900000*n;
        java.sql.Timestamp slotTime = new java.sql.Timestamp(calendar.getTime().getTime() + currntTimeAdd);

        return slotTime;

    }

    private boolean validTime(CarObject car, Timestamp t1){
        if (car.getPlugInTime().getTime() < t1.getTime()) {
          //  System.out.println("prop time");
            if (car.getExistTime().getTime() > t1.getTime()) return true;
        }
        else{
          //  System.out.println("else Time");
            return false;
        }

        return true;
    }



    private int findArrayLength() {
        int i;
        for (i = 0; carList[i] != null; i++){

        }
        return i;
    }

    private int[] buyBubblesort(){
        System.out.println("bubble sort :                     ksdjfbhskd jfsaf sdf sdfsda fs");
        float temp ;
        int num = findArrayLength();
        int[] buyArray = new int[num];
        float[] checkList =new float[num];
        for (int k=0; k<num; k++){
            checkList[k] = carList[k].getBuyPrice();
            buyArray[k] = k;

            System.out.print(checkList[k]+"\t");
        }

        for (int i = 0;i< num-1; i++)
            for (int j = 0; j<= findArrayLength()-i-2; j++) {
            //     System.out.println("j = "+j);
                if (checkList[j] > checkList[j+1]) {
                    int k = buyArray[j];
                    buyArray[j] =buyArray[j+1];
                    buyArray[j+1] =k;
                    temp = checkList[j];
                    checkList[j] = checkList[j+1];
                    checkList[j+1] = temp;
                }
            }
        for (int j=0; j< num;j++)  System.out.print(buyArray[j]+" : "+carList[buyArray[j]].getBuyPrice() +"\t");
        return buyArray;
    }

    private boolean checkEnergyAvailable(CarObject car, float energy){
        if (car.getBatteryLevel() > energy){

        }
        return true;
    }


    private int[] sellBubblesort(){
        float temp ;
        int num = findArrayLength();
        int[] sellArray = new int[num];
        float[] checkList =new float[num];
        for (int k=0; k<num; k++){
            checkList[k] = carList[k].getSellingPrice();
            sellArray[k] = k;
        }
        for (int i = 0;i< findArrayLength()-1; i++)
            for (int j = 0; j<= findArrayLength()-i-2; j++) {
                if (checkList[j] > checkList[j+1]) {
                    int k = sellArray[j];
                    sellArray[j] =sellArray[j+1];
                    sellArray[j+1] =k;
                    temp = checkList[j];
                    checkList[j] = checkList[j+1];
                    checkList[j+1] = temp;
                }
            }

        for (int j=0; j< num;j++)  System.out.print(sellArray[j]+"\t");

        return sellArray;
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


    /*
    private void chargeCar() throws InterruptedException {
        if (threadName == "Charging") {
            for (int i = 0; i < 96; i++) {
                setUserBuying(powerPlants[i].getPrice());
                //    System.out.println(" i :"+i);
                Thread.sleep(100);
            }
            System.out.println(" exist charging");
        }
        else {
            for (int i = 0; i < 96; i++) {
                setUserSelling(powerPlants[i].getPrice());
                Thread.sleep(100);
            }
            System.out.println(" exist discharging");
        }

    }

    */



}

