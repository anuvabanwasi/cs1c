package week02_part01.solution;

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
		while (iter.hasNext())
		{
			System.out.print(iter.next() + " ");
		}
		System.out.println();

		// b) print reverse
		while (iter.hasPrevious())
		{
			System.out.print(iter.previous() + " ");
		}
		System.out.println();   

		// c) replaces some elements
		String potentialReplacee;
		while (iter.hasNext())
		{
			potentialReplacee = (String)iter.next();
			if (potentialReplacee.equals("X"))
				iter.set("Q");
		}
		
		// print the updated list in order
		for (String elem : myStrings)
			System.out.print(elem + " ");
		System.out.println();
		
		System.out.println("Done with TestArrayListIterator");
	}

}
