package sample;

import org.apache.commons.math3.fitting.*;

/**
 * Created by srinath on 1/14/2016.
 */
public class CurveFitting {

    //performs a least squares fit of a polynomial function of the given degree
//mapping each input[k] vector to each output[k] vector
//returns the coefficients in a matrix
    public static double[][] fitpolynomial(double input[][], double output[][], int degree){
        double[][] X = new double[input.length][];
        //Run the input through the polynomialization and add the bias term
        for (int k = 0; k < input.length; k++){
            X[k] = polynomial(input[k], degree);
        }
        int inputs = X[0].length ;//number of inputs after the polynomial
        int outputs = output[0].length ;//number of outputs
        int datapoints = X.length ;//number of data points
        //calculate X^T*X
        double[][] XTX = new double[inputs][] ;//XTX is symmetric
        for (int i = 0; i < inputs; i++){//so we only have to
            XTX[i] = new double[i+1] ;//build the lower triangle
            for (int j = 0; j <= i; j++){
                for (int k = 0; k < datapoints; k++){
                    XTX[i][j] += X[k][i] * X[k][j];
                }
            }
        }
        //calculate the LDL decomposition in place with D over top of the diagonal
        for (int j = 0; j < inputs; j++){
            for (int k = 0; k < j; k++){//D starts as XTXjj then subtracts
                XTX[j][j] -= XTX[j][k] * XTX[j][k] * XTX[k][k];//Ljk^2 * Dk
            }
            for (int i = j + 1; i < inputs; i++){//L over the lower diagonal
                for (int k = 0; k < j; k++){//Lij starts as XTXij then subtracts
                    XTX[i][j] -= XTX[i][k] * XTX[j][k] * XTX[k][k];//Ljk^2*D[k]
                }
                XTX[i][j] /= XTX[j][j];//divide Lij by Dj
            }
        }
        double[][] matrix = new double[outputs][] ;
        //perform the substitution for each output separately
        for (int o = 0; o < outputs; o++){
            double[] XTY = new double[inputs];
            //multiply this output column by X^T to get RHS of least squares problem
            for (int j = 0; j < inputs; j++){
                for (int k = 0; k < datapoints; k++){
                    XTY[j] += output[k][o]* X[k][j];
                }
            }
            //in-place forward substitution with L
            for (int j = 0; j < inputs; j++){
                for (int i = 0; i < j; i++){
                    XTY[j] -= XTX[j][i] * XTY[i];
                }
            }
            //Multiply by inverse of D matrix
            for (int k = 0; k < inputs; k++){//inverse of diagonal
                XTY[k] /= XTX[k][k];//is 1 / each element
            }
            // in-place backward substitution with L^T
            for (int j = inputs - 1; j >= 0; j--){
                for (int i = j + 1; i < inputs; i++){
                    XTY[j] -= XTX[i][j] * XTY[i];
                }
            }
            //copy into final matrix row for this output
            matrix[o] = XTY;
        }
        return matrix ;
    }


    //applies a polynomial built with the fitpolynomial method
    public static double[] applypolynomial(double input[], double matrix[][], int degree){
        double[] i = polynomial(input, degree);//apple the same polynomial
        double[] o = new double[matrix.length];
        for (int j = 0; j < o.length; j++){//multiply by the matrix
            for (int k = 0; k < i.length; k++){
                o[j] += matrix[j][k] * i[k];
            }
        }
        return o;//return result
    }


    //Creates a new input vector which is a 1, and each input raised to integer powers up to degree
//when called with degree=1 this simply adds a bias value to the input vector
//otherwise it creates a separable polynomial of the given degree
    public static double[] polynomial(double[] input, int degree)
    {
        double[] output = new double[1 + input.length * degree];//1 is for the constant
        int i = 1, k, j;
        for (k = 0; k < input.length; k++){
            for (j = 1; j <= degree; j++){
                output[i] = (double)Math.pow(input[k], j);
                i++;
            }
        }
        output[0] = 1; //constant
        return output;
    }

}