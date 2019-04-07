package week03_part01;

public class BubbleSort
{
   // returns true if a modification was made to the array
   public static < E extends Comparable<? super E > > 
   boolean floatLargestToTop(E[] data, int top)
   {
      boolean changed = false;
      E temp;
      
      if ( !(data[0] instanceof String) )
      {
         // stop at top-1 = (worst case) arraySize-2 due to k+1 in loop
         for (int k =0; k < top; k++)
            if (data[k].compareTo(data[k+1]) > 0)
            {
               temp = data[k];
               data[k] = data[k+1];
               data[k+1] = temp;
               changed = true;
            }
      }
      else
      {
         // For strings, ignore case (a choice we make)
         for (int k =0; k < top; k++)
         {
            String strK = (String)data[k];
            String strKplus1 = (String)data[k+1];
            if ( strK.compareToIgnoreCase(strKplus1) > 0)
            {
               temp = data[k];
               data[k] = data[k+1];
               data[k+1] = temp;
               changed = true;
            }
         }
      }        
      return changed;
   } /* floatLargestToTop() method */
   
   public static <E extends Comparable<? super E>> 
   void sortArray(E[] array)
   {
      for (int k = 0; k < array.length; k++)
         if ( !floatLargestToTop(array, array.length - 1 - k) )
            return;
   } /* sortArray() method */
}