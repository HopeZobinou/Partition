/**
* Partition_HKZ
*
* This program prompts the user for a file name, reads the array in the file and
* Partitions each it into 2 equal groups. If the array is not partitionable it will
* print out close results.
*
*/
import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;
public class Partition_HKZ
{
   public static void main(String[]args)
   {  
      boolean Flag = true;
      int New = 0;
      int [] intArray; 
      String UserInputFile;
      Scanner kb = new Scanner(System.in);
      System.out.println("*** P|r|o|g|r|a|m|-|p|a|r|t|i|t|i|o ***");
      System.out.println();
      System.out.print("Enter input file: ");
      UserInputFile = kb.nextLine();
      System.out.println();
      intArray = readIntFile(UserInputFile);
      System.out.println("Partitioning ...");
      System.out.println();
      int n = intArray.length;
      System.out.println("n: "+n);
      int[] Sub0 = new int[intArray.length];
      int[] Sub1 = new int[intArray.length];
      int Sum0 = 0;
      int Sum1 = 0;
      int Sum = 0;
      for(int a = 0; a < n; a++)
      {
         Sub0[a] = -1;
         Sub1[a] = -1;
      }
      long et_start = System.nanoTime();
      while(Flag == true)
      {
         for(int Redo = 0; Redo < intArray.length - 1; Redo++)
         {
            for(int index = 0; index < intArray.length - Redo - 1; index++)
            {
               if(intArray[index] < intArray[index + 1])
               {
                  New = intArray[index];
                  intArray[index] = intArray[index + 1];
                  intArray[index + 1] = New;
               }   
            }
         }
         for(int index = 0; index < n; index++)
         {
            Sum += intArray[index];
         }
         int ModCheck = Sum % 2;
         int Tries = 0;
         for(int index = 0; index < n; index++)
         {
            Sum0 += intArray[index];
            Sub0[index] = intArray[index];
            Tries ++;
            if(Sum0 > Sum/2)
            {
               Sum0 -= intArray[index];
               Sub0[index] = -1;
               Sum1 += intArray[index]; 
               Sub1[index] = intArray[index];
            }
         }
         if(Sum0 == Sum1 || ModCheck != 0 || Tries == n)
         {
            Flag = false; 
         } 
      }
      long et = (System.nanoTime() - et_start);
      System.out.println();
      System.out.println("Sum: "+Sum+" Sum0: "+Sum0+", Sum1: "+Sum1);
      String OutPutFile0 = "part_s0_"+UserInputFile; 
      String OutPutFile1 = "part_s1_"+UserInputFile;        
      writeIntFile(OutPutFile0,Sub0);
      writeIntFile(OutPutFile1,Sub1);
      System.out.println();
      double dSum0 = (int) Sum0;
      double ROS = dSum0/Sum1;
      if(ROS < 1)
      {
         double dSum1 = (int) Sum1;
         ROS = dSum1/Sum0;
      }
      DecimalFormat df = new DecimalFormat ("0.00000000");
      double d_et_start = (long) et_start;
      System.out.print("ROS: "+ df.format(ROS)+", ");
      System.out.printf("ET: %.4e nsecs\n", (double) et);
      System.out.println();
      System.out.println("Writing output files: "+OutPutFile0);
      System.out.println();
      System.out.println("Writing output files: "+OutPutFile1);
           
   }
   public static int[] readIntFile(String inFileName) 
   {
   // open the input file
   File file = new File(inFileName);
   Scanner input = null;
   try 
   {
   input = new Scanner(file);
   }
   catch (FileNotFoundException ex) 
   {
      System.out.println();
      System.out.println("File read error, cannot open: " + inFileName);
      System.exit(1); // quit the program
   }    
   // determine size of input file
   int n = 0;
   while (input.hasNextInt()) 
   {
      input.nextInt();
      n++;
   }
   input.close();
   // read the integers from the input file and store them in an array
   try 
   {
      input = new Scanner(file);
   }
   catch (FileNotFoundException ex) 
   {
      System.out.println("File read error, cannot open: " + inFileName);
      System.exit(1); // quit the program
   }
      int[] intArray = new int[n];
      System.out.println("Reading input file: " + inFileName);
      System.out.println();
      for (int i=0;input.hasNextInt();i++) 
      {
         intArray[i] = input.nextInt();
      }
      input.close();
               
      // return the integer array to the calling method
      return intArray;
   } // end readIntFile
   public static void writeIntFile(String fileName, int[] intArray) 
   {
      File file = new File(fileName);
      PrintWriter output = null;
      try 
      {
         output = new PrintWriter(file);
      }
      catch (FileNotFoundException ex) 
      {
         System.out.println("*** Cannot create " + fileName + " ***");
         System.exit(1); 
      }
      for (int index = 0; index < intArray.length; index++) 
      {
         if(intArray[index] != -1)
         output.print(String.valueOf(intArray[index]) + " ");
      }
      output.close();
    }
}