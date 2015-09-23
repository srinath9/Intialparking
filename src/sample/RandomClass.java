package sample;

import java.util.Random;

/**
 * Created by srinath on 9/7/2015.
 */
public class RandomClass {

    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;


    public static int integerValue( int n){
        // log("Generating 10 random integers in range 0..99.");

        //note a single Random object is reused here
        Random randomGenerator = new Random();

            int randomInt = randomGenerator.nextInt(100 - n);
           // log("Generated : " + randomInt);

        return randomInt;

    }

    private static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }



    public static String stringValue(){
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public static float sellingPrice(){
        Random randomGenerator = new Random();

        float randomInt = randomGenerator.nextInt(1000);

        if (randomInt < 100){
            randomInt += 100;
        }

        randomInt =randomInt/100;
        return  randomInt;

    }

    public static float greaterthanmin(float n){

        Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(1000);

        while ((float) randomInt < n*10){
            randomInt = randomGenerator.nextInt(1000);
            System.out.println("next int value : "+ randomInt);
        }

        randomInt =randomInt/100;
        return  randomInt;

    }

    private static void log(String aMessage){
        // System.out.println(aMessage);
    }
}
