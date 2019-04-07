package subsetsum;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * The SublistSong class represents a subset or a sublist of a list containing SongEntry objects.
 * It has a reference to the list of songs from which the sublist was formed.
 * It stores the indices of the songs in the original list.
 * It uses an array of integers to represent the actual sublist. the integers are indices of the
 * elements in the original list.
 * The Sublist class provides an addItem(int) method to generate new subsets from existing subsets.
 * @author anuva
 */

public class SublistSong implements Cloneable {
	// sum of the elements in the sub list
	private double sum = 0;
	
	// reference to the original list from which sub lists will be formed
	private ArrayList<SongEntry> originalObjects;
	
	// the sublist stores the indices of the items in the original list
	private ArrayList<Integer> indices;

	// constructor creates an empty SublistSong (no indices)
	public SublistSong(ArrayList<SongEntry> shoppingList) {
		sum = 0.0;
		originalObjects = shoppingList;
		indices = new ArrayList<Integer>();
	}

	/**
	 * Return sum of duration of songs in sublist
	 * @return sum of duration of songs in subset
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * Clone the sub list, with a deep copy of the indices since each sublist will have its own indices
	 * This method is needed to do a copy of a sub list. A shallow copy of the original list is done since
	 * all sublists can refer to the same original list. However, a deep copy of the indices is needed, so
	 * that each sublist can have its indices list.
	
	 */
	public Object clone() throws CloneNotSupportedException {
		// shallow copy
		SublistSong newObject = (SublistSong) super.clone();
		// deep copy
		newObject.indices = (ArrayList<Integer>) indices.clone();

		return newObject;
	}

	/**
	 * Adds a song to sub list. Also updates sum of duration of songs in the sub list. 
	 * This method takes a song from the original list and forms a sublist - the sublist
	 * has that song added to the songs already in the calling ("this") sublist. It will adjust the 
	 * sum of the newly created sublist, since it will be larger than the sum of the 'this' object
	 * @param indexOfItemToAdd the index of item in the original list to add to the sub list
	 * @return new sublist representing a SublistSong to the client. The new sublist is 
	 * an augmented sublist comprised of a copy of the 'this' sublist with the index 
	 * (in the original set) of the item added to the list of indices. 
	 */
	public SublistSong addItem(int indexOfItemToAdd) {
		try {
			
			SublistSong subList = (SublistSong) this.clone();
			subList.indices.add(indexOfItemToAdd);
			subList.sum = sum + originalObjects.get(indexOfItemToAdd).getDuration();
			return subList;
		
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/**
	 * Display the songs of the sub list
	 */
	public void showSublist() {
		System.out.print("[ ");
		for (int i = 0; i < indices.size(); i++) {
			double duration = originalObjects.get(indices.get(i)).getDuration();
			if(i != indices.size()-1)
				System.out.print(duration + " ");
			else
				System.out.print(duration);
		}
		System.out.println("]");
	}
	
	/**
	 * Creates a list using indices to index into the original list of songs.
	 * Each SublistSong object has an array of ints which are indices in the original list 
	 * @return sublist with songs from original list
	 */
	public ArrayList<SongEntry> getListFromIndices() {
		
		ArrayList<SongEntry> result = new ArrayList<SongEntry>();
		
		for (int index : indices) 
			result.add(originalObjects.get(index));
		
		return result;
	}

	@Override
	public String toString() {
		return "SublistSong [sum=" + sum + ", originalObjects=" + originalObjects + ", indices=" + indices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indices == null) ? 0 : indices.hashCode());
		result = prime * result + ((originalObjects == null) ? 0 : originalObjects.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SublistSong other = (SublistSong) obj;
		if (indices == null) {
			if (other.indices != null)
				return false;
		} else if (!indices.equals(other.indices))
			return false;
		if (originalObjects == null) {
			if (other.originalObjects != null)
				return false;
		} else if (!originalObjects.equals(other.originalObjects))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		return true;
	}
	
}
