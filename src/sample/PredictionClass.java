package sample;

import java.io.*;
import java.lang.Math;

public class PredictionClass {

    //user defineable variables
    public static int numEpochs = 100; //number of training cycles
    public static int numInputs = 4; //number of inputs - this includes the input bias ,      for each slot
    public static int numHidden = 4; //number of hidden units
    public static int numPatterns = 96; //number of training patterns,          number of slots
    public static double LR_IH = 0.7; //learning rate
    public static double LR_HO = 0.07; //learning rate

    //process variables
    public static int patNum;
    public static double errThisPat;
    public static double outPred;
    public static double RMSerror;

    //training data
    public static double[][] trainInputs = new double[numInputs][numPatterns];
    public static double[] trainOutput = new double[numPatterns];

    // predicted array
    public static double[] predicted = new double[numPatterns];

    //the outputs of the hidden neurons
    public static double[] hiddenVal = new double[numHidden];

    //the weights
    public static double[][] weightsIH = new double[numInputs][numHidden];
    public static double[] weightsHO = new double[numHidden];


//==============================================================
//********** THIS IS THE MAIN PROGRAM **************************
//==============================================================

    public static void intiate() {

        //initiate the weights
        initWeights();

        //load in the data
        initData();

        //train the network
        for (int j = 0; j <= numEpochs; j++) {

            for (int i = 0; i < numPatterns; i++) {

                //select a pattern at random
                patNum = (int) ((Math.random() * numPatterns) - 0.001);
               // System.out.println("patnum "+patNum);

                //calculate the current network output
                //and error for this pattern
                calcNet();

                //change network weights
                WeightChangesHO();
                WeightChangesIH();
            }

            //display the overall network error
            //after each epoch
            calcOverallError();
            //  System.out.println("epoch = " + j + "  RMS Error = " + RMSerror);

        }

        //training has finished
        //display the results
        displayResults();
        test();

    }

//============================================================
//********** END OF THE MAIN PROGRAM **************************
//=============================================================


    //************************************
    public static void calcNet() {
        //calculate the outputs of the hidden neurons
        //the hidden neurons are tanh
        for (int i = 0; i < numHidden; i++) {
            hiddenVal[i] = 0.0;

            for (int j = 0; j < numInputs; j++) {
                hiddenVal[i] = hiddenVal[i] + (trainInputs[j][patNum] * weightsIH[j][i]);
                //  System.out.println("train "+patNum+" : "+ " j " +trainInputs[j][patNum]);
                //  System.out.println("train "+i+" : "+ " j "+j + " " + weightsIH[j][i]);
            }
            // System.out.println("hidden "+i+" : "+hiddenVal[i]);
            hiddenVal[i] = tanh(hiddenVal[i]);
        }


        //calculate the output of the network
        //the output neuron is linear
        outPred = 0.0;

        for (int i = 0; i < numHidden; i++)
            outPred = outPred + hiddenVal[i] * weightsHO[i];

        //calculate the error
        errThisPat = outPred - trainOutput[patNum];
    }


    //************************************
    public static void WeightChangesHO()
    //adjust the weights hidden-output
    {
        for (int k = 0; k < numHidden; k++) {
            double weightChange = LR_HO * errThisPat * hiddenVal[k];
            weightsHO[k] = weightsHO[k] - weightChange;

            //regularisation on the output weights
            if (weightsHO[k] < -5)
                weightsHO[k] = -5;
            else if (weightsHO[k] > 5)
                weightsHO[k] = 5;


        }
    }


    //************************************
    public static void WeightChangesIH()
    //adjust the weights input-hidden
    {
        for (int i = 0; i < numHidden; i++) { //System.out.println("loop " + i);
            for (int k = 0; k < numInputs; k++) {
                //System.out.println("loop123 : "+k);
                double x = 1 - (hiddenVal[i] * hiddenVal[i]);
                x = x * weightsHO[i] * errThisPat * LR_IH;
                x = x * trainInputs[k][patNum];
                double weightChange = x;
                weightsIH[k][i] = weightsIH[k][i] - weightChange;
                //   System.out.println("wt "+" :  "+i +" "+k+" : "+weightsIH[k][i]);
            }
        }
    }


