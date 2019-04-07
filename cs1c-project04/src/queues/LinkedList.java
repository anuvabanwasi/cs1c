package queues;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Singly-linked list implementation of the Linked list. Provides operations to add at the head, add at tail, add in the middle of linked list.
 * Also provides operations to remove at the head, remove at the tail and in the middle of the linked list. Provides push , pop and peek methods to allow
 * linked list to function as a stack. Provides a fail fast iterator to iterate through the linked list and fail in case the list is
 * structurally modified at any time after the iterator is created.
 * @author anuva
 *
 * @param <E> the type of elements held in this collection
 */
public class LinkedList<E> implements Iterable<E> {
	/**
	 * Definition of inner Node class
	 */
	class Node {
		Node next;
		E data;

		/**
		 * Constructor 
		 * @param x data value of node
		 * @param nxt reference to the next node
		 */
		Node(E x, Node nxt) {
			data = x;
			next = nxt;
		}
	}

	/**
	 * Utility Pair class for returning (Node, index} pairs.
	 */
	private class Pair {
		Node node;
		int index;

		/**
		 * Constructor
		 * @param node node
		 * @param index index of node in linked list
		 */
		Pair(Node node, int index) {
			this.node = node;
			this.index = index;
		}
	};

	/**
	 * Principal private data for LinkedList.
	 */
	
	// size of linked list
	private int mSize;
	
	// Head and Tail nodes of linked list
	private Node mHead, mTail;

	/**
	 * For internal use
	 */
	private static final int NOT_FOUND = -1;

	/**
	 * For iterator concurrency testing
	 */
	private int modCount = 0;

	/**
	 * Default constructor
	 */
	public LinkedList() {
		clear();
	}

	/**
	 * Collection to add to the list.
	 * 
	 * @param rhs A collection of elements.
	 */
	public LinkedList(Collection<? extends E> rhs) {
		clear();
		addAll(rhs);
	}

	/**
	 * Fundamental List operations.
	 */
	
	/**
	 * Returns the size of the linked list
	 * @return integer representing the number of elements in the linked list
	 */
	public int size() {
		return mSize;
	}

	/**
	 * Returns true if the linked list is empty
	 * @return boolean value indicating if linked list is empty
	 */
	public boolean isEmpty() {
		return mSize == 0;
	}

	/**
	 * Reset the linked list by removing references to the previous elements.
	 */
	public void clear() {
		mHead = null;
		mTail = null;
		mSize = 0;
		modCount++;
	}

	// private helper methods ----------------------------------------------------

	/**
	 * Returns the Node in the index-th position of the list
	 * 
	 * @param index The requested index.
	 * @return The node at the requested index.
	 */
	private Node getNode(int index) {
		return getNode(index, 0, size() - 1);
	}

	/**
	 * Returns the Node in the index-th position of the list
	 * @param idx The requested index.
	 * @param lower lower bound for index in linked list
	 * @param upper upper bound for index in linked list
	 * @return The node at the requested index.
	 */
	private Node getNode(int idx, int lower, int upper) {

		if (idx < lower || idx > upper) {
			throw new IndexOutOfBoundsException();
		}

		Node p = mHead;
		for (int i = 0; i < idx; i++)
			p = p.next;

		return p;
	}

	/**
	 * Returns both the node and index of first occurrence of Object o
	 * 
	 * @param o The requested object
	 * @return The first occurrence of the requested object.
	 */
	private Pair getFirstOccurrence(Object o) {
		Node p;
		int k;

		if (o != null) {
			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (o.equals(p.data))
					return new Pair(p, k);
		} else {
			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (p.data == null)
					return new Pair(p, k);
		}
		return new Pair(null, NOT_FOUND);
	}

	/**
	 * Returns both the node and index of first occurrence of Object o
	 * 
	 * @param o The requested object
	 * @return The last occurrence of the requested object.
	 */
	private Pair getLastOccurrence(Object o) {
		Node p, q = null;
		int k, qK = -1;

		if (o != null) {

			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (o.equals(p.data)) {
					q = p;
					qK = k;
				}
		} else {
			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (o.equals(p.data)) {
					q = p;
					qK = k;
				}
		}

		return new Pair(q, qK);

	}

	// LinkedList public methods ---------------------------------------------
	// accessors and mutators
	
	/**
	 * Returns the element at the specified position in this linked list.
	 * @param index index of the element to return
	 * @return the element at the specified position in this linked list
	 */
	public E get(int index) {
		if (index < 0 || index >= mSize)
			throw new IndexOutOfBoundsException();
		return getNode(index).data;
	}

