package week03_part02;

public class Factorial {
	public static int recursiveNumMethodCalls = 0;
	public static int iterativeNumMethodCalls = 0;

	public static int recursiveFactorial(int n)
	{
		recursiveNumMethodCalls++;

		// base case
		if (n == 0) 
			return 1;
		else
			// recursive call
			return n * recursiveFactorial(n - 1); 
	}

	public static int iterativeFactorial(int n)
	{
		iterativeNumMethodCalls++;

		int current;
		int result = 1;

		// use multiplication to compute factorial
		for ( current = n; current > 1; current--)
		{
			result *= current;
		}

		// return factorial value
		return result;
	}

	public static void main(String [] args)
	{
		int result = recursiveFactorial(5);
		System.out.println("recursive result = " + result);		
		System.out.println("number of recursive method calls = " + recursiveNumMethodCalls);

		result = iterativeFactorial(4);
		System.out.println("iterative result = " + result);		
		System.out.println("number of iterative method calls = " + iterativeNumMethodCalls);
	}
}