    //************************************
    public static void initWeights() {

        for (int j = 0; j < numHidden; j++) {
            weightsHO[j] = (Math.random() - 0.5) / 2;
            for (int i = 0; i < numInputs; i++)
                weightsIH[i][j] = (Math.random() - 0.5) / 5;
        }

    }


    //************************************
    public static void initData() {

        System.out.println("initialising data");

        String filename = "4week.csv";
        String csvdata1 = "2nov.csv";
        String csvdata5 = "26oct.csv";
        String csvdata2 = "9nov.csv";
        String csvdata3 = "16nov.csv";
        String csvdata4 = "23nov.csv";


        String workingDirectory = System.getProperty("user.dir");

        //****************//

        String day1 = "";
        String day2 = "";
        String day3 = "";
        String day4 = "";
        String day5 = "";

        //absoluteFilePath = workingDirectory + System.getProperty("file.separator") + filename;
        day1 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata5;
        day2 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata1;
        day3 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata2;
        day4 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata3;
        day5 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata4;
        //absoluteFilePath = new FileInputStream("input.txt");
        //System.out.println(absoluteFilePath);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(day1));

            int slot = 0;
            int number = 1;
            System.out.println("train inpooout");
            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;
                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {

                        trainInputs[0][slot] = Double.parseDouble(price[6]) / 4440;             // normalizing the data with max value possible
                     //   System.out.println(trainInputs[0][slot]);
                        slot++;


                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }

            }

