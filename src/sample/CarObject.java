package sample;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by srinath on 8/28/2015.
 */
public class CarObject{
    private String carName;
    private int carId;
    private String chargingType;
    private float batteryLevel;
    private float moneyEarned;
    private float moneyRequired;
    private float[] spentMoneyGraph = new float[96];
    private float[] earnedMoneyGraph = new float[96];
    private float chargingRate;
    private float disChargingRate;

    private boolean active;
    public String type;

    // private float price = (float) 0.0;
    private Float buyPrice = (float) 0.0;
    private Float sellingPrice = (float) 0.0;
    private Float criticalMinBattery = (float) 20.0;
    private Float initialBattery = (float) 80.0;
    private Timestamp plugInTime;
    private Timestamp existTime;
    private Float plugInDuration;


    public CarObject(String name, String cartype ){
        this.carName = name;
        this.type = cartype;
        this.carId = 1;
        this.chargingType = "slow";
        
        this.criticalMinBattery= (float)20.0;
       
        // this.price = (float) 0.0;
        this.buyPrice = (float) 0.0;
        this.sellingPrice = (float) 0.0;
        this.existTime = null;
        this.plugInTime = null;
        this.plugInDuration = (float) 0.0;
        this.batteryLevel = this.initialBattery;
        this.moneyEarned = 0;
        this.moneyRequired = 0;
        this.disChargingRate = 0;
        this.chargingRate = 0;

    }
    public CarObject(String name, String cartype,  float buyPrice, float sellingPrice) {
        this.carName = name;
        this.type = cartype;
        //  this.price = (float) 0.0;
        this.buyPrice = buyPrice;
        this.sellingPrice = sellingPrice;
        this.existTime = null;
        this.plugInTime = null;
        this.criticalMinBattery= (float)20.0;
        this.plugInDuration = (float) 0.0;
        this.chargingType = "slow";
        this.batteryLevel = this.initialBattery;
        this.carId = 1;
        this.moneyEarned =0;
        this.moneyRequired = 0;
        this.disChargingRate = 0;
        this.chargingRate = 0;

    }

    public CarObject(String name, String cartype,  float buyPrice, float sellingPrice, Timestamp plugInTime, Timestamp ExistTime) {
        this.carName = name;
        this.type = cartype;
        // this.price = (float) 0.0;
        this.buyPrice = buyPrice;
        this.sellingPrice = sellingPrice;
        this.existTime = ExistTime;
        this.plugInTime = plugInTime;
        this.criticalMinBattery= (float)20.0;
        this.initialBattery = (float) 80.0;
        this.plugInDuration = (float) 0.0;
        this.chargingType = "slow";
        this.carId = 1;
        this.batteryLevel = this.initialBattery;
        this.moneyEarned =0;
        this.moneyRequired = 0;
        this.disChargingRate = 0;
        this.chargingRate = 0;

    }


    public CarObject(String name, String cartype, Float buyPrice, Float sellingPrice, Timestamp plugInTime, Timestamp ExistTime,Float initialBattery, Float criticalMinBattery, String chargeType,Float chargingRate, Float disChargingRate) {
        this.carName = name;
        this.type = cartype;
        // this.price = (float) 0.0;
        this.buyPrice = buyPrice;
        this.sellingPrice = sellingPrice;
        this.existTime = ExistTime;
        this.plugInTime = plugInTime;
        this.criticalMinBattery= criticalMinBattery;
        this.initialBattery = initialBattery;
        this.plugInDuration = (float) 0.0;
        this.chargingType = chargeType;
        this.batteryLevel = this.initialBattery;
        this.carId = 1;
        this.moneyEarned =0;
        this.moneyRequired = 0;
        this.disChargingRate = disChargingRate ;
        this.chargingRate = chargingRate;



    }


    public String getType(){return this.type;}

    public String getCarName(){  return this.carName;     }

    public void setCarName(String name){ this.carName = name;}

    public float getMoneyEarned(){return this.moneyEarned;}
    public void addMoneyEarned(float n){this.moneyEarned += n;}

    public float getMoneyRequired(){return this.moneyRequired;}
    public void addMoneyRequired(float n){this.moneyRequired +=n;}
    public float[] getSpentMoneyGraph(){return this.spentMoneyGraph;}
    public float[] getEarnedMoneyGraph(){return this.earnedMoneyGraph;}

    public void addSpentMoneyGraph(float n, int id){this.spentMoneyGraph[id] =n; }
    public void addEarnedMoneyGraph(float n, int id){this.earnedMoneyGraph[id] =n; }

    public Float getCriticalMinBattery(){return this.criticalMinBattery;}
    public void setCriticalMinBattery(float n){this.criticalMinBattery = n;}

    public Float getInitialBattery(){return this.initialBattery;}
    public void setInitialBattery(float n){this.initialBattery = n;}

    public void setChargingType(String type){this.chargingType = type;}
    public String getChargingType(){return this.chargingType;}

    public void setAddCharge(float n){ this.batteryLevel += n;}
    public void setDisCharge(float n){ this.batteryLevel -= n;}

    public float getBatteryLevel(){return this.batteryLevel;}
    public void setBatteryLevel(float n){this.batteryLevel = n;}
    public String typeSell(){ return this.type;    }

    public void myData(){ System.out.println("carname "+this.carName+ " \tbatterylife "+this.initialBattery+" \ttype of selling "+this.type+ "\tmin price : "+ this.buyPrice+
            "\tMax price : "+this.sellingPrice+"\t entrty time : "+ this.plugInTime + "\t exist time : " + this.existTime);    }

    public void setBuyPrice(float n){this.buyPrice = n;}
    public void setSellingPrice(float n){this.sellingPrice = n;}
    public float getBuyPrice(){return this.buyPrice;}
    public float getSellingPrice(){return this.sellingPrice;}
    public void printPlugInTime(){ System.out.println("Entry time is : "+plugInTime);}
    public void printExistTime(){System.out.println("Estimated outgoing time is : "+existTime );}
    public Timestamp getPlugInTime(){return this.plugInTime;}
    public Timestamp getExistTime(){return this.existTime;}
    public void setPlugInTime(Timestamp n){this.plugInTime = n;}
    public void setExistTime(Timestamp n){this.existTime =n;}

    public void setPlugInDuration(float n){ this.plugInDuration=n;}

    public Float getPlugInDuration(){return this.plugInDuration;}

    public int getCarId(){return this.carId;}
    public void setCarId(int n){this.carId = n;}

    public float getChargingRate(){return  this.chargingRate;}
    public float getDisChargingRate(){return this.disChargingRate;}



}