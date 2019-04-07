package week02_part01;

import java.util.*;

public class TestFHarrayList
{
   public static void main (String[] args)
   {
      String[] words = {"When", "Harry", "Met", "Sally", 
         "X", "Wild", "Beast", "X", 
         "La", "Femme", "Nikita", "X", "Back", "To", 
         "The", "Future", "X"};
      
      FHarrayList<String> myStrings = new FHarrayList<String>();
      // ArrayList<String> myStrings = new ArrayList<String>();
      
      int k;
      
      // load up the list
      for (k = 0; k < words.length; k++)
         myStrings.add(words[k]);

      //--- ListIterator Demo -----------------
      ListIterator<String> p;
 
      // print the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         System.out.print( p.next() + " " ); 
      System.out.println();
      
      try
      {
         p = myStrings.listIterator();
         p.next();
         p.add("GRATEFUL DEAD");
         //p.next();
         //myStrings.remove(0);  // add only this and get concurrency
         //p.set("GRATEFUL DEAD");
         //p.hasNext();
         //p.remove();
         //p.next();            // add this line and the next and no problema
         //p.remove();          // add only this and get state
         p.add("GRATEFUL DEAD");
         p.add("GRATEFUL DEAD");
         //p.set("GRATEFUL DEAD");

         //p.previous();
         //p.remove();
         p.next();
         //p.set("GRATEFUL DEAD");
         System.out.println( "No problema.");
      }
      catch (IllegalStateException e)
      {
         System.out.print( "Oh my, State - " + e);
      }
      catch(ConcurrentModificationException e)
      {
         System.out.print( "Oh my, Concurrency: " + e);
      }      
      catch(Exception e)
      {
         System.out.print( "Oh my, Other: " + e);
      }
   } // main()
}
