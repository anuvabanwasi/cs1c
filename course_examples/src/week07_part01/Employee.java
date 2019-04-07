package week07_part01;

// Example of an Employee class
public class Employee 
{
	public static final int MAX_LEN = 50;

	private String name;
	private int ss;

	public Employee(String name , int ss)
	{
		this();
		setName(name);
		setSS(ss);
	}

	public Employee()
	{
		name = "undefined";
		ss = 0;
	}

	String getName() 
	{ return name; }
	
	int getSS() { return ss; }

	boolean setName( String name )
	{
		if (name == null)
			return false;
		if (name.length() > MAX_LEN)
			return false;
		this.name = name;
		return true;
	}

	boolean setSS( int ss )
	{
		if (ss < 0 || ss > 999999999 )
			return false;
		this.ss = ss;
		return true;
	}

	public String toString()
	{
		return name + " (" + ss + ")";
	}
	
	public boolean equals( Employee rhs )
	{
		return ss == rhs.ss;
		// return name.equals(rhs.name);
	}

	public int hashCode()
	{ 
		return ss;
		// return name.hashCode();
	}
}