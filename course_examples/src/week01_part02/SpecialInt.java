package week01_part02;

class SpecialInt extends MyInt
{
	private int myNumber;

	public SpecialInt(int n) 
	{ 
		super(n); 
		this.myNumber = n;
	}

	public String toString()
	{
		return "" + myNumber;
	}
}