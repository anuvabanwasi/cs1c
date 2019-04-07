package hashTables;


// HashEntry class for use by FHhashQP -----------------------
/**
 * New data type to act as the underlying type for the array representing the hash table.
 * Wrapper class for the actual data object. Includes a state member which represents whether the 
 * array position is ACTIVE, DELETED or EMPTY
 * @author Anuva
 *
 * @param <E> Generic type of the elements in the hash table
 */
public class HashEntry<E>
{
   public E data;
   public int state;
   
   public HashEntry( E x, int st )
   {
      data = x;
      state = st;
   }

   public HashEntry()
   {
      this(null, FHhashQP.EMPTY);
   }
}
