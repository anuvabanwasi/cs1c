package week02_part02.practice;

//Note: In order to test this example fully
//you need to complete the implementation of FHStack   
public class TestFHstack
{
	public static void main (String[] args) throws CloneNotSupportedException
	{
		int k;

		FHstack<Integer> myStack = new FHstack<Integer>();

		for (k = 0; k < 10; k++)
			myStack.push(k);

		int the_top = myStack.top();
		System.out.println("top: " + the_top );

		for (k = 0; k < 12; k++)
			System.out.println( myStack.pop() );
	}
}
