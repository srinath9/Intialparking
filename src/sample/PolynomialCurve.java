package sample;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.util.FastMath;

import java.io.*;
import java.util.Arrays;

/**
 * Created by srinath on 1/14/2016.
 */
public class PolynomialCurve {
  //  public static int degree =8;
    private static double trainInputs[][][] = new double[7][53][96];
   // private static double coeff[] = new double[degree];
    private static int trainSetNum = 50;

    private static double testInputs[][][] = new double[7][52][96];


    public static void makecurve() {

       trainInputs = ScatterPoints.getTrainInputs();
        testInputs = ScatterPoints.getTestInputs();
        System.out.print("\n" + trainInputs[3][5][45]);

     //   getCoeffients();
        trainLargeSample();

    }


    public static void trainLargeSample() {
     //   final Random randomizer = new Random(0x5551480dca5b369bl);
        double maxError = 0;
        double[] actualValues = new double[52];
        WeightedObservedPoints obs = new WeightedObservedPoints();
        int weekday =6,slot=48;
      //  for (;slot<96;slot++) {
            System.out.print("" + slot + "\n");

            for (int week = 0; week < 52; week++) {
                actualValues[week] = trainInputs[weekday][week][slot];
                obs.add(week, trainInputs[weekday][week][slot]);
            }
        double targetOuput= testInputs[weekday][0][slot];
        System.out.println("test data");
        System.out.println(Arrays.toString(actualValues));

            // Instantiate a third-degree polynomial fitter.


            //  final WeightedObservedPoints obs = new WeightedObservedPoints();


            for (int degree = 8; degree < 18; ++degree) {
                // final PolynomialFunction p ;            //= buildRandomPolynomial(degree, randomizer);
                PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);


                // Retrieve fitted parameters (coefficients of the polynomial function).
                //    coeff = fitter.fit(obs.toList());
                //  double[] coefficients = new double[degree + 1];

                //       return new PolynomialFunction(coefficients);
                //  final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);


                System.out.print("" + degree + "\t");
                double sum = 0;

                PolynomialFunction fitted = new PolynomialFunction(fitter.fit(obs.toList()));
                testPolyomial(fitted,actualValues,trainSetNum,targetOuput);


                for (int week = 0; week < 50; week++) {

                    if (actualValues[week] != 0) {
                        double error = FastMath.abs(actualValues[week] - fitted.value(week)) ;

                        //  maxError = FastMath.max(maxError, error);
                        sum = sum + (error * error);
                        // Assert.assertTrue(FastMath.abs(error) < 0.01);
                        //      System.out.print("" + error + " : " + fitted.value(week) + " : " + actualValues[week] + " : "+sum+ "\t\t  ");
                    }
                }
                System.out.print("training : " + sum + "\n\n");
               // System.out.print("" + sum + "\t");
         //   }
        }   //  Assert.assertTrue(maxError > 0.001);
    }



    public static void testPolyomial(PolynomialFunction fitted,double[] actualValues, int polyStartIndex,double targetOuput) {
      //  double[] actualValues = new double[52];
     //   WeightedObservedPoints obs = new WeightedObservedPoints();
  /*      int weekday =0,slot=0;

        System.out.print("" + slot + "\n");
        for (int week = 0; week < 30; week++) {
            actualValues[week] = trainInputs[weekday][week][slot];
            obs.add(week, actualValues[week]);
        }
*/
        double maxerror = 0;

        GraphDisplay.testErrorGraph( fitted, actualValues,  polyStartIndex,targetOuput);

        for (; polyStartIndex < 52; ++polyStartIndex) {

         //   System.out.print("" + polyStartIndex + "\t");

            double error = FastMath.abs(actualValues[polyStartIndex] - fitted.value(polyStartIndex))/actualValues[polyStartIndex];
            if(maxerror < error) maxerror = error;
            System.out.print(polyStartIndex + " : "+ "" + error *100 + " : " + fitted.value(polyStartIndex) + " : " + actualValues[polyStartIndex]+"\t\t  ");


        }   //  Assert.assertTrue(maxError > 0.001);
        System.out.print("\ntesting max error : " + maxerror *100+ "\n");
    }






    /*

    private  static void testData(){
        double[] predict= new double[96];
        double val =0;
        double pol;
        for (int i=0;i<53;i++){
            pol =1;
            val=0;
            for(int x=1;x<4;x++) {
                pol = pol*i;
                val = val + (coeff[x] * pol);
                System.out.print(val+"\t");
            }
            val = val+coeff[0];
            predict[i] = val;
        }
        System.out.println("predicted ");

        for (int i=0;i<predict.length;i++) System.out.print(predict[i]+"\t");

    }

*/
    private static void predictData(){

        double[] trainOutput = new double[96];

        String csvdata = "2jan.csv";

        String workingDirectory = System.getProperty("user.dir");

        //****************//

        String day = "";

        //absoluteFilePath = workingDirectory + System.getProperty("file.separator") + filename;
        day = workingDirectory + File.separator + "src" + '\\' + "sample" + '\\' + csvdata;

        //absoluteFilePath = new FileInputStream("input.txt");
        //System.out.println(absoluteFilePath);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(day));
            int slot = 0;
            System.out.println("train inpooout");
            while (((line = br.readLine()) != null) ) {
                //   number += 1;
                //  if (number > 10) {
                // use comma as separator
                String[] price = line.split(cvsSplitBy);
                try {

                    if (price[1].length() >0) {
                        slot=0;
                    }
                    //  System.out.print(price[1]+" ");
                    trainOutput[slot] = Double.parseDouble(price[6]) ;
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






    }
}

