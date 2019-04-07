package week04_part01;

public class BinarySearch 
{
	public static < E extends Comparable<? super E > >
	   int binarySearch(E[] array, E searchItem,
	                    int firstIndex, int lastIndex)
	      {
	         int middleIndex, result;
	         
	         if (firstIndex > lastIndex)
	            return -1;
	            
	         middleIndex = (firstIndex + lastIndex) / 2;
	         result = searchItem.compareTo(array[middleIndex]);
	         
	         if (result==0)
	            return middleIndex;   // found it!
	         else if (result < 0)
	            return binarySearch( array, searchItem, firstIndex, middleIndex-1);
	         else
	            return binarySearch( array, searchItem, middleIndex+1, lastIndex); 
	      }
}
