package sample;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by srinath on 10/9/2015.
 */
public class RandomDetails {
    static Random sellingPrice = new Random();
    static Random buyingPrice = new Random();
    static  Random stayTime = new Random();
    static  Random entryTime = new Random();

    //   batterylabel.setText("Present Parking lot purchase Price is : " + sellvalue);

    public static CarObject[] setCarDetails() {

        CarObject[] carList = new CarObject[1000] ;
        int j = 0;
        for (; j < 10; j++) {
            double buy = buyingPrice.nextGaussian() * 1.5 + 4;
            double sell = sellingPrice.nextGaussian() * 1.5 + 3;

            while ( buy <0 || sell <0 || buy > sell){
                buy = buyingPrice.nextGaussian() * 1.5 + 4;
                sell = sellingPrice.nextGaussian() * 1.5 + 3;
      //          System.out.println(buy+" : "+sell);

            }

            Random battery = new Random();
            float intialBat = battery.nextFloat()*20 +50;
            while (intialBat<0) {
                System.out.println("intialBat : "+intialBat);
                intialBat = battery.nextFloat()*20 +50;
            }
            float criticalBat = battery.nextFloat()*30+50;
            while (intialBat<0) {
                System.out.println("criticalBat : "+criticalBat);
                criticalBat = battery.nextFloat()*30+50;
            }

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            java.sql.Timestamp t5 = new java.sql.Timestamp(calendar.getTime().getTime());


            // mean is 9 hr 20 min  std is 50min
            double val = stayTime.nextGaussian() * 4200000 + 33600000;       // more zeros becuase of time in miiliseconds time
            int stay = (int) (val);
            int entry = (int) (entryTime.nextGaussian() * 3000000 + 26100000);   //mean 7 hr 45min  std is 50min



            java.sql.Timestamp t1 = new java.sql.Timestamp(calendar.getTime().getTime() + entry);

            java.sql.Timestamp t2 = new java.sql.Timestamp(calendar.getTime().getTime() + stay);
           /* carList[j].setPlugInTime(t1);
            carList[j].setExistTime(t2);*/

            carList[j] = new CarObject(RandomClass.stringValue(), "Both", (float) buy, (float) sell,t1,t2,intialBat,criticalBat,"slow");
          /*  long diffInMinutes = (stay - entry)/60000;
            System.out.println(diffInMinutes+ " :   "+(val)/3600000);*/
            carList[j].setPlugInDuration((float) (val / 3600000));
            carList[j].setCarId(j);
        }

        //System.out.println("intial values   sdjlfskjfdb fjksghsf");
    //    for (int i = 0; carList[i] != null; i++) System.out.print(carList[i].getCarId()+" : "+carList[i].getBatteryLevel()+" : "+ carList[i].getCriticalMinBattery() + "\t");
        System.out.println("");

        return carList;

    }

    public static PowerPlant[] setPlantDetails(){
        PowerPlant[] powerPlants = new PowerPlant[96];
        int slot = 0;
        float sell ;

        while (slot<96){
            sell = (float) ((sellingPrice.nextGaussian() * 2 + 3)*1.1);
            while (sell <0){
                sell = (float) ((sellingPrice.nextGaussian() * 2 + 3)*1.1);
            }

            Random energy = new Random();
            float available = energy.nextFloat()*80;
            System.out.print(available+"\t");
            powerPlants[slot] = new PowerPlant(slot, sell,available);
            slot++;

        }
        System.out.println("");

        return powerPlants;
    }
}