	/**
	 * Returns the first element in this linked list.
	 * @return the first element in this linked list
	 */
	public E getFirst() {
		if (mSize == 0)
			throw new NoSuchElementException();
		return mHead.data;
	}

	/**
	 * Returns the last element in this linked list. Throws NoSuchElementException if list is empty.
	 * @return the last element in this linked list
	 */
	public E getLast() {
		if (mSize == 0)
			throw new NoSuchElementException();
		
		return mTail.data;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of the linked list. 
	 * @return the value of the element at the head of this linked list or null if the list is empty
	 */
	public E peek() {
		if (mSize == 0)
			return null;
		return mHead.data;
	}

	/**
	 * Retrieves, but does not remove, the first element of the linked list
	 * @return the value of the element at the head of this linked list or null if the list is empty
	 */
	public E peekFirst() {
		if (mSize == 0)
			return null;
		return mHead.data;
	}

	/**
	 * Retrieves, but does not remove, the last element of the linked list. Throws NoSuchElementException if list is empty.
	 * @return the value of the element at the tail of this linked list
	 */
	public E peekLast() {
		if (mSize == 0)
			throw new NoSuchElementException();

		return mTail.data;
	}

	// poll() equiv. to pollFirst()
	/**
	 * Retrieves and removes the head (first element) of this linked list.
	 * @return the element at the head of this linked list
	 */
	public E poll() {
		if (mSize == 0)
			return null;
		
		E retVal = mHead.data;
		
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			mHead = mHead.next;
		}

		modCount++;
		mSize--;

		return retVal;
	}

	/**
	 * Retrieves and removes the head (first element) of this linked list.
	 * @return the element at the head of this linked list
	 */
	public E pollFirst() {
		if (mSize == 0)
			return null;
		
		E retVal = mHead.data;
		
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			mHead = mHead.next;
		}
		
		modCount++;
		mSize--;

