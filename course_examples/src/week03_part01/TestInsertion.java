package week03_part01;


import java.util.*;
import java.text.*;

/**
 * Testing insertion using two inputs:
 *    - the N (array size) and 
 *    - M (number of insertions) 
 */
public class TestInsertion
{
   /** Double N and triple M each time.  
    * What is the Theta estimation?
	* Since we have analyzed this in a previous section and found this to be a Theta(NM), 
	* we expect a 6-fold running increase each loop pass.
	*/
   public static void main(String[] args) throws Exception
   {
      int k, arraySize, numInsertions, attempt, writePosition;
      double elapsedTime;
      Integer[] arrayOfInts;
      int[] somePositions = {3, 67, 20, 15, 59  };
      int numPositions = somePositions.length;

      // to time the algorithm -------------------------
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      elapsedTime = 0;
      for (arraySize = 100, numInsertions = 50; 
        elapsedTime < 10; 
         arraySize*=2, numInsertions*=3)
      {
         // allocate array and stuff will values
         arrayOfInts = new Integer[arraySize + 1];
         for (k = 0; k < arraySize; k++)
            arrayOfInts[k] = (int)(Math.random() * arraySize);

         startTime = System.nanoTime();

         // we will do numInsertions insertions, throwing away tunes that run off the top
         for (attempt = 0; attempt < numInsertions; attempt++)
         {
            writePosition = somePositions[ attempt % numPositions ];

            // move everything up one 
            for (k = arraySize; k > writePosition; k--)
               arrayOfInts[k] = arrayOfInts[k-1];
            // now put a new number into the free position
            arrayOfInts[writePosition] = 51;
         }

         stopTime = System.nanoTime();
         elapsedTime = (stopTime - startTime) / 1e9;
         
         System.out.println("N: " + arraySize
            + " M: " + numInsertions
            + " Insertion time: "
            + tidy.format(elapsedTime)
            + " seconds.");
      } 
   }
}