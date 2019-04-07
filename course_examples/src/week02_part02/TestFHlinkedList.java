package week02_part02;

import java.util.LinkedList;

public class TestFHlinkedList 
{
	public static void main (String[] args) throws CloneNotSupportedException
	{
	    int k;
	    final int INT_ARRAY_SIZE = 10000;
	    double avg;

	    String[] words = {"When", "Harry", "Met", "Bullitt", 
	    "X", "Wild", "Beast", "X", 
	    "X", "Men", "Nikita", "X", "Back", "To", 
	    "The", "Future", "X", "House", "Good", "Wife"};
	    String[] capturedStrings = new String[100];

	    FHlinkedList<String> 
	    myStrings = new FHlinkedList<String>(),
	    myStrings2 = new FHlinkedList<String>(),
	    myStrings3;
	    FHlinkedList<Integer> 
	    myInts = new FHlinkedList<Integer>(),
	    myInts2;

	    LinkedList<Integer> myIntsColl = new LinkedList<Integer>();
	    //FHlinkedList<Integer> myIntsColl = new FHlinkedList<Integer>();

	    // testing add() on Strings
	    for (k = 0; k < words.length; k++)
	    {
	        myStrings.add(words[k]);
	        myStrings2.add(new String(words[k]));
	    }

	    // testing add(index, ...) on ints
	    for (k = 0; k < INT_ARRAY_SIZE; k++)
	        myInts.add( myInts.size()/2, (int)(Math.random()*100) );

	    // testing get() on ints
	    for (k = 0, avg = 0.; k < myInts.size(); k++)
	        avg += myInts.get(k);
	    avg /= myInts.size();
	    System.out.println("Average: " + avg );

	    // testing set() on ints
	    for (k = 0; k < myInts.size(); k++)
	        myInts.set(k, (int)(Math.random()*100) );

	    for (k = 0, avg = 0.; k < myInts.size(); k++)
	        avg += myInts.get(k);
	    avg /= myInts.size();
	    System.out.println("Average: " + avg );

	    // testing equals
	    if (myStrings.equals(myStrings2))
	        System.out.println("\nTest as equal -- GOOD");
	    else
	        System.out.println("Test as unequal -- ERROR");

	    myStrings2.set(2, "Hurt Locker");
	    if (myStrings.equals(myStrings2))
	        System.out.println("Test as equal -- ERROR");
	    else
	        System.out.println("Test as unequal -- GOOD");      

	    if (myStrings.equals(myInts))
	        System.out.println("Test as equal -- ERROR");
	    else
	        System.out.println("Test as unequal -- GOOD");

	    // testing clone
	    myStrings3 = (FHlinkedList<String>)myStrings.clone();
	    if (myStrings.equals(myStrings3))
	        System.out.println("clone should test equal -- GOOD");
	    else
	        System.out.println("clone tests unequal -- ERROR");

	    myStrings3.set(3, "The Kennedys");
	    System.out.println("shallow changes after clone");
	    for (k = 0; k < 4; k++)
	        System.out.println(k + ": " + myStrings.get(k) + " / "
	        + myStrings3.get(k) ); 

	    // testing add(index, ...) on Strings
	    System.out.println("\nOriginal String ArrayList:"); 
	    for (k = 0; k < myStrings.size(); k++)
	        System.out.print(k + ": " + myStrings.get(k) + " / ");
	    System.out.println(); 

	    myStrings.add(2, "Broken Heart");

	    System.out.println("Item added in position 2:"); 
	    for (k = 0; k < myStrings.size(); k++)
	        System.out.print(k + ": " + myStrings.get(k) + " / ");
	    System.out.println(); 

	    // testing remove(index) and remove(object)
	    System.out.println("\nTesting remove(index) and remove(object).");
	    for (k = 0; k < 6; k++)
	        System.out.print(k + ": " + myInts.get(k) + " / ");
	    System.out.println();

	    myInts.remove(3);
	    myInts.remove( myInts.get(0) );

	    System.out.println("0th and 3rd items should be gone.");
	    for (k = 0; k < 6; k++)
	        System.out.print(k + ": " + myInts.get(k) + " / ");
	    System.out.println();

	    // testing clear() and addAll()
	    myInts.clear();
	    System.out.println("\nNew size (should be 0): " + myInts.size() );
	    for (k = 0; k < 10; k++)
	        myIntsColl.add(k);
	    myInts2 = new FHlinkedList<Integer>();
	    myInts2.addAll(myIntsColl);

	    System.out.println("\naddAll() -- should be identical");
	    for (k = 0; k < myIntsColl.size(); k++)
	        System.out.print(k + ": " + myIntsColl.get(k) + " / " );
	    System.out.println();
	    for (k = 0; k < myInts2.size(); k++)
	        System.out.print(k + ": " + myInts2.get(k) + " / ");
	    System.out.println();

	    // testing indexOf()...
	    myInts2.add(6,2);
	    System.out.println();
	    for (k = 0; k < myInts2.size(); k++)
	        System.out.print(k + ": " + myInts2.get(k) + " / ");
	    System.out.println();
	    System.out.println("\nindexOf() and lastIndexOf()");
	    System.out.println("index of 2: " + myInts2.indexOf(2));    
	    System.out.println("last index of 2: " + myInts2.lastIndexOf(2));  
	    System.out.println();

	    // testing contains()
	    System.out.println("String List contains Bullitt? (should be T): " 
	    + myStrings.contains("Bullitt") );
	    System.out.println("Int List contains 93? (should be F): " 
	    + myInts2.contains(93) ); 

	    // testing exceptions -----------------
	    System.out.println();
	    // set()
	    try
	    {
	        myInts.set(20, 20);
	    }
	    catch(Exception e)
	    {
	        System.out.println("set() - caught!  -- " + e);
	    }

	    // add()
	    try
	    {
	        myInts.add(20, 20);
	    }
	    catch(Exception e)
	    {
	        System.out.println("add() - caught!  -- " + e);
	    }

	    // get()
	    try
	    {
	        myInts.get(20);
	    }
	    catch(Exception e)
	    {
	        System.out.println("get() - caught!  -- " + e);
	    }

	    System.out.println("\nTesting toArray()");
	    String[] y = myStrings.toArray(capturedStrings);

	    for (k = 0; capturedStrings[k] != null; k++)
	        System.out.print(k + ": " + capturedStrings[k] + " / ");
	    System.out.println();
	    for (k = 0; y[k] != null; k++)
	        System.out.print(k + ": " + y[k] + " / ");
	}
}
