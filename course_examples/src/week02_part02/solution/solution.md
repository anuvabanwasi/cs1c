Week 2 Part 2 Practice Problems
========================

Learning Objectives
-------------------
- FHlinkedList class
- Getting started with FHstack


Problem 1
---------
**Given the class FHlinkedList in the modules, write a brief description of when the methods `getFirst()` vs `getLast()` vs `getNode()` should be used.**

*Description:*

a) and b) Provide a fast way to look up either end of the linked list.

c) The `getNode()` method returns the `Node` itself, and the caller can decide what it wants to do with the node.

<br>

*A bit more detail:*
`getNode()` tests whether index is closer to the front or back of the list and starts hopping from the closer of the two terminals.  An interesting performance boost idea, if you absolutely needed linked lists, but also had to do a lot of random accesses, is to include a finite number of intermediate tent-posts between mHead and mTail.  For example, you could provide `m_middle`, which always points to `mSize/2`.  This would halve an index-based search-time at the cost of adding a division (or really shift, since it is dividing by 2) and an assignment to every `add()` and `remove()`.  And if you decided this was acceptable, and you wanted a stringer drug, you could add four, eight or more of these beasts.  It is an exotic idea for a special-purpose tool.  We won't do it.

<br><br>


Problem 2
---------
**What is the difference between returning an element via the `FHitertor`'s `next()` method in class `FHarrayList` vs the class `FHlinkedList` ?**


The nested `FHiterator` in class `FHlinkedList`'s is traversing via `mCurrentNode`, which is a `Node` object. 
Yet the `next()` method wants us to return an `E Object`. Therefore, we dereference `last_node_returned` to get at the data with the final statement:

```java
    return mLastNodeReturned.data;
```

<br><br>


Problem 3
---------

*Note: This is part of your programming assignment.* 

**The output is:**

```java
top: 9
9
8
7
6
5
4
3
2
1
0
null
null
```

<br>

