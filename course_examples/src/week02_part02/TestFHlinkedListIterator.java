package week02_part02;

import java.util.NoSuchElementException;

public class TestFHlinkedListIterator 
{	
	public static void main (String[] args)
	{
		int k;
		FHlinkedList<Integer> fhList = new FHlinkedList<Integer>();

		fhList.addLast(1);
		fhList.addLast(2);
		fhList.addLast(3);
		fhList.addLast(4);
		fhList.addLast(5);
		fhList.addLast(6);

		fhList.addFirst(0);
		fhList.addFirst(-1);
		fhList.addFirst(-2);
		fhList.addFirst(-3);

		fhList.set( fhList.size()/2, -99);

		System.out.println( "List size: " + fhList.size() );
		System.out.println( "First val: " + fhList.get(0) );
		System.out.println( "Middle val: " + fhList.get(fhList.size()/2) );
		System.out.println( "Last val: " + fhList.get( fhList.size() - 1) );

		System.out.println( "List size: " + fhList.size() );
		for (k = 0; k < 4; k++)
		{
			System.out.println( fhList.getFirst() + " " + fhList.getLast() );
			fhList.removeFirst();
		}

		System.out.println("\n" + fhList.size() );
		for (k = 0; k < 12; k++)
		{
			try
			{
				System.out.println( fhList.getFirst() + " " + fhList.removeLast() );
			}
			catch ( NoSuchElementException e)
			{
				System.out.println("Caught Ex. at k = " + k + ".  Empty list.");
			}
		}

		System.out.println( "List size: " + fhList.size() );
	}
}
