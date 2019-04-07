Week 9 Part 2 Practice Problems
========================

Learning Objectives
-------------------
- Heap Sort
- Merge Sort


Problem 1
---------

**Assume we are using a MAX heap as described in the current module.**

```java
   // heapsort public driver
   public static < E extends Comparable< ? super E > >
   void heapSort(E[] a)
   {
      int k, arraySize;
      E temp;

      // order the array using percolate down
      arraySize = a.length;
      for(k = arraySize/2; k >= 0; k-- )
         percolateDown(a, k, arraySize);

      // now remove the max element (root) and place at end of array
      for(k = arraySize - 1; k > 0; k-- )
      {
         // "remove" by placing at end of array
         temp = a[0];
         a[0] = a[k];
         a[k] = temp;
         percolateDown( a, 0, k );  // k represents the shrinking array size
      }
   }
   
   
   // percolateDown()
   protected static < E extends Comparable< ? super E > >
   void percolateDown(E[] a, int hole, int arraySize)</span>
   {
      int child;
      E tmp;

      for( tmp = a[hole]; 2 * hole + 1 < arraySize; hole = child )
      {
         child = 2 * hole + 1;
         // if 2 children, get the GREATER of the two (because MAX heap)
         if( child < arraySize - 1 && a[child].compareTo(a[child + 1]) < 0)
            child++;
         if( tmp.compareTo(a[child]) < 0 )   // MAX heap, not min heap
            a[hole] = a[child];
         else
            break;
      }
      a[hole] = tmp;
   }
```

**Show how heap sort processes the sequence below.**

```java
15, 19, 10, 7, 17 and 16
```

Fill in the following table* for the **percolateDown()** method:

<pre>
|    k     |    a[k]     |  p(a, k, arraySize)  |    hole     |    tmp     |   child     |     a[child]      | 2*hole + 1 < arraySize   | for your own use    
-----------------------------------------------------------------------------------------|----------------------------------------------|------------------
|    3     |      7      |  p(a, 3, 6)          |     3       | a[3] = 7   |    n/a      | Do we reach this? | 7 < 6   breaks from loop |
|          |             |                      |             |            |             |                   |                          |
|          |             |                      |             |            |             |                   |                          |
|          |             |                      |             |            |             |                   |                          |
|          |             |                      |             |            |             |                   |                          |
</pre>

*Note: The first line has been done for you.
        
<br><br>


Problem 2
---------

**Show how heap sort processes the sequence below.**


```java
142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811 and 102
```

Note: Include the steps and show the final result.
        
<br><br>


