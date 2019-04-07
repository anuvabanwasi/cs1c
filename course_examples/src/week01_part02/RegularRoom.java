package week01_part02;

public class RegularRoom 
{
	private String name;
	private Object lockCombination;

	public RegularRoom(String name) 
	{   this.name = name;  }

	// parameterized method
	public <T> T setLock(T newCombination)
	{  
		this.lockCombination = newCombination;
		return newCombination;  
	}

	public String toString()
	{   return name + "'s room with lock set to " + this.lockCombination;  }
}

