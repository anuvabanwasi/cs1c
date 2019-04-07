package week03_part02;

public class MyExample 
{
	public static void methodC()
	{
		/* lots of goodies */
		System.out.println("methodC done");
	}

	public static void methodB()
	{
		methodC();
		System.out.println("methodB done");
	}

	public static void methodA()
	{
		methodB();
		System.out.println("methodA done");
	}	

	public static void main(String[] args) 
	{
		methodA();
		System.out.println("main done");
	}
}
