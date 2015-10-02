package sample;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by srinath on 8/28/2015.
 */
public class CarObject{
    private String carName;
    
    private boolean active;
    public String type;

    // private float price = (float) 0.0;
    private Float minPrice = (float) 0.0;
    private Float maxPrice = (float) 0.0;
    private Float criticalMinBattery = (float) 20.0;
    private Float initialBattery = (float) 80.0;
    private Timestamp entryTime;
    private Timestamp stayTime;


    public CarObject(String name, String cartype ){
        this.carName = name;
        this.type = cartype;
        
        this.criticalMinBattery= (float)20.0;
       
        // this.price = (float) 0.0;
        this.minPrice = (float) 0.0;
        this.maxPrice = (float) 0.0;
        this.stayTime = null;
        this.entryTime = null;

    }
    public CarObject(String name, String cartype,  float minPrice, float maxPrice) {
        this.carName = name;
        this.type = cartype;
        //  this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.stayTime = null;
        this.entryTime = null;
        this.criticalMinBattery= (float)20.0;
    }

    public CarObject(String name, String cartype,  float minPrice, float maxPrice, Timestamp entryTime, Timestamp StayTime) {
        this.carName = name;
        this.type = cartype;
        // this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.stayTime = StayTime;
        this.entryTime = entryTime;
        this.criticalMinBattery= (float)20.0;
        this.initialBattery = (float) 80.0;

    }


    public CarObject(String name, String cartype, Float minPrice, Float maxPrice, Timestamp entryTime, Timestamp StayTime, Float criticalMinBattery,Float initialBattery) {
        this.carName = name;
        this.type = cartype;
        // this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.stayTime = StayTime;
        this.entryTime = entryTime;
        this.criticalMinBattery= criticalMinBattery;
        this.initialBattery = initialBattery;

    }


    public String getType(){return this.type;}

    public String getCarName(){  return this.carName;     }

    public void setCarName(String name){ this.carName = name;}

    public Boolean activity(){
        return this.active;
    }

    public Float getCriticalMinBattery(){return this.criticalMinBattery;}
    public void setCriticalMinBattery(float n){this.criticalMinBattery = n;}

    public Float getInitialBattery(){return this.initialBattery;}
    public void setInitialBattery(float n){this.initialBattery = n;}

    /* public void selling(float n){
         this.price += n;
         return ;
     }
     public void getPrice(){ System.out.println("your price is : "+this.price);}
 */
    public String typeSell(){ return this.type;    }

    public void myData(){ System.out.println("carname "+this.carName+ " \tbatterylife "+this.initialBattery+" \ttype of selling "+this.type+ "\tmin price : "+ this.minPrice+
            "\tMax price : "+this.maxPrice+"\t entrty time : "+ this.entryTime + "\t exist time : " + this.stayTime);    }

    public void setMinPrice(float n){this.minPrice = n;}
    public void setMaxPrice(float n){this.maxPrice = n;}
    public float getMinPrice(){return this.minPrice;}
    public float getMaxPrice(){return this.maxPrice;}
    public void printEntryTime(){ System.out.println("Entry time is : "+entryTime);}
    public void printStayTime(){System.out.println("Estimated outgoing time is : "+stayTime );}
    public Date getEntryTime(){return this.entryTime;}
    public Date getStayTime(){return this.stayTime;}
    public void setEntryTime(Timestamp n){this.entryTime = n;}
    public void setStayTime(Timestamp n){this.stayTime =n;}


}