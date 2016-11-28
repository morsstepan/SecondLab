/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Stepan
 */
public class SecondLab {  
    public static void main(String[] args) {
        File inFile = new File("data.txt");
        File outFile = new File("result.txt");
        try{
            Scanner sc = new Scanner(inFile);
            FileWriter out = new FileWriter(outFile);
            int n;
            if(sc.hasNextInt())
                n = sc.nextInt();
            else
                //System.out.println("Error! Please, pay attension on matrix dismention");
                writeUsingFileWriter("Error! Please, pay attension to the matrix dismention");
            //Matrix = new Matrix myMatrix(n);
            Matrix myMatrix = Matrix.CreateFromFile(inFile);
            //writeUsingFileWriter("Entered matrix: \n");
            //myMatrix.printMatrix();
            out.flush();
            double k=myMatrix.determinant();
            String detString="A determinant of a matrix: ";
            writeUsingFileWriter(detString+k+"\n");
            String aKindOfBeauty="-------------------------------------------------------------\n";
            writeUsingFileWriter(aKindOfBeauty);
            //System.out.println("det: "+k);
            double doubleMatrix[][] = myMatrix.convertToDouble();
            //myMatrix.printDoubleMatrix(doubleMatrix);
            if(k!=0){
                double invertedMatrix[][]=myMatrix.invert(doubleMatrix);
                writeUsingFileWriter("Invertible matrix: \n");
                writeUsingFileWriter(invertedMatrix);
                writeUsingFileWriter(aKindOfBeauty);
                //myMatrix.printDoubleMatrix(invertedMatrix);
            }
            else{
                writeUsingFileWriter("Determinant equals 0. An invertible matrix doesn't exist \n");
                writeUsingFileWriter(aKindOfBeauty);
            }
            String sqString="Matrix of degree 2: \n";
            String cubString="Matrix of degree 3: \n";
            double squaredMatrix[][]=myMatrix.multiply(doubleMatrix,doubleMatrix);
            double cubedMatrix[][]=myMatrix.multiply(doubleMatrix,squaredMatrix);
            //myMatrix.printDoubleMatrix(squaredMatrix);
            writeUsingFileWriter(sqString);
            writeUsingFileWriter(squaredMatrix);
            writeUsingFileWriter(aKindOfBeauty);
            //myMatrix.printDoubleMatrix(cubedMatrix);
            writeUsingFileWriter(cubString);
            writeUsingFileWriter(cubedMatrix);
            writeUsingFileWriter(aKindOfBeauty);
            //String detString="A determinant of a matrix: ";
            //out.write((int) k);
            //out.write((int) k);
            //writeUsingFileWriter(k);
            //writeUsingFileWriter(cubedMatrix);
            //String aKindOfBeauty="--------------------------------------------------\n";
            //String invertedString="Invertible matrix: \n";
            //writeUsingFileWriter(detString+k+"\n");
            //writeUsingFileWriter(aKindOfBeauty);
            //writeUsingFileWriter(sqString);
            //writeUsingFileWriter(squaredMatrix);
            //writeUsingFileWriter(aKindOfBeauty);
            //writeUsingFileWriter(cubString);
            //writeUsingFileWriter(cubedMatrix);
            //writeUsingFileWriter(aKindOfBeauty);
            
            //out.close();
        }
        catch(IOException e){
            System.out.println("Error!");  
        }
        
    }
    public static void writeUsingFileWriter(String data) {
        File outFile = new File("result.txt");
        FileWriter out = null;
        try {
            out = new FileWriter(outFile, true);
            out.write(data);
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
    public static void writeUsingFileWriter(double[][] data) {
        File outFile = new File("result.txt");
        FileWriter out = null;
        try {
            out = new FileWriter(outFile, true);
            for(int i = 0; i < data.length; i++){ 
                for(int j = 0; j < data.length; j++){ 
                    //System.out.print(doubleMatrix[i][j]);
                    out.append(" "+data[i][j]+" ");
                    if(j < data.length-1 ) 
                        out.append("    "); 
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
    public static void writeUsingFileWriter(double data) {
        File outFile = new File("result.txt");
        FileWriter out = null;
        try {
            out = new FileWriter(outFile, true);
            out.write((int) data);
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
}
