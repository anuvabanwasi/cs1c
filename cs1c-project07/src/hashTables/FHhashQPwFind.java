package hashTables;

import java.util.NoSuchElementException;

/**
 * Implementation of hash table that checks if a key is in the hash table.
 * Implements the class FHhashQPwFind to extend FHhashQP. The class takes a parameter KeyType to allow find
 * by key
 * @author Anuva
 *
 * @param <KeyType> The data type that was used as the key
 * @param <E> Generic type of the elements in the hash table
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E> {
	
	/**
	 * Find method that takes a key and returns the object with that key value.
	 * @param key represents the key of the element to look up.
	 * @return the object with that key value
	 */
	protected E find(KeyType key) {
		
		int index = findPosKey(key);
		
		//System.out.println("\t\tretrieving bucket " + index );
		
		if(mArray[index].state == ACTIVE)
			return mArray[index].data;
		else
			throw new NoSuchElementException("key " + key + " not found in hash table");
				
	}
	
	/**
	 * A function called by findPosKey() which mapping the key to a particular index in the table. Gets the hash value based on the key, not on the object
	 * @param key represents the key of the element to look up.
	 * @return hash value as a result of applying a hash function on the key. Invokes the hashCode() method of the key
	 */
	protected int myHashKey(KeyType key) {

		int hashVal;

		hashVal = key.hashCode() % mTableSize;
		if (hashVal < 0)
			hashVal += mTableSize;

		return hashVal;
	}
	
	/**
	 * A function that will be called by find() which finds the position based on the key, not on the object
	 * @param key represents the key of the element to look up.
	 * @return index in the hash table where the key is found
	 */
	protected int findPosKey(KeyType key) {
		int kthOddNum = 1;
		int index = myHashKey(key);
		
		while (mArray[index].state != EMPTY && mArray[index].data.compareTo(key) != 0) {
			//System.out.println("\n\ndata " + mArray[index].data + " key " + key);
			index += kthOddNum; // k squared = (k-1) squared + kth odd #
			kthOddNum += 2; // compute next odd #
			if (index >= mTableSize)
				index -= mTableSize;
		}
		return index;

	}
}
