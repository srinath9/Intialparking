package sample;

import java.io.*;
/**
 * Created by srinath on 1/10/2016.
 */
public class ScatterPoints {
    //weekday week  slots
    private static double trainInputs[][][] = new double[7][52][96];
    private static double testInputs[][][] = new double[7][52][96];
    public static double mean[][] = new double[7][96];
    public static double std[][] = new double[7][96];



    public static double[][][] initiateData(String filename){
        System.out.println("initialising data");

        String csvdata =filename;

        String workingDirectory = System.getProperty("user.dir");

        //****************//

        String day = "";

        //absoluteFilePath = workingDirectory + System.getProperty("file.separator") + filename;
        day = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + "csv_data" +'\\' + csvdata;

        //absoluteFilePath = new FileInputStream("input.txt");
        //System.out.println(absoluteFilePath);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(day));
            int slot = 0;
           
            int week = 0;
            int weekday = 0;
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
                    trainInputs[weekday][week][slot] = Double.parseDouble(price[6]) ;
                 //   System.out.print(""+slot+" : "+trainInputs[weekday][week][slot] +"\t");
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
    //    System.out.print("\n" + trainInputs[2][0][2]+"\n");

     //   cleanData();

        return trainInputs;
    }


    public static  double[][][] initiateData(){
        return initiateData("2014-15.csv");
    }

    public static double[][][] getTrainInputs(){
        double trainInputs1[][][]  = initiateData("2014-15.csv");
     //   double trainInputs1[][][] = new double[7][53][96];
        //trainInputs1 = trainInputs;


      //  System.out.print("\n" + trainInputs1[2][0][2]);

        return trainInputs1;
    }

    public static double[][][] getTestInputs() {
       return initiateData("oneyear.csv");
    }

    public static void runData(){
        initiateData();

        findMean();
        findstd();

        cleanData();
        startGraphs();
    }




    private static void startGraphs(){


        for (int k=0;k<1;k++)
            GraphDisplay.scatterGraph(trainInputs[k],""+k,mean,std);

        //     for (int week=0;week<53;week++)
        //       for (int slot=0;slot<96;slot++)
        //  System.out.print(""+slot+" : "+trainInputs[4][week][slot] +"\t");


        GraphDisplay.scatterGraph(trainInputs[0],""+0,mean,std);

    }

    private static void findMean(){
        double sum=0;
        int week = 0;
        System.out.println("mean : ");

        for (int weekday=0;weekday<7;weekday++)
            for (int slot =0;slot<96;slot++) {


                for (; week < 53; week++) {
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


                for (; week < 53; week++) {
                    //  if(trainInputs[weekday][week][slot] !=0)
                    val = trainInputs[weekday][week][slot] - mean[weekday][slot];
                    val = val * val;
                    sqr = sqr + val;

                }
                //System.out.print(sum+"\t"+trainInputs[weekday][week-1][slot]);
                std[weekday][slot] = (double) Math.sqrt(sqr / week);

                System.out.print(weekday + " " + slot + " " + std[weekday][slot] + "\t");
                sqr = 0;
                week = 0;
            }
            System.out.println();
        }

    }


    private static void cleanData(){


        double sqr=0,val;
        int week = 0;

        System.out.println("Std : ");

        for (int weekday=0;weekday<7;weekday++) {
            for (int slot = 0; slot < 96; slot++) {


                for (week = 0; week < 53; week++) {
                    if (trainInputs[weekday][week][slot] >  5*std[weekday][slot] )  trainInputs[weekday][week][slot] =  mean[weekday][slot];
                }
                //System.out.print(sum+"\t"+trainInputs[weekday][week-1][slot]);
              //  std[weekday][slot] = (double) Math.sqrt(sqr / week);

             //   System.out.print(weekday + " " + slot + " " + std[weekday][slot] + "\t");


            }
          //  System.out.println();
        }

    }



}

