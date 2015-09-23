package sample;

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
    private Date entryTime;
    private Date estimatedOutTime;


    public CarObject(String name, float batterylife, String cartype, float addBattery){
        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
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

    }

    public CarObject(String name, float batterylife, String cartype, float addBattery, float minPrice, float maxPrice, Date entryTime, Date existTime) {
        this.carName = name;
        this.type = cartype;
        this.addBattery = addBattery;
        this.battery = batterylife + addBattery;
       // this.price = (float) 0.0;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.estimatedOutTime = existTime;
        this.entryTime = entryTime;

    }

    public void setactivity(){      if (type=="Distributor") active = true;    }

    public String getType(){return this.type;}

    public void run(){  System.out.println("running "+ this.carName);     }

    public float batteryLife(){ return this.battery;   }

    public String getCarName(){  return this.carName;     }

    public Boolean activity(){
        return this.active;
    }

    public Float dischargeBatteryLife(float batteryLife){ return  (  this.battery>batteryLife  ? this.battery-batteryLife : null); }

    public Float addBatteryLife(float batteryLife){return (  batteryLife >0  ? this.battery+batteryLife : null);}


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
    public void setEntryTime(Date n){this.entryTime = n;}
    public void setExistTime(Date n){this.estimatedOutTime =n;}




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