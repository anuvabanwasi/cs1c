package week07_part02;

import java.util.ArrayList;
import java.util.Iterator;

/*
 *  Linear hash table uses closed addressing.
 *  Addressing scheme is to hash to an index and search the linked list of data at each index.
 *  
 *  source*: adapted from: M. Weiss, "Data Structures and Algo", 3rd Ed
 *  source**: and http://en.wikipedia.org/wiki/Primality_test
 *  source***: adapted from M. Loceff, CS1C Fall 2014
 */
class FHmapSC<K,V>
{
    protected static final boolean ENABLE_DEBUG = false;

    // minimum table size
    private static final int INIT_CAPACITY = 7;

    // load factor to reduce clustering**
    private static final double MAX_LOAD_FACTOR = 0.5;

    private int tableSize;
    private int numElements;

    private HashEntries<K,V>[] table;

    /* 
     * Create a table with default size
     */
    public FHmapSC()
    {
        this(INIT_CAPACITY);
    }

    /* 
     * Determine the next prime number as the size of the table
     * Create the table.
     */ 
    public FHmapSC(int requestedSize)
    {
        numElements = 0;

        if (requestedSize < INIT_CAPACITY)
            tableSize = INIT_CAPACITY;
        else
            tableSize = nextPrime(requestedSize);

        allocateTable();
    }


    /*
     * Internal method to find a prime number at least as large as n.*
     */
    protected static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /*
     * Internal method to test if a number is prime.**
     */
    private static boolean isPrime( int n )
    {
        // if divisible by 2 and 3 return true
        if( n == 2 || n == 3 )
            return true;

        // if trivial case or divisible by 2 or 3 then it's not a prime
        if( n == 1 || n % 2 == 0  || n %3 == 0)
            return false;


        // if n is not prime, then it's smallest non-trivial divisor is not greater than sqrt(n)
        double sqrtN = Math.floor(Math.sqrt(n));
        for (int i = 5; i <= sqrtN; i += 6) 
        {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    /* 
     * Calculates the hash code for the given key
     */
    public int myHash(K key)
    {
        int hash = Math.abs(key.hashCode());
        int index = hash % tableSize;

        if (ENABLE_DEBUG)    System.out.printf("DEBUG: %s : %d \n", key, index);
        return index;
    }


    /*
     *  Allocate space for elements to be hashed
     */
    @SuppressWarnings("unchecked")
    private void allocateTable()
    {
        table = new HashEntries[tableSize];
    }


    /*
     * Move elements in original table to a larger table.***
     */
    private void reHash()
    {
        // we save old list and size then we can reallocate freely
        HashEntries<K,V>[] origTable = table;

        // increase the table's capacity by the next available prime size
        tableSize = nextPrime(2*origTable.length);

        // reset the number of elements
        numElements = 0;

        // allocate a larger empty array
        allocateTable();  

        // insert all elements in original table by rehashing
        for(int i = 0; i < origTable.length; i++)
        {
            HashEntries<K,V> current = origTable[i];
            if (current != null)
            {                
                // reuse the insert algorithm to put each element the new table
                for(HashEntry<K,V> entry : current.getDataEntries())
                {
                    // use the element's own key to re-insert into hash table
                    insert(entry.getKey(), entry.getData());
                }
            }
        } // for all indices of table
    }

    /*
     *  Insert key value pair to the table.
     */
    public void insert(K key, V data)
    {
        // the ratio of number of elements in table >= load factor
        // so move all elements to a new table
        if (numElements >= Math.ceil(tableSize*MAX_LOAD_FACTOR))
        {
            reHash();
        }

        // calculate hash code from key
        int hash = myHash(key);

        // if index is empty, then create a new HashEntries object to
        // manage the current and future elements mapped to index
        if (table[hash] == null)
        {
            HashEntries<K,V> mappedElements = new HashEntries<>(key, data);
            table[hash] = mappedElements;
        }
        else
        {
            // check if data already exists in list of elements at hashed index
            // if so do not insert duplicate data
            if (table[hash].getDataEntries().contains(data))
                return;

            // otherwise, add element to the chain 
            table[hash].addEntry(key, data);
        }
        
        numElements++;
    }

    /*
     * checks if a key exists in table
     */
    public boolean containsKey(K key)
    {
        // TODO: Complete the implementation such that it checks if a key exists
    	return false;
    }

    /*
     * Delete the key (and all corresponding value) from the table
     */
    public ArrayList<V> remove(K key)
    {
        // calculate hashcode from key
        int hash = myHash(key);

        // if index is empty then nothing to return 
        if (table[hash] == null)
        {
            return null;
        }

        // we've found the requested index
        // so save the chain of elements at index
        ArrayList<HashEntry<K,V>> mappedElements = table[hash].getDataEntries();
        
        // Note: we do not need to provide client with implementation details, i.e. use of HashEntry
        // so only save the data portion
        ArrayList<V> dataList = new ArrayList<>();
        for (HashEntry<K,V> entry : mappedElements)
            dataList.add(entry.getData());
        
        // remove the chain from  table
        table[hash] = null;

        // deduct from number of elements in table
        numElements -= mappedElements.size();

        // return the saved elements
        return dataList;
    }


    /*
     * Remove the value from the table
     */
    public V removeValue(K key, V value)
    {
        // calculate hashcode from key
        int hash = myHash(key);

        // if index is empty then value does not exist in table
        if (table[hash] == null)
        {
            return null;
        }

        // we've found the requested index
        // so save the chain of elements at index
        ArrayList<HashEntry<K,V>> mappedElements = table[hash].getDataEntries();

        // check if chain contains requested element
        // Note: for safe removal use iterator instead of counter loop
        // See notes on ListIterator from previous module
        Iterator<HashEntry<K,V>> itr = mappedElements.iterator();
        V data = null;
        while(itr.hasNext())
        {
            HashEntry<K,V> entry = itr.next();
            data = entry.getData();
            if (data.equals(value))
                itr.remove();
        }

        numElements--;

        // return the saved element
        return data;
    }


    /*
     * A string representation of the table.
     */
    public String toString()
    {
        String details = "{";

        for (int i = 0; i < tableSize; i++)
        {
            HashEntries<K,V> mappedElements = table[i];

            details += "#" + i + " ";

            // contents of table index ---------------
            
            // index is empty
            if (table[i] == null)
                details += "null,";

            // valid index
            // one or more elements at index
            else
            {
                details += mappedElements.getKey();

                details += " : {";
                for (HashEntry<K,V> entry : mappedElements.getDataEntries())
                {
                    details += entry.getData() + ", ";
                }

                details += "}";
            }
            // formatting
            if (i != tableSize-1)
                details += "\n";
        }
        return details + "\n}";
    }


    /*
     * size of hash table
     */
    public int getTableSize()
    {    return tableSize;  }


    /*
     * number of elements in table
     */
    public int getElementCount()
    {    return numElements;  }
}
   
