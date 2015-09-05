package sample;

/**
 * Created by srinath on 8/28/2015.
 */
public class CarObject implements Runnable{

    private Thread t;

    private String carName;
    private float battery;
    private boolean active;
    public String type;


    public CarObject(String name, float batterylife, String cartype){
        this.carName = name;
        this.battery = batterylife;
        this.type = cartype;


    }



    public void setactivity(){
        if (type=="Distributor") active = true;
    }

    public void run(){
        System.out.println("running "+ this.carName);

    }

    public float batteryLife(){
        return this.battery;
    }

    public String customerName(){
        return this.carName;
    }

    public Boolean activity(){
        return this.active;
    }


    public String typeSell(){
        return this.type;
    }




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