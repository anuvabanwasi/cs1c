package week03_part02;

import java.util.Scanner;

public class Fibonacci 
{
	public static int recursiveNumMethodCalls = 0;
	public static int iterativeNumMethodCalls = 0;

	// iterative solution
	static int iterativeFibonacci(int n) 
	{
		iterativeNumMethodCalls++;

		if (n == 0)
			return 0;
		if (n == 1 || n == 2)
			return 1;

		int prev = 1;
		int current = prev;
		int next = 0;


		//System.out.printf("%7s \t%7s \t%7s \t%7s\n", "n", "prev", "current", "next");
		//System.out.printf("%7d \t%7d \t%7d \t%7d\n", n, prev, current, next);
		for (int i = 3; i <= n; i++) 
		{
			next = prev + current;
			prev = current;
			current = next;
			//System.out.printf("%7d \t%7s \t%7s \t%7d\n", n, prev, current, next);
		}
		return next;
	}

	// recursive solution
	static int recursiveFibonacci(int  n) 
	{
		recursiveNumMethodCalls++;

		// base case
		if (n == 0)
		{
			System.out.printf("%7d \t%7s \t%7s \t%7d\n", n, "", "", 0);
			return 0;
		}

		// base case
		if (n == 1)
		{
			System.out.printf("%7d \t%7s \t%7s \t%7d\n", n, "", "", 1);
			return 1;
		}

		// two recursive calls
		int fibNMinusOne = recursiveFibonacci(n - 1);
		int fibNMinusTwo = recursiveFibonacci(n - 2);
		int intermediateResult = fibNMinusOne + fibNMinusTwo;
		System.out.printf("%7d \t%7d \t%7d \t%7d, \t%7d \t%7d\n", n, n-1, n-2, intermediateResult, fibNMinusOne, fibNMinusTwo);
		return intermediateResult;
	}

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		// the nth element
		System.out.println("Enter the number to computer fibonacci: ");
		int n = keyboard.nextInt();

		// show the iterative sequence
		System.out.printf("Iterative fibonacci(%d) = %d \n", n, iterativeFibonacci(n));
		System.out.println("number of iterative method calls = " + iterativeNumMethodCalls);


		// show the recursive sequence
		System.out.printf("\n%7s \t%7s \t%7s \t%7s \t%7s \t%7s\n", "n", "n-1", "n-2", "intermediate result", "fib(n-1)", "fib(n-2)");
		System.out.printf("Recursive fibonacci(%d) = %d \n", n, recursiveFibonacci(n));
		System.out.println("number of recursive method calls = " + recursiveNumMethodCalls);

	}
}