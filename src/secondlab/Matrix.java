/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Stepan
 */
public class Matrix {
    ArrayList <ArrayList<Double>> matrix;
    private int size;
    public Matrix(int n){
        matrix = new ArrayList<>();
        size=n;
        for(int i = 0; i < n; i++){
            matrix.add(new ArrayList<Double>());
        }
    } 
    public static Matrix CreateFromFile(File file){
        try{ 
            Scanner sc = new Scanner(file); 
            int n=0;
            //if(sc.hasNextInt())
                n=sc.nextInt(); 
            //else{
                //throw new InputMismatchException("Wrong dismention of a matrix!");
                //SecondLab.writeUsingFileWriter("Wrong dismention of a matrix!");
                //writeUsingFileWriter("dd");
            //}
            Matrix myMatrix = new Matrix(n); 
            for(int i = 0; i < n; i++){ 
                    for(int j = 0; j < n; j++) 
                        myMatrix.matrix.get(i).add(sc.nextDouble()); 
            }
            return myMatrix;
        } 
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("A matrix with dismention '0' doesn't exist.");
            return null;
        }
        catch(IOException e){ 
            System.out.println("a common error!"); 
            return null;
        }
        catch(InputMismatchException e){
            System.out.println("Wrong symbols in a matrix!"); 
            return null;
        }
        catch(NegativeArraySizeException e){
            System.out.println("A negative array size.");
            return null;
        }
        
    }
    public void printMatrix(){ 
        for(int i = 0; i < size; i++){ 
            for(int j = 0; j < size; j++){ 
                System.out.print(this.matrix.get(i).get(j)); 
                if(j < size - 1) 
                    System.out.print(" "); 
            } 
            System.out.println(); 
        } 
    } 
    public void writeUsingFileWriter() {
        File outFile = new File("result.txt");
        FileWriter out = null;
        try {
            out = new FileWriter(outFile, true);
            for(int i = 0; i < size; i++){ 
                for(int j = 0; j < size; j++){ 
                    //System.out.print(doubleMatrix[i][j]);
                    out.append(this.matrix.get(i).get(j)+"");
                    if(j < size-1 ) 
                        out.append("\t"); 
                } 
                out.append("\n"); 
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public double determinant()
    {
        double det=0;
        if(size == 1) 
        { 
            det = matrix.get(0).get(0); 
        } 
        else if (size == 2) 
        {       
            det = matrix.get(0).get(0)*matrix.get(1).get(1)-matrix.get(1).get(0)*matrix.get(0).get(1); 
        }
        else
        {
            det=0;
            for(int j1=0;j1<size;j1++)
            {
                Matrix m = new Matrix(size-1);
                //double[][] m = new double[N-1][];
                /*
                for(int k=0;k<(size-1);k++)
                {
                    m[k] = new double[N-1];
                }
                */
                for(int i=1;i<size;i++)
                {
                    int j2=0;
                    for(int j=0;j<size;j++)
                    {
                        if(j == j1)
                            continue;
                        //m.matrix.set(i-1, matrix.get(i)).set(j2,matrix.get(j));                      
                        m.matrix.get(i-1).add(matrix.get(i).get(j));
                        //m[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* matrix.get(0).get(j1) * m.determinant();
            }
        }
        return det;
    }
    public double[][] convertToDouble(){
        try{
            double doubleMatrix[][] = new double[size][size];
            for(int i=0; i<doubleMatrix.length;i++)
                for(int j=0; j<doubleMatrix.length; j++)
                    doubleMatrix[i][j]=matrix.get(i).get(j);   
            return doubleMatrix;
        }
        catch(InputMismatchException e){
            System.out.println("Wrong symbols in a matrix!"); 
            return null;
        }
        catch(NegativeArraySizeException e){
            System.out.println("A negative array size.");
            return null;
        }
    }
    public static void printDoubleMatrix(double [][] doubleMatrix){ 
        for(int i = 0; i < doubleMatrix.length; i++){ 
            for(int j = 0; j < doubleMatrix.length; j++){ 
                System.out.print(doubleMatrix[i][j]); 
                if(j < doubleMatrix.length - 1) 
                    System.out.print(" "); 
            } 
            System.out.println(); 
        } 
    } 
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        //printDoubleMatrix(x);
        return x;
    }
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
    public static double[][] multiply(double[][] a, double[][] b) {
        try{
            int m1 = a.length;
        
            int n1 = a[0].length;
            int m2 = b.length;
            int n2 = b[0].length;
            if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
            double[][] c = new double[m1][n2];
            for (int i = 0; i < m1; i++)
                for (int j = 0; j < n2; j++)
                    for (int k = 0; k < n1; k++)
                        c[i][j] += a[i][k] * b[k][j];
            return c;
        //SecondLab.writeUsingFileWriter(c);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("A matrix with dismention '0' doesn't exist.");
            return null;
        }
        catch(NegativeArraySizeException e){
            System.out.println("A negative array size.");
            return null;
        }
    }
}
