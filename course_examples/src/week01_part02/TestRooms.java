package week01_part02;

public class TestRooms 
{
	public static void main(String [] args)
	{
		RegularRoom crib = new RegularRoom("Will");
		
		int combo01 = crib.setLock(1234);	// 	QUESTION: What happens here?
		System.out.println(crib);
		
		int combo02 = crib.setLock(9090);	// 	QUESTION: What about here?
		System.out.println(crib);
		
		String combo03 = crib.setLock("fresh");	// 	QUESTION: ...and here?
		System.out.println(crib);
		
		System.out.println("");
		
		
		ParameterizedRoom<Integer> cave = new ParameterizedRoom<Integer>("Ali");
		
		combo01 = cave.setLock(1234);	// 	QUESTION: What happens here?
		System.out.println(cave);
		
		combo02 = cave.setLock(9090);	// 	QUESTION: What about here?
		System.out.println(cave);
		
		combo03 = cave.setLock("agimagi");	// 	QUESTION: ...and here?
		System.out.println(cave);
	}
}
