package sample;

/**
 * Created by srinath on 10/9/2015.
 */
public class PowerPlant {
    private float price;
    private int id ;

    PowerPlant(){
        this.id = 1;
        this.price=0;
    }

    PowerPlant(int id,float price){
        this.id = id;
        this.price = price;
    }

    public float getPrice(){return this.price;}
    public int getId(){return this.id;}
}
