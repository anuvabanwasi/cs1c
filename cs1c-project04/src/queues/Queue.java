package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Parameterized class representing a Queue which implements Iterable. Queue is a FIFO (First In First Out) data structure
 * Objects of type Queue manage items in a singly linked list where we can enqueue() items to the end and dequeue() items from the front of the queue.
 * @author anuva
 *
 * @param <E>
 */
public class Queue<E> extends LinkedList<E>{
	
	private String name;						// name of this instance of the queue
	private Node head;							// head points to the front of the queue
	private Node tail;							// tail points to the end of the queue
	
	/**
	 * Constructor - takes in a user assigned name and initializes the class attributes - name , head and tail pointers
	 * @param name   	name of this instance of the queue
	 */
	Queue(String name){
		super();
		this.name = name;
		head = null;
		tail = null;
	}
	
	/**
	 * Method to add an element to the end of the queue. Takes a generic item as the argument and adds the item to the end of the queue.
	 * @param val generic item representing the value to add to the queue
	 */
	public void enqueue(E val) {
		
		if(val != null) {
			super.addLast(val);
			head = elementAtHead();
			tail = elementAtTail();
			
		} 
	}
	
	/**
	 * Method to remove an element from the head of the queue. Receives no arguments and removes the item from the front of the queue. 
	 * This method should return the generic item dequeue-ed. This method should throw an NoSuchElementException if the queue is empty.
	 * @return the generic item dequeue-ed or throws  NoSuchElementException if attempt is made to dequeue from an empty queue.
	 */
	public E dequeue() {
		if(size() == 0)
			throw new NoSuchElementException("Queue is empty!");
		
		E retVal = super.removeFirst();
		head = elementAtHead();
		tail = elementAtTail();
		
		return retVal;
	}
	
	/**
	 * Method looks at the head of the queue for the least recently added item of the queue and returns an object of the generic type for the data seen at the front of the queue. 
	 * The item is not removed from the front of the queue.
	 * @return the generic item at the head of the queue
	 */
	public E peek() {
		if(isEmpty())
			return null;
		
		head = elementAtHead();		
		return head.data;
	}
	
	/**
	 * Method looks at the tail of the queue and returns an object of the generic type for the data seen at the tail of the queue. 
	 * The item is not removed from the tail of the queue.
	 * @return the generic item at the tail of the queue
	 */
	public E peekLast() {
		if(isEmpty())
			return null;
		
		tail = elementAtTail();		
		return tail.data;
	}
	
	/**
	 * Checks if the queue is empty
	 * @return true if the queue is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the size of the queue
	 * @return number of items in the queue
	 */
	public int size() {
		return super.size();
	}
	
	/**
	 * Reset the queue
	 */
	public void clear() {
		super.clear();
		head = null;
		tail = null;
	}
	/**
	 * Disallow insertion in the middle of the queue. In the queue insertion is always at the tail.
	 */
	public void add(int index, E x) {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use enqueue() to add elements from one end (tail) of the queue. Insertion in the middle of the queue is not allowed.");
	}
	
	/**
	 * Disallow insertion at the front of the queue.In the queue insertion is always at the tail.
	 */
	public void addFirst(E x) {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use enqueue() to add elements from one end (tail) of the queue. Insertion in the head of the queue is not allowed");

	}
	
	/**
	 * Disallow removal in the middle of the queue. In the queue deletion is always at the front.
	 */
	public E remove(int idx) {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use dequeue() to remove elements from one end (head) of the queue. Removal in the middle of the queue is not allowed");
	}
	
	/**
	 * Disallow removal at the end of the queue. In the queue deletion is always at the front.
	 */
	public E removeLast() {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use dequeue() to remove elements from one end (head) of the queue. Removal from the tail of the queue is not allowed");
	}
	
	/**
	 * Disallow removal at the end of the queue. In the queue deletion is always at the head.
	 */
	public E pollLast() {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use dequeue() to remove elements from one end (head) of the queue. Removal from the tail of the queue is not allowed");
	}
	
	/**
	 * Disallow insertion at the head of the queue. In the queue insertion is always at the tail.
	 */
	public boolean offerFirst(E x) {
		throw new UnsupportedOperationException("Queue is First In First Out(FIFO). Use offerLast() to add elements from one end (tail) of the queue. Insertion in the head of the queue is not allowed");
	}
	
	/**
	 * Print items in queue in readable format
	 * @return String representation of items in the queue
	 */
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append( name  + ":\n");
		sb.append("[");
		
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			sb.append(it.next() );
			if(it.hasNext())
				sb.append( ";\n ");
		}
		
		sb.append("]");
		sb.append("\n");
		
		return sb.toString();
	}

	// Accessor
	/**
	 * Return the name of the queue
	 * @return String representing name of the queue
	 */
	public String getName() {
		return name;
	}
}
