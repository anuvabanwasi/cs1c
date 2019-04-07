package week01_part02;

public class ParameterizedRoom<T>
{
	private String name;
	private T lockCombination;
	
	public ParameterizedRoom(String name)
	{
		this.name = name;
	}
	
	public T setLock(T newCombination)
	{  
		this.lockCombination = newCombination;
		return newCombination;  
	}
	
	public String toString()
	{
		String info = name + "'s ";
		info += "room with lock set to " + this.lockCombination;

		return info;
	}
}