            br = new BufferedReader(new FileReader(day2));
            slot=0;
            number = 1;
            //System.out.println("inouting 1");
            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;
                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {

                        trainInputs[1][slot] = Double.parseDouble(price[6]) / 4440;             // normalizing the data with max value possible
                     //   System.out.println(trainInputs[1][slot]);
                        slot++;


                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }

            }



            br = new BufferedReader(new FileReader(day3));

            slot = 0;
            number = 1;

            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;
                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {

                        trainInputs[2][slot] = Double.parseDouble(price[6]) / 4440;             // normalizing the data with max value possible
                        slot++;
                        //  System.out.print(slot + " \t");
                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }

            }
            br = new BufferedReader(new FileReader(day4));

            slot = 0;
            number = 1;

            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;
                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {

                        trainInputs[3][slot] = Double.parseDouble(price[6]) / 4440;                  // normalizing the data with max value possible
                        slot++;
                        //  System.out.print(slot + " \t");
                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }

            }


            br = new BufferedReader(new FileReader(day5));

            slot = 0;
            number = 1;
            double t=0;
            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;

                if (number > 10) {
                    System.out.print("trainOutput " + " \t");
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {

                        trainOutput[slot] = Double.parseDouble(price[6]) / 4440;                  // normalizing the data with max value possible
                        t =trainOutput[slot];                  // normalizing the data with max value possible
                        slot++;

                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                    System.out.print(Double.parseDouble(price[6]) / 4440 + " " + slot + " " + trainOutput[slot] + "\t");
                 //   System.out.println( + "    "+slot);
                   // System.out.print("number "+number);
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

        System.out.println("Done");


        // the data here is the XOR data
        // it has been rescaled to the range
        // [-1][1]
        // an extra input valued 1 is also added
        // to act as the bias
/*

        trainInputs[0][0]  = 1;
        trainInputs[0][1]  = -1;
        trainInputs[0][2]  = 1;//bias
        trainOutput[0] = 1;

        trainInputs[1][0]  = -1;
        trainInputs[1][1]  = 1;
        trainInputs[1][2]  = 1;//bias
        trainOutput[1] = 1;

        trainInputs[2][0]  = 1;
        trainInputs[2][1]  = 1;
        trainInputs[2][2]  = 1;//bias
        trainOutput[2] = -1;

        trainInputs[3][0]  = -1;
        trainInputs[3][1]  = -1;
        trainInputs[3][2]  = 1;//bias
        trainOutput[3] = -1;
*/
        // printInput();
        //
        // printOutput();

        System.out.println("inoput values");
        for(int num =0;num<4;num++) {
            for (int slot = 0; slot < 96; slot++) {
                // item[i] = series1;
                System.out.print(trainInputs[num][slot] * 4440 + "\t");
            }
            System.out.println();
        }








    }

    public static void printInput() {
        System.out.print("print input data");
        for (int i = 0; i < 96; i++) {
            for (int k = 0; k < 3; k++) {
                System.out.print(trainInputs[k][i] + "\t");
            }
            System.out.println();
        }
    }

    public static void printOutput() {
        System.out.print("print output data");
        for (int i = 0; i < 96; i++) {
            System.out.print(trainOutput[i] + "\t");

        }
    }


    //************************************
    public static double tanh(double x) {
        if (x > 20)
            return 1;
        else if (x < -20)
            return -1;
        else {
            double a = Math.exp(x);
            double b = Math.exp(-x);
            //return (a - b) / (a + b);
            return 1/(1+b);
        }
    }


    //************************************
    public static void displayResults() {
        float errorper =0;
        for (int i = 0; i < numPatterns; i++) {
            patNum = i;
            calcNet();

            errorper = (float) ((float) (trainOutput[patNum]-  outPred)/trainOutput[patNum])*100;
            System.out.println("pat = " + (patNum + 1) + " actual = " + trainOutput[patNum] * 4440 + " neural model = " + outPred * 4440+" error = "+errorper);
            predicted[i] = outPred * 4440;

        }
        GraphDisplay.predictiongraph(trainOutput, predicted,trainInputs,"new");
    }


    //************************************
    public static void calcOverallError() {
        RMSerror = 0.0;
        for (int i = 0; i < numPatterns; i++) {
            patNum = i;
            calcNet();
            RMSerror = RMSerror + (errThisPat * errThisPat);
        }
        RMSerror = RMSerror / numPatterns;
        RMSerror = java.lang.Math.sqrt(RMSerror);
    }

    public static void test() {
        String workingDirectory = System.getProperty("user.dir");
        String csvdata4 = "30nov.csv";

        String day4 = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata4;
        //absoluteFilePath = new FileInputStream("input.txt");
        //System.out.println(absoluteFilePath);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            double temp = 0;
            int slot = 0;
            while (slot != 96){
                temp = trainInputs[0][slot];
                trainInputs[0][slot] =  trainInputs[1][slot];
                trainInputs[1][slot] = trainInputs[2][slot];
                trainInputs[2][slot]= trainInputs[3][slot];
                trainInputs[3][slot] = trainOutput[slot];
                slot++;

            }

            br = new BufferedReader(new FileReader(day4));

            slot = 0;
            int number = 1;
            while (((line = br.readLine()) != null) && slot < 96) {
                number += 1;
                if (number > 10) {
                    // use comma as separator
                    String[] price = line.split(cvsSplitBy);
                    try {
                        trainOutput[slot] = Double.parseDouble(price[6]) / 4440;             // normalizing the data with max value possible
                        slot++;
                        // System.out.print(price[6]+ " \t");
                        if (slot == 96)
                            break;

                    } catch (Exception e) {
                        //System.out.println("no val");
                        e.printStackTrace();
                    }
                }
            }

            for (int j = 0; j <= 500; j++) {
                for (int i = 0; i < numPatterns; i++) {

                    //select a pattern at random
                    patNum = (int) ((Math.random() * numPatterns) - 0.001);
                    //System.out.println("patnum "+patNum);

                    //calculate the current network output
                    //and error for this pattern
                    calcNet();

                    //change network weights
                    WeightChangesHO();
                    WeightChangesIH();
                }
                // calcNet();


            }
            displayResults();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
