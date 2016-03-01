package sample;

/**
 * Created by srinath on 1/8/2016.
 */
/*
public class NewNeuralNetwork {
}
*/


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.text.DecimalFormat;

public class NewNeuralNetwork
{
    private static final int INPUT_NEURONS = 9;
    private static final int HIDDEN_NEURONS = 6;
    private static final int OUTPUT_NEURONS = 1;

    private static final double LEARN_RATE = 0.2;    // Rho.
    private static final double NOISE_FACTOR = 0.45;
    private static final int TRAINING_REPS = 5000;

    // Input to Hidden Weights (with Biases).
    private static double wih[][] = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];

    // Hidden to Output Weights (with Biases).
    private static double who[][] = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];

    // Activations.
    private static double inputs[] = new double[INPUT_NEURONS/3];
    private static double hidden[] = new double[HIDDEN_NEURONS];
  //  private static double target[] = new double[OUTPUT_NEURONS];
    private static double predictedVal[][][][]  = new double[7][52][96][OUTPUT_NEURONS];
    private static double target;

    public static double mean[][] = new double[7][96];
    public static double std[][] = new double[7][96];

    // Uniterrors.
    private static double erro[] = new double[OUTPUT_NEURONS];
    private static double errh[] = new double[HIDDEN_NEURONS];
    
    private static final int MAX_SAMPLES = 96;

    private static double[] predictedVal1 = new double[96];
    private static double[] target1 = new double[96];

    private static double trainInputs[][][] = new double[7][52][96];
    public static double maxVal = 7000.0;
    public static double minVal = 230.0;

    public static int weekday =0;
    public static int week =0;
    public static int slot =0;

    private static double testOutput[][][] = new double[7][52][96];

    private static ArrayList allVal = new ArrayList() ;

    public static void NeuralNetwork()
    {

        initiateData();
        System.out.println("\nTest network against original input:");
        assignRandomWeights();
        System.out.println("\nTest network against original input:");

        cleanData();
    //    Collections.sort(allVal);

 //       minVal  = (double) allVal.get(0);
  //      maxVal    = (double) allVal.get(allVal.size()-1);

  //      System.out.println("\nmaxval :: "+maxVal+" minval :: "+minVal);
    //    printAllValues();
        startTraining();
     /*   for (int slot=0;slot<96;slot++)
            target1[slot] =  trainOutput[slot][0];
            */
        // Train the network.

        getTrainingStats();

        System.out.println("\nTest network against original input:");
        nextDayTest();
   //     testNetworkTraining();

        System.out.println("\nTest network against noisy input:");
      //  testNetworkWithNoise1();

       // for(int k=0;k<96;k++) predictedVal[weekday][week][slot]1[k] = trainOutput[k][0];
     //   GraphDisplay.predictiongraph(target1, predictedVal[weekday][week][slot]1, trainInputs[0], "" + 0);
        int max_files=6;

        for(int k=1;k<max_files+1;k++) {
            System.out.println("test number " + k);
     //       nextDayTest();
        //    testNetworkTraining();
            if (k!=max_files) {

     //           startTraining();
            }

       //     GraphDisplay.predictiongraph(target1, predictedVal[weekday][week][slot]1, trainInputs[0],""+k);
        }
        /*System.out.println("test number "+max_files);
        nextDayTest(max_files);
        for (int sample=0;sample<96;sample++)
            target1[sample] =  trainOutput[sample][0];
        //startTraining();
        testNetworkTraining();
        GraphDisplay.predictiongraph(target1, predictedVal[weekday][week][slot]1, trainInputs,""+max_files);*/

        return;
    }

    private static void printAllValues(){
        slot = 0;
        week = 0;
        for (weekday=0;weekday<7;weekday++) {
            for (week = 0; week < 48; week++) {
                System.out.println("week : " + week);
                for (slot = 0; slot < 96; slot++) {
                    System.out.print(trainInputs[weekday][week][slot] + "\t");
                }
            }
        }


    }

    private static void startTraining() {
        slot = 0;
        week = 0;
        for (weekday=0;weekday<7;weekday++) {
            for (week = 0; week < 48; week++) {
                for (int epoch = 0; epoch < TRAINING_REPS; epoch++) {
                    slot += 1;
                    if (slot == MAX_SAMPLES) {
                        slot = 0;
                    }

                    setSingleVectorInputs();

              /*      for (; i < (INPUT_NEURONS/3);i++ ) {
                        try {
                            int preslot = (96 - (slot - (i + 1))) / 96;

                            int preweekday = weekday - preslot ;
                            int preweek = (7 - preweekday )/ 7;

                            inputs[i] = trainInputs[weekday - ((week + i) / 7)][(week + i) % 7][slot];                               //previous week prices

                            inputs[i + (INPUT_NEURONS / 3)] = trainInputs[(weekday - preslot) % 7][week - preweek][(slot - (i + 1)) % 96];       // previous slot prices of same day

                            inputs[INPUT_NEURONS - i - 1] = trainInputs[(weekday - (i + 1)) % 7][week + ((weekday - (i + 1)) / 7)][slot];        // previous day prices  // -ve bcoz of divened iwill be negative
                            //  i =i+2;

                        }
                        catch (Exception e){
                       //     System.out.println("error in week "+week +" : "+ weekday+" : "+slot);
                          //  e.printStackTrace();
                        }

                    } // i*/

            //        System.out.println(week+" : "+ weekday+" : "+ slot);
         /*           for ( i =0;i<INPUT_NEURONS;i++) {
                        System.out.print(inputs[i]+"\t");

                    }*/

                //    System.out.println();

               //     for (int i = 0; i < OUTPUT_NEURONS; i++) {
              //          target = trainOutput[weekday][week][slot];
                    if (week+(INPUT_NEURONS /3)  > 51 ) System.out.println("error "+week+"  " +INPUT_NEURONS);

                    target = trainInputs[weekday][week+(INPUT_NEURONS /3)][slot];
                //    } // i

                    feedForward();
                    backPropagate();

                } // epoch

        //        System.out.println("training and target         sdfsd");

                // for (int k=0;k<96;k++) System.out.println(""+target1[k]+" :    "+ trainOutput[k][0]);


            }
        }
    }

    private static void setSingleVectorInputs(){
        int i = 0;
        for (; i < (INPUT_NEURONS/3);i++ ) {
            try {
                int preslot = (96 - (slot - (i + 1))) / 96;

                int preweekday = weekday - preslot ;
                int preweek = (7 - preweekday )/ 7;

                inputs[i] = trainInputs[weekday ][(week + i) ][slot];                               //previous week prices

         //       inputs[i + (INPUT_NEURONS / 3)] = trainInputs[(weekday - preslot) % 7][week - preweek][(slot - (i + 1)) % 96];       // previous slot prices of same day

         //       inputs[INPUT_NEURONS - i - 1] = trainInputs[(weekday - (i + 1)) % 7][week + ((weekday - (i + 1)) / 7)][slot];        // previous day prices  // -ve bcoz of divened iwill be negative
                //  i =i+2;

            }
            catch (Exception e){
                //     System.out.println("error in week "+week +" : "+ weekday+" : "+slot);
                //  e.printStackTrace();
            }

        } // i

    }



    private static void getTrainingStats()
    {
        System.out.println("\ngetTrainingStats :");
        double sum = 0.0;
    //    int sample = 0, weekday = 0;

        for (weekday = 0; weekday < 7; weekday++) {
            for (int week = 0; week < 48; week++) {
                for (slot = 0; slot < MAX_SAMPLES; slot++) {
                    setSingleVectorInputs();

                    //    for (int j = 0; j < OUTPUT_NEURONS; j++) {
                    target = trainInputs[weekday][week + (INPUT_NEURONS /3)][slot];

                    target1[slot] = target;
                    //    } // j


                    feedForward();
                    double error = target - predictedVal[weekday][week][slot][0];

                    if (error > 0.1){
                        System.out.println("we got error  more");
                    }

                    System.out.println(inputs[0] + "\t" + inputs[1] + "\t" + inputs[2] + "\t"  + "\t" + target+"\t "+ predictedVal[weekday][week][slot][0]+ "\t  "+ error);
                    // System.out.println(maximum(predictedVal[weekday][week][slot]) + "\t" + maximum(target));

            /*if(maximum(predictedVal[weekday][week][slot]) == maximum(target)){
                sum += 1;
            }else{

            }*/
                } // i
            }
        }

     //   System.out.println("Network is " + ((double) sum / (double) MAX_SAMPLES * 100.0) + "% correct.");


    }

