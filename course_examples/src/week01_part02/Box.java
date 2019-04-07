package week01_part02;

/**
 * A generic box which can hold any Object type.
 * @author Foothill College, Bita M
 *
 * @param <T>	Any child of the class Object.
 */
public class Box<T> 
{
	private T data;
	
	public void setData(T obj)
	{   this.data = obj;  }
	
	public T getData()
	{   return this.data;  }
}