		return retVal;
	}

	/**
	 * Retrieves and removes the tail (last element) of this linked list.
	 * @return the element at the tail of this linked list
	 */
	public E pollLast() {
		if (mSize == 0)
			return null;
			
		E retVal = mTail.data;
		
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			Node q = mHead;
	
			while (q.next != mTail) {
				q = q.next;
			}
	
			q.next = null;
			mTail = q;
		}

		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Returns true if this list contains the specified element. Uses equals to determine equality
	 * @param o element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	public boolean contains(Object o) {
		Node p;
		int k;

		if (o != null) {
			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (o.equals(p.data))
					return true;
		} else {
			for (k = 0, p = mHead; k < mSize; p = p.next, k++)
				if (p.data == null)
					return true;
		}

		return false;
	}

	/**
	 * Replaces the element at the specified position in this linked list with the
     * specified element.
	 * @param index index of the element to replace
	 * @param x the value to be stored at the specified position
	 * @return the value previously at the specified position
	 */
	public E set(int index, E x) {
		E retVal;
		Node p;

		if (index < 0 || index >= mSize)
			throw new IndexOutOfBoundsException();

		p = getNode(index);
		retVal = p.data;
		p.data = x;
		return retVal;
	}

	/**
	 * Add the specified element to the end of this linked list.
	 * @param x element to be added to the end of this linked list
	 * @return true
	 */
	public boolean add(E x) {
		addLast(x);
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this linked list.
	 * @param index index at which the specified element is to be inserted
	 * @param x element element to be inserted
	 */
	public void add(int index, E x) {
		//System.out.println("invoked with " + index + ":  x " + x);
		if (index < 0 || index > mSize) // == mSize allowed
			throw new IndexOutOfBoundsException();

		if (size() == 0)
			addFirst(x);
		else if (index == size())
			addLast(x);
		else {
			addBefore(getNode(index, 0, size()), x);
			mSize++;
			modCount++;
		}
	}

	/**
	 * Inserts the specified element at the beginning of this linked list.
	 * @param x the element to add
	 */
	public void addFirst(E x) {
		if(mHead == null) {
			Node n = new Node(x, null);
			mHead = n;
			mTail = n;
		} else {
			Node n = new Node(x, mHead);
			mHead = n;
		}

		mSize++;
		modCount++;
	}

	/**
	 * Adds the specified element to the end of this linked list.
	 * @param x the element to add
	 */
	public void addLast(E x) {
		
		if(mHead == null) {
			Node n = new Node(x, null);
			mHead = n;
			mTail = n;
		} else {
			Node n = new Node(x, null);
			mTail.next = n;
			mTail = n;
		}
	
		mSize++;
		modCount++;
	}

	/**
	 * Adds the specified element as the tail (last element) of this linked list.
	 * @param x the element to add
	 * @return true
	 */
	public boolean offer(E x) {
		add(x);
		return true;
	}

	/**
	 * Inserts the specified element at the front of this linked list.
	 * @param x the element to insert
	 * @return true
	 */
	public boolean offerFirst(E x) {
		addFirst(x);
		return true;
	}

	/**
	 * Inserts the specified element at the end of this linked list.
	 * @param x the element to insert
	 * @return true
	 */
	public boolean offerLast(E x) {
		addLast(x);
		return true;
	}

	/**
	 * Equiv. to removeFirst()
	 */
	/**
	 * Pops an element from the stack represented by this linked list.  Removes and returns the first element of this linked list.
	 * @return the element at the front of this list (top of the stack)
	 */
	public E pop() {
		if (mSize == 0)
			throw new NoSuchElementException();

		E retVal = mHead.data;
			
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			mHead = mHead.next;
		}

		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Equiv. to addFirst()
	 */
	/**
	 * Pushes an element onto the stack represented by this linked list.  Inserts the element at the front of this linked list.
	 * @param x the element to push to the stack
	 */
	public void push(E x) {
		
		addFirst(x);

	}

	/**
	 * Retrieves and removes the head (first element) of this linked list.
	 * @return the element at the head of the linked list
	 */
	public E remove() {
		if (mSize == 0)
			throw new NoSuchElementException();
		
		E retVal = mHead.data;
			
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			mHead = mHead.next;
		}

		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Removes and returns the first element from this linked list.
	 * @return the first element from this linked list
	 */
	public E removeFirst() {

		if (mSize == 0)
			throw new NoSuchElementException();

		E retVal = mHead.data;
		
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			mHead = mHead.next;
		}

		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Removes and returns the last element from this linked list.
	 * @return the last element from this linked list
	 */
	public E removeLast() {
		// low-level methods should be efficient - don't call other rmv()
		if (mSize == 0)
			throw new NoSuchElementException();

		E retVal = mTail.data;
		
		if(mHead == mTail) {
			mHead = null;
			mTail = null;
		} else {
			Node q = mHead;
	
			while (q.next != mTail) {
				q = q.next;
			}
	
			q.next = null;
			mTail = q;
		}
		
		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Removes the element at the specified position in this linked list. 
	 * @param idx the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public E remove(int idx) {

		E retVal = remove(getNode(idx));

		mSize--;
		modCount++;

		return retVal;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator. 
	 * @param rhs collection containing elements to be added to this list
	 * @return true if this list changed as a result of the call
	 */
	public boolean addAll(Collection<? extends E> rhs) {
		// this is not a low-level or common function, so ok to call add()
		if (rhs.size() == 0)
			return false;
		for (E x : rhs)
			add(x);
		return true;

	}

	/**
	 * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.
	 * @param index index at which to insert the first element
     *              from the specified collection
	 * @param rhs collection containing elements to be added to this list
	 * @return true if this list changed as a result of the call
	 */
	public boolean addAll(int index, Collection<? extends E> rhs) {
		// this is not a low-level or common function, so ok to call add()
		if (rhs.size() == 0)
			return false;

		int k = 0;
		for (E x : rhs)
			add(k++, x);
		return true;

	}

	/**
	 * Returns true if this list all the elements of the specified collection.
	 * @param c collection containing elements to check
	 * @return true if this list all the elements of the specified collection.
	 */
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	/**
	 * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(Object o) {
		return getFirstOccurrence(o).index;
	}

	/**
	 * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
	 */
	public int lastIndexOf(Object o) {
		return getLastOccurrence(o).index;
	}

	/**
	 * Return true if this linked list is equal to the other linked list
	 */
	public boolean equals(Object o) {
		Node p1, p2;
		LinkedList<E> that;
		E thisData, thatData;

		if (!(o instanceof LinkedList<?>))
			return false;

		that = (LinkedList<E>) o;
		if (that.size() != mSize)
			return false;

		for (p1 = mHead, p2 = that.mHead; p1 != mTail; p1 = p1.next, p2 = p2.next) {
			thisData = p1.data;
			thatData = p2.data;
			// we allow null values, so we have to test null==null first
			if (thisData == null || thatData == null) {
				if (thisData != null || thatData != null)
					return false;
			} else {
				if (!thisData.equals(thatData))
					return false;
			}
		}
		return true;
	}

	/**
	 * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
	 * @return an array containing all of the elements in this list
     *         in proper sequence
     */
	public Object[] toArray() {
		int k;
		Node p;

		Object[] retArray = new Object[mSize];
		for (k = 0, p = mHead; k < mSize; k++, p = p.next)
			retArray[k] = p.data;
		return retArray;
	}

	/**
	 * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element);
	 * @param userArray the array into which the elements of the list are to
     *          be stored
	 * @return an array containing the elements of the list
	 */
	public <T> T[] toArray(T[] userArray) {
		int k;
		Node p;
		Object[] retArray;

		if (mSize > userArray.length)
			retArray = new Object[mSize];
		else
			retArray = userArray;

		for (k = 0, p = mHead; k < mSize; k++, p = p.next)
			retArray[k] = p.data;

		if (mSize < userArray.length)
			retArray[mSize] = null;

		return (T[]) userArray;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * @return the head of this list
	 */
	public E element() {
		if (mSize == 0)
			throw new NoSuchElementException();
		return mHead.data;
	}
	
	/**
	 * Returns the node containing the first element of the list
	 * @return node containing the first element of the list
	 */
	public Node elementAtHead() {
		return mHead;
	}
	
	/**
	 * Returns the node containing the first element of the list
	 * @return node containing the first element of the list
	 */
	public Node elementAtTail() {
		return mTail;
	}

	// must be defined because this implements Collection ----------------------

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	// this is the only method that should really be supported, but I don't ----
	public LinkedList<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Insert a new node before the specified node
	 * @param node Node representing node before which to insert the new node
	 * @param x the element to insert
	 */
	private void addBefore(Node node, E x) {

		Node newNode = new Node(x, node);

		Node q = mHead;
		

		Node p = mHead;

		//System.out.println("p has " + p.data);

		while (p != node) {
			q = p;
			p = p.next;
		}

		q.next = newNode;

	}

	/**
	 * Remove the specified node
	 * @param node node to remove
	 * @return the element previously at the specified position
	 */
	private E remove(Node node) {

		Node q = mHead;

		Node p = mHead;

		while (p != node) {

			q = p;
			p = p.next;
		}

		q.next = p.next;

		return node.data;
	}

	/**
	 * Iterate and print elements of linked list
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			E retVal = it.next();
			sb.append(retVal + " ");
		}
		sb.append("]");
		sb.append("\n");
		
		return sb.toString();
	}
	
	
	// ------------- Iterator / ListIterator -----------------------------------
	public java.util.Iterator<E> iterator() {
		return new FHiterator();
	}

	/**
	 * Internal Iterator class
	 */
	private class FHiterator implements java.util.Iterator<E> {
		// for use with derived FHlistIterator methods remove(), set().
		protected static final int NOT_VALID = -1;
		protected static final int NEXT = 10;

		protected Node mCurrentNode;
		protected int mCurrentIndex;

		// for ConcurrentModificationExceptions
		protected int mIterModCount = modCount;

		// for IllegalStateExceptions
		protected Node mLastNodeReturned = null; // valid: any Node object
		protected int mLastIteration = NOT_VALID; // valid: NEXT 

		// required interface implementations -----------------------------------

		/**
		 * Returns true if there are more elements in the list
		 */
		public boolean hasNext() {
			return mCurrentIndex < mSize;
		}

		/**
		 * Next element in the list
		 */
		public E next() {
			if (mIterModCount != modCount) {
				System.out.println( mIterModCount + " : " + modCount);
				throw new ConcurrentModificationException();
			}
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			mLastNodeReturned = mCurrentNode;
			mCurrentNode = mCurrentNode.next;
			mCurrentIndex++;
			mLastIteration = NEXT;
			return mLastNodeReturned.data;
		}

		/**
		 * Use iterator to remove an element from the linked list
		 */
		public void remove() {
			if (mIterModCount != modCount) {
				System.out.println( mIterModCount + " : " + modCount);
				throw new ConcurrentModificationException();
			}
			if (mLastNodeReturned == null)
				throw new IllegalStateException();
			LinkedList.this.remove(mLastNodeReturned);
			if (mLastIteration == NEXT)
				mCurrentIndex--;
			mSize--;
			mIterModCount++;
			modCount++;
			mLastNodeReturned = null;
		}

		/**
		 * Constructors (default access for package only)
		 */
		FHiterator() {
			mCurrentNode = mHead;
			mCurrentIndex = 0;
		}
	}

}
