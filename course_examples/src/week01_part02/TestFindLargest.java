package week01_part02;

public class TestFindLargest
{
	static <E extends Comparable<? super E>>
	E findLargestOfThree(E x, E y, E z)
	{
		if (x.compareTo(y) > 0)
			return (x.compareTo(z) > 0) ? x : z;
		else
			return (y.compareTo(z) > 0) ? y : z; 
	} 

	static <E, T> void foo(E a, E b, T c)
	{
		System.out.println(a + "\n" + b + "\n" + c);
	}
	
	public static void main(String [] args)
	{
		SpecialInt a = new SpecialInt(5);
		SpecialInt b = new SpecialInt(-29);
		SpecialInt c = new SpecialInt(77);
		SpecialInt x;

		int result = a.compareTo(b);  // compiler has no problem
		System.out.println("result = " + result);

		x = findLargestOfThree(a, b, c);
		System.out.println(x.the_int + " " );
		
		
		foo(a, b, "hello");
	}
}
