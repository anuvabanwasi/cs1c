package hashTables;

import cs1c.SongEntry;

/**
 * Wrapper class that encloses a SongEntry. Used to compare objects based on the song's integer id field.
 * @author Anuva
 *
 */
public class SongCompInt implements Comparable<Integer> {
	private SongEntry data;

	public SongCompInt(SongEntry e) {
		data = e;
	}

	/**
	 * Outputs all the data for a specific instance of SongCompInt
	 */
	public String toString() {
		return data.toString();
	}

	// we'll use compareTo() to implement our find on key. Compares objects based on
	// the song's id field.
	/**
	 * Compares objects based on the song's id field.
	 */
	@Override
	public int compareTo(Integer key) {
		return data.getID() - key;
	}

	// let equals() preserve the equals() provided by embedded data
	/**
	 * Compares equality of 2 SongCompInt objects based on Id
	 * @param obj another SongEntry object against which equality is to be tested
	 * @return true if the 2 SongCompInt objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		//System.out.println("\n\n\n SongCompInt equals got called");
		return data.getID() == ((SongCompInt)obj).data.getID();
	}
	
	/**
	 * Calculates hashcode based on the enclosed Song's id.
	 */
	@Override
	public int hashCode() {
		return data.getID();
	}
}