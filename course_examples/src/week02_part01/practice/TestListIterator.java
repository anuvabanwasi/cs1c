package week02_part01.practice;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class TestListIterator 
{
	public static void main(String[] args) 
	{
		String line = "When Harry Met Sally X Wild Beast X La Femme Nikita X Back To The Future X";
		List<String> wordsAsList = Arrays.asList(line.split("\\s* \\s*"));
		FHarrayList<String> myStrings = new FHarrayList<String>(wordsAsList);
		
		// a) print forward
		ListIterator<String> iter = myStrings.listIterator();
		// TODO: Use an iterator to print the list in order 
		// (i.e. from first element to last element) 
		System.out.println();

		// b) print reverse
		// TODO: Use an iterator to print the list in reverse
		System.out.println();   

		// c) replaces some elements
		// TODO: Use an iterator to replace all "X"s with "Q"s.

		// TODO: Then print the list a for-each loop.
		// QUESTION: Does the for-each loop print in the elements in order or in reverse?
		
		System.out.println("Done with TestArrayListIterator");
	}

}
