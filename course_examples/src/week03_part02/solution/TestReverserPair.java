package week03_part02.solution;


import java.util.Scanner;

public class TestReverserPair
{
	public void reverseDisplay(String value)
	{
		int pos = value.length() - 1;
		reverseDisplay(value, pos);
	}

	public void reverseDisplay(String value, int pos)
	{
		if (pos >= 0) 
		{
			System.out.print(value.charAt(pos));
			reverseDisplay(value, pos - 1);
		}	
	}


	public static void main(String[] args) 
	{
		TestStringReversal application = new TestStringReversal();

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter a string to reverse:");

		String requestedStr = keyboard.nextLine();

		application.reverseDisplay(requestedStr);

		System.out.println("\nDone with TestStringReversal");
	}
}
