package subsetsum;

import java.util.ArrayList;

/**
 * The Sublist class represents a subset or a sublist of a list containing grocery prices.
 * Each sublist object has a reference to the list of shopping prices from which the sublist was formed.
 * It stores the indices of the items in the original list.
 * The sublist uses an array of ints to represent the actual sublist. The ints are indices of the
 * elements in the original list.
 * Note - The Sublist class provides an addItem(int) method to generate new subsets from existing subsets.
 * @author anuva
 *
 */
public class Sublist implements Cloneable {
	
	// sum of the elements in the sub list
	private double sum = 0;
	
	// reference to the original list from which sub lists will be formed
	private ArrayList<Double> originalObjects;
	
	// the sublist stores the indices of the items in the original list
	private ArrayList<Integer> indices;

	/**
	 * Constructor creates an empty Sublist (no indices)
	 * @param shoppingList list of prices of grocery items
	 */
	public Sublist(ArrayList<Double> shoppingList) {
		sum = 0.0;
		originalObjects = shoppingList;
		indices = new ArrayList<Integer>();
	}

	/**
	 * Return sum of items in sublist
	 * @return sum of elements in sub set
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * Clone the sub list, with a deep copy of the indices since each sublist will have its own indices
	 * This method is needed to do a copy of a sub list, a shallow copy of the original list is done since
	 * all sublists can refer to the same original list. However, a deep copy of the indices is needed, so
	 * that each sublist can have its own indices list
	 */
	public Object clone() throws CloneNotSupportedException {
		// shallow copy
		Sublist newObject = (Sublist) super.clone();
		// deep copy
		newObject.indices = (ArrayList<Integer>) indices.clone();

		return newObject;
	}

	/**
	 * This method generates a new subset from existing subsets. Also updates sum of items in the sub list. 
	 * This method takes an item from the originalObjects list and forms a sublist - the sublist
	 * has that item added to the items already in the calling ("this") sublist. It will adjust the 
	 * sum of the newly created sublist, since it will be larger than the sum of the 'this' object
	 * @param 	indexOfItemToAdd the index of item in the original list to add to the sub list
	 * @return 	new sublist representing a Sublist to the client. The new sublist is 
	 * an augmented sublist comprised of a copy of the 'this' sublist with the index 
	 * (in the original set) of the item added to the list of indices. 
	 */
	
	public Sublist addItem(int indexOfItemToAdd) {
		Sublist subList = null;
		
		try {
			subList = (Sublist) this.clone();
			subList.indices.add(indexOfItemToAdd);
			subList.sum = sum + originalObjects.get(indexOfItemToAdd);
			return subList;
		
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return subList;
	}

	/**
	 * Display the elements of the sub list
	 */
	public void showSublist() {
		
		System.out.print("[ ");
		for (int i = 0; i < indices.size(); i++) {
			double item = originalObjects.get(indices.get(i));
			if(i != indices.size() - 1)
				System.out.print(item + ", ");
			else
				System.out.print(item);
		}
		System.out.println("]");
		
	}
	
	/**
	 * Creates a list using the indices to index into the original list of items
	 * @return sublist with items from original list
	 */
	public ArrayList<Double> getListFromIndices() {
		
		ArrayList<Double> result = new ArrayList<Double>();
		
		for (int index : indices) {
			double item = originalObjects.get(index);
			result.add(item);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Sublist [sum=" + sum + ", originalObjects=" + originalObjects + ", indices=" + indices  +  " , actualItems=  " + getListFromIndices() + "]";
	}
}
