package stacks;

/**
 * Represents a last-in-first-out (LIFO) stack of objects. It extends LinkedList with push, pop, peek, isEmpty, size and clear 
 * operations to allow the linked list to be treated as a stack. When a stack is created it has no items. In a stack data structure
 * insertion and deletion is always at the top of the stack (LIFO). A special node is designated as top and keeps track of the top of the stack.
 * @author anuva
 *
 * @param <E> the type of elements held in this stack
 */
public class StackList<E> extends LinkedList<E>{
	
	// private members
	private String name;			// name of the stack
	private Node top;				// represents top of the stack
	
	/**
	 * Constructor. Creates an empty stack 
	 * @param name name of the stack
	 */
	StackList(String name) {
		super();
		this.name =  name;
		top = elementAt();
	}
	
	/**
     * Pushes an item onto the top of this stack. 
     * @param   x   the item to be pushed onto this stack.
     */
	public void push(E x) {
		if(x != null) {
			super.push(x);
			top = elementAt();
		} 
	}

	/**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return  The object at the top of this stack or null if the stack is empty
     */
	public E pop() {
		if(size() == 0)
			return null;
		
		E retVal = super.pop();
		top = elementAt();
		return retVal;
	}

	/**
     * Returns at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack or null if the stack is empty
     */
	public E peek() {
		if(isEmpty())
			return null;
		return top.data;
	}

	/**
     * Returns at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack or null if the stack is empty
     */
	public E peekFirst() {
		return peek();
	}

	
	/**
     * Returns at the object at the tail of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack 
     */
	public E peekLast() {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use peek() to look at element at top of the stack.");
	}
	
	/**
	 * Reset the stack
	 */
	public void clear() {
		super.clear();
		top = null;
	}
	
	/**
	 * Tests if this stack is empty.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Fundamental List operations.
	 */
	/**
	 * Returns the size of the stack
	 */
	public int size() {
		return super.size();
	}
	
	/**
	 * Add the specified element to the top of this stack.
	 * @param x element to be added to the end of this linked list
	 * @return true
	 */
	public boolean add(E x) {
		if(x != null) {
			addFirst(x);
			top = elementAt();
			return true;
		} else 
			return false;
	}
	
	/**
	 * Removes the element to the top of this stack
	 * @return the element previously at the top of the stack or null if the stack is empty
	 */
	public E remove() {
		if(isEmpty())
			return null;
		
		E val = removeFirst();
		top = elementAt();
		
		return val;
	}
	
	/**
	 * Adds the specified element as the top (first element) of this stack.
	 * @param x the element to add
	 * @return true
	 */
	public boolean offer(E x) {
		return offerFirst(x);
	}

	/**
	 * Adds the specified element as the top (first element) of this stack.
	 * @param x the element to add
	 * @return true
	 */
	public boolean offerFirst(E x) {
		boolean retVal = super.offer(x);
		top = elementAt();
		return retVal;
	}
	
	/**
	 * Removes the element as the top (first element) of this stack.
	 * @return the element previously at the top of the stack or null if the stack is empty
	 */
	public E poll() {
		if(isEmpty())
			return null;
		
		E retVal = super.poll();
		top = elementAt();
		return retVal;
	}
	
	/**
	 * Removes the element as the top (first element) of this stack.
	 * @return the element previously at the top of the stack or null if the stack is empty
	 */
	public E pollFirst() {
		if(isEmpty())
			return null;
		
		E retVal = super.poll();
		top = elementAt();
		return retVal;
	}
	
	/**
	 * Disallow insertion in the middle of the stack. In the stack insertion is always at the top.
	 */
	public void add(int index, E x) {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use push() to add elements from one end (head) of the stack. Insertion in the middle of the stack is not allowed.");
	}
	
	/**
	 * Disallow insertion at the end of the stack.In the stack insertion is always at the top.
	 */
	public void addLast(E x) {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use push() to add elements from one end (head) of the stack. Insertion in the tail of the stack is not allowed");

	}
	
	/**
	 * Disallow removal in the middle of the stack. In the stack deletion is always at the top.
	 */
	public E remove(int idx) {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use pop() to remove elements from one end (head) of the stack. Removal in the middle of the stack is not allowed");
	}
	
	/**
	 * Disallow removal at the end of the stack. In the stack deletion is always at the top.
	 */
	public E removeLast() {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use pop() to remove elements from one end (head) of the stack. Removal from the tail of the stack is not allowed");
	}
	
	/**
	 * Disallow removal at the end of the stack. In the stack deletion is always at the top.
	 */
	public E pollLast() {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use poll() to remove elements from one end (head) of the stack. Removal from the tail of the stack is not allowed");
	}
	
	/**
	 * Disallow insertion at the end of the stack. In the stack insertion is always at the top.
	 */
	public boolean offerLast(E x) {
		throw new UnsupportedOperationException("Stack is Last In First Out(LIFO). Use offerFirst() to add elements from one end (head) of the stack. Insertion in the tail of the stack is not allowed");
	}
		
	/**
	 * Print human readable representation of the stack
	 */
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append( name + " with " + size() + " links " + "\n");
		sb.append("[");
		for(int i = 0; i < size(); i++) {
			if( i != size()-1)
				sb.append(get(i) + " , ");
			else
				sb.append(get(i));
		}
		sb.append("]");
		sb.append("\n");
		
		return sb.toString();
	}

	
}
