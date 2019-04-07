package week07_part02;


/**
 * An object of type HashElement holds information about one item
 */
public class HashEntry<K,V>
{  
    private K key;
    private V data;
    private boolean deleted;

    public HashEntry(K key, V data)
    {
        this.key = key;
        this.data = data;
        deleted = false;
    }

    public void setDeleted()
    {    deleted = true; }

    public boolean isDeleted()
    {    return deleted;  }

    public K getKey()
    {    return key;  }

    public V getData()
    {    return data;  }
	
    public String toString()
    {    return data.toString(); }
}