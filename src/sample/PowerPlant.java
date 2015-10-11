package sample;

/**
 * Created by srinath on 10/9/2015.
 */
public class PowerPlant {
    private float price;
    private int id ;
    private float energyAvailable;
    PowerPlant(){
        this.id = 1;
        this.price=0;
        this.energyAvailable = 100;

    }

    PowerPlant(int id,float price){
        this.id = id;
        this.price = price;
        this.energyAvailable = 100;
    }
    PowerPlant(int id,float price,float energyAvailable){
        this.id = id;
        this.price = price;
        this.energyAvailable = energyAvailable;
    }


    public float getPrice(){return this.price;}
    public int getId(){return this.id;}

    public float getEnergyAvailable(){return this.energyAvailable;}
    public void setEnergyAvailable(float n) {this.energyAvailable = n;}
    public void disChargeEnergy(float n){this.energyAvailable -= n;}


}
