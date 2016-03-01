package sample;

import java.io.*;
import java.util.ArrayList;
import java.lang.Double;

import java.util.List;

/**
 * Created by srinath on 1/25/2016.
 */
public class PatternRecognization {
    public static ArrayList<ArrayList<Double>> patternAr = new ArrayList<ArrayList<Double>>();;
    public static ArrayList<Double> patForRec = new ArrayList<Double>();;
    public static ArrayList<Double> avgLine = new ArrayList<Double>();;
    public static ArrayList<Double> performanceAr =new ArrayList<Double>();;
    public static ArrayList<Double> bid = new ArrayList<Double>();
    public static ArrayList<Double> ask = new ArrayList<Double>();
    public static ArrayList<Double> allData = new ArrayList<Double>();;
    public static ArrayList<Double> accuracyArray = new ArrayList<Double>();;
    public static final int patternLength =30;
    public static int toWhat = 100;

    public static ArrayList<ArrayList<Double>> plotPatAr =  new ArrayList<ArrayList<Double>>();


    public static void startPatternRecog(){

        loadData();
        int dataLength = allData.size();
        int samps = 0;
        int startpoint = 0;
        int found =0;
        System.out.println("alldata "+allData);

        while ( toWhat <200) {
            System.out.println("======================================================================================================================" +
                    "==============================================================================================================");
       //     List x = allData.subList(startpoint, startpoint+toWhat);
            System.out.println( dataLength);
            List x = allData.subList(0, toWhat);
            patForRec.clear();
            patternAr.clear();
            performanceAr.clear();


            avgLine.addAll(x);
         //   System.out.println(avgLine);
            patternStorage();
            System.out.println( "total lenth "+(startpoint+toWhat)+ " Entire (18) proce  "+toWhat );
            System.out.println( avgLine.get(avgLine.size()-1));
            currentPattern();
            patternRecognition();
            System.out.println( "acuracy"+  accuracyArray);



            samps +=1;
            double accuracyAverage = avgList(accuracyArray);
          //  System.out.println(  "Entire (18) processing took:",totalEnd,"seconds");
            System.out.println(  "Backtested Accuracy is  "+accuracyAverage+" after  " +samps + "   actionable trades start value "+toWhat+ "      \t   "+plotPatAr.size());

            avgLine.clear();
            toWhat++;
            if (plotPatAr.size() !=0  ){
                found++;

                if (( found <10)) GraphDisplay.patternPrediction(plotPatAr);

            }
/*
            if (toWhat == 20&& startpoint==dataLength) break;

            toWhat++;
            if (toWhat == 20) {
                startpoint++;
                toWhat = 10;
            }*/
        }
        System.out.println("plotPatAr       "+plotPatAr);
        GraphDisplay.patternPrediction(plotPatAr);
    }

    public static double percentChange(double startPoint, double currentPoint) {
        try {
            double x = (((double) (currentPoint) - startPoint) / Math.abs(startPoint)) * 100.00;
            if (x == 0.0)
                return 0.000000001;
            else
                return x;
        }
        catch (Exception e) {
            return 0.0001;
        }
    }





    public static void patternRecognition() {

        ArrayList<Double>  predictedOutcomesAr =  new ArrayList<Double>();

        int  patFound = 0;
        double howSim=0;
        System.out.println("plotpatar" +plotPatAr);
        double sim;
    //    System.out.println("insode pattern recognisation "+patternAr);

        for( ArrayList<Double> eachPattern : patternAr) {
       //     System.out.println(eachPattern);
            howSim = 0;
            for (int i=0;i <patternLength;i++) {
                sim = 100.00 - Math.abs(percentChange(eachPattern.get(i), patForRec.get(i)));
                //System.out.println("F     "+sim);
                howSim = howSim+sim;
            }

            //System.out.println("howSim "+howSim);
            howSim =(double) howSim/patternLength;
        //    System.out.println("plotpatar" +plotPatAr);
         //   System.out.println(howSim);
            if (howSim > 65) {
                System.out.println("Fooooooooooooooooooooooooooooooooooooooooooooouuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuunnnnnnnnnnnnnnnnnnnnnnnnnnnnnd");
                double patdex = patternAr.indexOf(eachPattern);
                patFound = 1;
         //       double[] xp =[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30] ;

                plotPatAr.add(eachPattern);
       //         System.out.println(plotPatAr);
            }
        }

        ArrayList<Double> predArray =  new ArrayList<Double>();
   //     System.out.println(plotPatAr);
        if (patFound == 1) {
            // fig = plt.figure(figsize = (10, 6))
       //     ArrayList<Double> eachPatt =null;
            for (ArrayList<Double>  eachPatt: plotPatAr) {

                int futurePoints = patternAr.indexOf(eachPatt);

                if ( performanceAr.get(futurePoints) > patForRec.get(patternLength -1 )) {
                    String pcolor = "#24bc00";
                    predArray.add(1.000);
                } else {
                    String pcolor = "#d40000";
                    predArray.add(-1.000);
                }
                predictedOutcomesAr.add(performanceAr.get(futurePoints));

            }
            List realOutcomeRange = allData.subList(toWhat + 20, toWhat + 30);
            double realAvgOutcome = avgList(realOutcomeRange) / realOutcomeRange.size();

            double realMovement = percentChange(allData.get(toWhat), realAvgOutcome);

            double predictionAverage = avgList(predArray);

         //   GraphDisplay.patternPrediction(plotPatAr);
        //    GraphDisplay.patternPrediction(patForRec);

         //   System.out.println( predArray);
        //    System.out.println( predictionAverage);


 /*           if (predictionAverage < 0) {
                System.out.println( "drop predicted");
                System.out.println(  patForRec.get( patternLength-1));
                System.out.println(  realMovement);
                if( realMovement< patForRec.get( patternLength-1))
                    accuracyArray.add(100.0);
                else
                    accuracyArray.add(0.0);

            }

            if( predictionAverage > 0) {
                System.out.println( "rise predicted");
                System.out.println( patForRec.get( patternLength-1));
                System.out.println(realMovement);
                if (realMovement >  patForRec.get(patternLength-1))
                    accuracyArray.add(100.0);
                else
                    accuracyArray.add(0.0);
            }         */
        }

    }

