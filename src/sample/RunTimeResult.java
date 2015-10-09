package sample;

/**
 * Created by srinath on 10/8/2015.
 */
public class RunTimeResult {
    public static void magic() {

        Transaction R1 = new Transaction( "Charging");
        R1.start();

        Transaction R2 = new Transaction( "Discharging");
      //  R2.start();
    }
}
