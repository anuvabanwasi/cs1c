package week07_part02;

import java.util.ArrayList;

/*
 * An object of type HashElement holds information about one item
 */
public class HashEntries<K,V>
{  
    private K key;
    private ArrayList<HashEntry<K,V>> dataEntries;

    public HashEntries(K key, V data)
    {
        this.key = key;
        dataEntries = new ArrayList<>();
        addEntry(key, data);
    }

    public void addEntry(K key, V data)
    {    
        HashEntry<K,V> entry = new HashEntry<>(key, data);
        dataEntries.add(entry);  
    }

    public K getKey()
    {    return key;  }

    public ArrayList<HashEntry<K,V>> getDataEntries()
    {    return dataEntries;  }
}