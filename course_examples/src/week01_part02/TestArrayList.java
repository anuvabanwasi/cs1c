package week01_part02;

import java.util.*;

public class TestArrayList
{
   public static void main (String[] args)
   {
       // example of instantiating an new object
       ArrayList<Double> myDoubles = new ArrayList<Double>(); 

       int k;
     
       for ( k = 0; k < 30; k++ )
          myDoubles.add( -.01 * k);
       
       myDoubles.set(1, 999.);
      
       for ( k = 0; k < myDoubles.size(); k++ )
       {
          System.out.print(myDoubles.get(k) + " " );
          if (k%10 == 9)
             System.out.println();
       }
   }
}
