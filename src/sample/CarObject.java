package sample;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by srinath on 8/28/2015.
 */
public class CarObject implements Runnable{

    private Thread t;

    private String carName;
    private float battery;
    private boolean active;
    public String type;
    public Float addBattery;
    // private float price = (float) 0.0;
    private Float minPrice = (float) 0.0;
    private Float maxPrice = (float) 0.0;
    private Float minBattery = (float) 20.0;
    private Float maxBattery = (float) 80.0;
    private Timestamp entryTime;
    private Timestamp estimatedOutTime;

    public CarObject(){
        this.carName = "name";
        this.type = "cartype";
        this.addBattery =(float) 20.1;
        this.minBattery= (float)20.0;
        this.battery = (float) 10;
        // this.price = (float) 0.0;
        this.minPrice = (float) 0.0;
        this.maxPrice = (float) 0.0;
        this.estimatedOutTime = null;
        this.entryTime = null;

    }


    public CarObject(String name, float batterylife, String cartype, float addBattery){
        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
        this.minBattery= (float)20.0;
        this.battery = batterylife + addBattery;
        // this.price = (float) 0.0;
        this.minPrice = (float) 0.0;
        this.maxPrice = (float) 0.0;
        this.estimatedOutTime = null;
        this.entryTime = null;

    }
    public CarObject(String name, float batterylife, String cartype, float addBattery, float minPrice, float maxPrice) {
        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
        this.battery = batterylife + addBattery;
        //  this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.estimatedOutTime = null;
        this.entryTime = null;
        this.minBattery= (float)20.0;

    }

    public CarObject(String name, float batterylife, String cartype, float addBattery, float minPrice, float maxPrice, Timestamp entryTime, Timestamp existTime) {
        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
        this.battery = batterylife;
        // this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.estimatedOutTime = existTime;
        this.entryTime = entryTime;
        this.minBattery= (float)20.0;
        this.maxBattery = (float) 80.0;

    }


    public CarObject(String name, float batterylife, String cartype, float minPrice, float maxPrice, Timestamp entryTime, Timestamp existTime, float minBattery,float maxBattery,float addBattery) {


        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
        this.battery = batterylife;
        // this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.estimatedOutTime = existTime;
        this.entryTime = entryTime;
        this.minBattery= minBattery;
        this.maxBattery = maxBattery;

    }


    public void setAddBattery(){this.battery += this.addBattery;}

    public void setactivity(){      if (type=="Distributor") active = true;    }

    public String getType(){return this.type;}

    public void run(){  System.out.println("running "+ this.carName);     }

    public float batteryLife(){ return this.battery;   }

    public String getCarName(){  return this.carName;     }

    public void setCarName(String name){this.carName = name;}


    public Boolean activity(){
        return this.active;
    }

    public Float dischargeBatteryLife(float batteryLife){ return  (  this.battery>batteryLife  ? this.battery-batteryLife : null); }

    public Float addBatteryLife(float batteryLife){return (  batteryLife >0  ? this.battery+batteryLife : null);}

    public Float getMinBattery(){return this.minBattery;}
    public void setMinBattery(float n){this.minBattery = n;}
    public Float getAddBattery(){return this.addBattery;}
    public Float getMaxBattery(){return this.maxBattery;}
    public void setMaxBattery(float n){this.maxBattery = n;}

    /* public void selling(float n){
         this.price += n;
         return ;
     }
     public void getPrice(){ System.out.println("your price is : "+this.price);}
 */
    public String typeSell(){ return this.type;    }

    public void myData(){ System.out.println("carname "+this.carName+ " \tbatterylife "+this.battery+" \ttype of selling "+this.type+ "\tmin price : "+ this.minPrice+
            "\tMax price : "+this.maxPrice+"\t entrty time : "+ this.entryTime + "\t exist time : " + this.estimatedOutTime);    }

    public void setMinPrice(float n){this.minPrice = n;}
    public void setMaxPrice(float n){this.maxPrice = n;}
    public float getMinPrice(){return this.minPrice;}
    public float getMaxPrice(){return this.maxPrice;}

    public void printEntryTime(){ System.out.println("Entry time is : "+entryTime);}
    public void printExistTime(){System.out.println("Estimated outgoing time is : "+estimatedOutTime );}
    public Date getEntryTime(){return this.entryTime;}
    public Date getEstimatedOutTime(){return this.estimatedOutTime;}
    public void setEntryTime(Timestamp n){this.entryTime = n;}
    public void setExistTime(Timestamp n){this.estimatedOutTime =n;}


    public float getBattery(){return this.battery;}



    public void start(){
        System.out.println("Thread starting");

        if (t == null){
            System.out.println("thread is null");
            t = new Thread (this,carName);
            System.out.println("Thread now starting");
            t.start();
            System.out.println(t);
        }
    }

}