    public static void  currentPattern(){
        double mostRecentPoint = (double) avgLine.get(avgLine.size()-1);
        double[] cp = new double[patternLength+1];



        for (int i=0;i<patternLength;i++) {
     //       System.out.println(avgLine.size()+" \t"+ patternLength+ "\t"+ i+ "index values "+(avgLine.size()-patternLength+1)+" \t "+(avgLine.size()-patternLength+i));
            patForRec.add(percentChange(avgLine.get(avgLine.size() - patternLength + 1), avgLine.get(avgLine.size() - patternLength + i)));
        }
    }

    public static void loadData(){
       // String csvdata = "GBPUSD1d.txt";
        String csvdata = "oneyear.csv";

        String workingDirectory = System.getProperty("user.dir");

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
                String[] price = line.split(cvsSplitBy);
                try {

                    double biding = Double.parseDouble(price[6]);
                 //   double asking = Double.parseDouble(price[2]);
                    double asking = 0.0;

              //      System.out.println( biding );

               //     bid.add(biding);
                    ask.add( asking);
                //    allData.add((Double.parseDouble(price[1])+Double.parseDouble(price[2]))/2);
                    allData.add((Double.parseDouble(price[6])));
                } catch (Exception e) {
                     System.out.println("no val");
                 //    e.printStackTrace();
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

        System.out.println( "loaded data" );

    }


    public static  double avgList(ArrayList<Double> list){

        double sum=0;
        int i=0;
        for (;i< list.size();i++)
            sum = sum + list.get(i);

        sum /= list.size();
        return sum;
    }

    public static  double avgList(List list){
        ArrayList<Double> listOfDouble = new ArrayList<Double>(list.size());
        listOfDouble.addAll(list);
        return avgList(listOfDouble);
    }

    public static void patternStorage(){

        int x = avgLine.size() - patternLength;
        System.out.println("\nlength of x "+x);

        int y = patternLength+1;
    //    System.out.println("storage "+x+y);
        String currentStance = "none";
        while (y < x) {
            ArrayList<Double>  pattern = new ArrayList<Double>();
           // double[] p = new double[30];


            for (int i=0;i<patternLength;i++) {
         //       System.out.print(y - patternLength +"\t\t");
      //          System.out.print(y - patternLength + 1 + i+"\n");
                pattern.add(percentChange(avgLine.get(y - patternLength), avgLine.get(y - patternLength + 1 + i)));
            }
            List<Double> outcomeRange =  avgLine.subList(y + patternLength-5, y + patternLength);
            double currentPoint = avgLine.get(y) ;
            double avgOutcome=0;
            try {
              /*  for (int i=0;i<outcomeRange.size();i++){
                    avgOutcome = avgOutcome + outcomeRange.get(i);
                }   */
                avgOutcome = avgList(outcomeRange);

            }
            catch (Exception e){

                 avgOutcome = 0;
            }

            double futureOutcome = percentChange(currentPoint, avgOutcome);

            patternAr.add(pattern);
        //    System.out.println(pattern);
            performanceAr.add(futureOutcome);
            y+=1;

        }
     //   System.out.println("pattern arr    "+patternAr);
        System.out.println("performance arr       "+performanceAr.size());

    }
}