/*


    private static void testNetworkTraining()
    {
            doubleerrorper;
            System.out.println("\ntestNetworkTraining   :");
            // This function simply tests the training vectors against network.
    //    int sample = 0, weekday = 0;
        for (weekday = 0; weekday < 52; weekday++) {
                for (int j = 0; j < INPUT_NEURONS; j++) {
                    inputs[j] = trainInputs[weekday][i][j];
                } // j

                feedForward();

                for (int j = 0; j < INPUT_NEURONS; j++) {
                    System.out.print(inputs[j] * 4440 + "\t");
                } // j
               errorper = (trainOutput[i][0] - predictedVal[weekday][week][slot][0]) * 100 / trainOutput[i][0];

                predictedVal[weekday][week][slot]1[i] = predictedVal[weekday][week][slot][0];

                //  System.out.print("Output: " + maximum(predictedVal[weekday][week][slot]) + "\n");
                // System.out.println("ip1 :"+inputs[0] + "\t"+ "ip1 :" + inputs[1] + "\t"+"ip2 :" + inputs[2] + "\t"+ "ip3 :" + inputs[3]+"\t predictedVal[weekday][week][slot] out  :  "+trainOutput[i][0]);
                System.out.println(trainOutput[i][0] * 4440 + "\t " + predictedVal[weekday][week][slot][0] * 4440 + "\t" +errorper);
                // i
            }
        }


        return;
    }
*/

    private static void nextDayTest(){
        String workingDirectory = System.getProperty("user.dir");
        String csvdata4;
        //absoluteFilePath = new FileInputStream("input.txt");
        //System.out.println(absoluteFilePath);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            double temp = 0;
           // int slot = 0;
          /*
            while (slot != 96){
              //  temp = trainInputs[0][slot];
                trainInputs[slot][0] =  trainInputs[slot][1];
                trainInputs[slot][1] =   trainInputs[slot][2];
                trainInputs[slot][2] = trainInputs[slot][3];
                trainInputs[slot][3] = trainOutput[slot][0];
                slot++;
            }
            */



            csvdata4 = "oneyear.csv";


            String day4 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + "csv_data" +'\\' + csvdata4;
            String day1 = workingDirectory + File.separator + "src" + "/" + "sample" + "/" + "csv_data" +"/" + csvdata4;

            try {
                br = new BufferedReader(new FileReader(day4));
            }
            catch (Exception e){
                br = new BufferedReader(new FileReader(day1));
            }

            weekday=1;
            week=0;
            slot=0;
            int number = 1;
            while (((line = br.readLine()) != null)) {
                number += 1;

                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);

                    if (slot == 96) {
                        slot = 0;
                        weekday++;
                        weekday = weekday%7;
                        if(weekday == 0) week++;
        //                System.out.println();
                        if (week == 52) break;

                    }

                    if (price[1].length() >0) {
                        slot=0;
                    }
                    try {
                        testOutput[weekday][week][slot] = normalizeValue( Double.parseDouble(price[6]) );             // normalizing the data with max value possible

             //           System.out.print(testOutput[weekday][week][slot] +"\t");
                    //    System.out.print(trainInputs[weekday][week][slot]+"\t");
                        slot++;
                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("testing data \n");

        for (weekday = 0; weekday < 7; weekday++) {
            for ( week = 0; week < 52- (INPUT_NEURONS/3); week++) {
                for (slot = 0; slot < MAX_SAMPLES - 1; slot++) {
                    //  for (int j = 0; j < INPUT_NEURONS; j++) {

                    //   setSingleVectorInputs();

                    //   inputs[j] = testOutput[weekday][week + j][slot];


                    for (int i = 0; i < (INPUT_NEURONS / 3); i++) {
                        try {
                            int preslot = (96 - (slot - (i + 1))) / 96;

                            int preweekday = weekday - preslot;
                            int preweek = (7 - preweekday) / 7;

                            inputs[i] = testOutput[weekday ][(week + i) ][slot];                               //previous week prices

                        //    inputs[i + (INPUT_NEURONS / 3)] = testOutput[(weekday - preslot) % 7][week - preweek][(slot - (i + 1)) % 96];       // previous slot prices of same day

                      //      inputs[INPUT_NEURONS - i - 1] = testOutput[(weekday - (i + 1)) % 7][week + ((weekday - (i + 1)) / 7)][slot];        // previous day prices  // -ve bcoz of divened iwill be negative
                            //  i =i+2;

                        } catch (Exception e) {
                            //     System.out.println("error in week "+week +" : "+ weekday+" : "+slot);
                            //  e.printStackTrace();
                        }

                    } // i

                    //         System.out.print(trainInputs[weekday][week + j][slot]+"\t");

                    // j

                    //    for (int j = 0; j < OUTPUT_NEURONS; j++) {
                    target = testOutput[weekday][week + (INPUT_NEURONS / 3)][slot];
                    target1[slot] = target;

                    //    } // j
                    feedForward();

                    double error = (target - predictedVal[weekday][week][slot][0])/target;

                    if (error > 1){
                        System.out.println("we got error  more");
                    }

        //            System.out.println(inputs[0] + "\t " + inputs[1] + "\t " + inputs[2] + "\t "  + "\t " + target+"\t "+ predictedVal[weekday][week][slot][0]+ "\t  "+ error);
                    //        System.out.println(inputs[0] + "\t" + inputs[1] + "\t" + inputs[2] + "\t" + inputs[3] + "\t" + target+"\t"+predictedVal[weekday][week + INPUT_NEURONS][slot][0]);
                 //   System.out.println(predictedVal[weekday][week][slot][0]);
                    // System.out.println(maximum(predictedVal[weekday][week][slot]) + "\t" + maximum(target));

            /*if(maximum(predictedVal[weekday][week][slot]) == maximum(target)){
                sum += 1;
            }else{

            }*/
                } // i

            }
        }

        double[] predict = new double[52];
        double[] actval = new double[52];
        double[] previous = new double[52];

        weekday = 4;
    //    slot=54;

        for ( slot =0; slot <96 ; slot++) {
            for (week = 0; week < 48; week++) {
                predict[week] = denormalizeValue(predictedVal[weekday][week][slot][0]);
                actval[week] = denormalizeValue(testOutput[weekday][week][slot]);
                previous[week] = denormalizeValue(trainInputs[weekday][week][slot]);
                //System.out.println(predict[week]);
            }
            GraphDisplay.annpredict(actval,predict,slot,weekday,testOutput,previous);
        }




    }

    public static double normalizeValue(double val){
        return (val - minVal)/(maxVal-minVal);
    }

    public static double denormalizeValue(double val){
        return val*(maxVal-minVal) +minVal;
    }



    private static int maximum(final double[] vector)
    {
        // This function returns the index of the maximum of vector().
        int sel = 0;
        double max = vector[sel];

        for(int index = 0; index < OUTPUT_NEURONS; index++)
        {
            if(vector[index] > max){
                max = vector[index];
                sel = index;
            }
        }
        return sel;
    }

    private static void feedForward()
    {
        double sum = 0.0;

        // Calculate input to hidden layer.

      //  System.out.print("week "+week+"\t");
        for(int hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            sum = 0.0;
            for(int inp = 0; inp < (INPUT_NEURONS/3); inp++)
            {
                sum += inputs[inp] * wih[inp][hid];
            } // inp

            sum += wih[INPUT_NEURONS/3][hid]; // Add in bias.
            hidden[hid] = sigmoid(sum);
        } // hid

        // Calculate the hidden to output layer.
        for(int out = 0; out < OUTPUT_NEURONS; out++)
        {
            sum = 0.0;
            for(int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                sum += hidden[hid] * who[hid][out];
            } // hid

            sum += who[HIDDEN_NEURONS][out]; // Add in bias.
            predictedVal[weekday][week][slot][out] = sigmoid(sum);

        } // out
        return;

    }
    //predictedVal[weekday][week][slot] == predicted
    // target = predictedVal[weekday][week][slot] output

    private static void backPropagate()
    {
        // Calculate the output layererror (step 3 for output cell).

                for (int out = 0; out < OUTPUT_NEURONS; out++) {
                   erro[out] = (target - predictedVal[weekday][week][slot][out]) * sigmoidDerivative(predictedVal[weekday][week][slot][out]);
                }

                // Calculate the hidden layererror (step 3 for hidden cell).
                for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
                    errh[hid] = 0.0;
                    for (int out = 0; out < OUTPUT_NEURONS; out++) {
                        errh[hid] +=erro[out] * who[hid][out];
                    }
                    errh[hid] *= sigmoidDerivative(hidden[hid]);
                }

                // Update the weights for the output layer (step 4).
                for (int out = 0; out < OUTPUT_NEURONS; out++) {
                    for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
                        who[hid][out] += (LEARN_RATE *erro[out] * hidden[hid]);
                    } // hid
                    who[HIDDEN_NEURONS][out] += (LEARN_RATE *erro[out]); // Update the bias.
                } // out

                // Update the weights for the hidden layer (step 4).
                for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
                    for (int inp = 0; inp < INPUT_NEURONS/3; inp++) {
                        wih[inp][hid] += (LEARN_RATE * errh[hid] * inputs[inp]);
                    } // inp
                    wih[INPUT_NEURONS/3][hid] += (LEARN_RATE * errh[hid]); // Update the bias.
                } // hid



        return;
    }

    private static void assignRandomWeights()
    {
        for (int weekday=0;weekday<7;weekday++)
            for (int week =0; week<52;week++)
                for (int slot=0;slot <96;slot++) {
                    for (int inp = 0; inp <= INPUT_NEURONS/3; inp++) // Do not subtract 1 here.
                    {
                        for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
                            // Assign a random weight value between -0.5 and 0.5
                            wih[inp][hid] = new Random().nextDouble() - 0.5;
                        } // hid
                    } // inp

                    for (int hid = 0; hid <= HIDDEN_NEURONS; hid++) // Do not subtract 1 here.
                    {
                        for (int out = 0; out < OUTPUT_NEURONS; out++) {
                            // Assign a random weight value between -0.5 and 0.5
                            who[hid][out] = new Random().nextDouble() - 0.5;
                        } // out
                    } // hid
                }
        return;
    }

    private static double sigmoid(final double val)
    {
        return (1.0 / (1.0 + Math.exp(-val)));
    }

    private static double sigmoidDerivative(final double val)
    {
        return (val * (1.0 - val));
    }

    public static void initiateData(){
        System.out.println("initialising data");

        String csvdata ="2014-15.csv";

        String workingDirectory = System.getProperty("user.dir");

        //****************//

        String day = "";

        day = workingDirectory + File.separator + "src" + "\\" + "sample" + "\\" + "csv_data" +"\\" + csvdata;
        String day1 = workingDirectory + File.separator + "src" + "/" + "sample" + "/" + "csv_data" +"/" + csvdata;

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            try {
                br = new BufferedReader(new FileReader(day));
            }
            catch (Exception e){
                br = new BufferedReader(new FileReader(day1));
            }
            System.out.println("train inpooout");
            while (((line = br.readLine()) != null) ) {
                //   number += 1;
                //  if (number > 10) {
                // use comma as separator
                String[] price = line.split(cvsSplitBy);
                try {
                    if (slot == 96) {
                        slot = 0;
                        weekday++;
                        weekday = weekday%7;
                        if(weekday == 0) week++;
                        // System.out.print(price[1]);

                        //  System.out.println("\n week " + week + "\tweekday : " + weekday);
                    }

                    if (price[1].length() >0) {
                        slot=0;
                    }
                    //  System.out.print(price[1]+" ");
                    trainInputs[weekday][week][slot] = normalizeValue(Double.parseDouble(price[6]) );
                    System.out.print(""+slot+" : "+trainInputs[weekday][week][slot] +"\t");
                    slot++;

                } catch (Exception e) {
                    // System.out.println("no val");
                    // e.printStackTrace();
                    //   }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    private static void cleanData(){
        findMean();
        findstd();

        double sqr=0,val;
        int week = 0;

     //   System.out.println("Std : ");

        for (int weekday=0;weekday<7;weekday++) {
            for (int slot = 0; slot < 96; slot++) {


                for (week = 0; week < 52; week++) {
                    if (trainInputs[weekday][week][slot] >  5*std[weekday][slot] )  trainInputs[weekday][week][slot] =  mean[weekday][slot];

                    else if (trainInputs[weekday][week][slot] <  0.02*std[weekday][slot] )  trainInputs[weekday][week][slot] =  mean[weekday][slot];

               //     allVal.add(trainInputs[weekday][week][slot]);


                }
                //System.out.print(sum+"\t"+trainInputs[weekday][week-1][slot]);
                //  std[weekday][slot] = (double) Math.sqrt(sqr / week);

                //   System.out.print(weekday + " " + slot + " " + std[weekday][slot] + "\t");


            }
            //  System.out.println();
        }

    }


    private static void findMean(){
        double sum=0;
        int week = 0;
        System.out.println("mean : ");
        for (int weekday=0;weekday<7;weekday++)
            for (int slot =0;slot<96;slot++) {
                for (week=0; week < 52; week++) {
                    //  if(trainInputs[weekday][week][slot] !=0)
                    sum = sum+trainInputs[weekday][week][slot];
                }
                //System.out.print(sum+"\t"+trainInputs[weekday][week-1][slot]);
                mean[weekday][slot] =(double) sum/week;

                //  System.out.print(weekday+" "+slot+" "+mean[weekday][slot]+"\t");
                sum =0;
                week = 0;
            }
    }

    private static void findstd(){
        double sqr=0,val;
        int week = 0;
        System.out.println("Std : ");

        for (int weekday=0;weekday<7;weekday++) {
            for (int slot = 0; slot < 96; slot++) {
                for (week=0; week < 52; week++) {
                    //  if(trainInputs[weekday][week][slot] !=0)
                    val = trainInputs[weekday][week][slot] - mean[weekday][slot];
                    val = val * val;
                    sqr = sqr + val;
                }
                //System.out.print(sum+"\t"+trainInputs[weekday][week-1][slot]);
                std[weekday][slot] = (double) Math.sqrt(sqr / week);

         //       System.out.print(weekday + " " + slot + " " + std[weekday][slot] + "\t");
                sqr = 0;
                week = 0;
            }
     //       System.out.println();
        }

    }






  /*  public static void main(String[] args)
    {
        NeuralNetwork();
        return;
    }
*/
}
