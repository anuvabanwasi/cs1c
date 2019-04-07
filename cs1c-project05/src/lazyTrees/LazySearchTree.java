package lazyTrees;

import java.util.*;

import cs1c.Traverser;

/**
 * Binary Search Tree which implements lazy deletion. Each node includes a boolean member called deleted. This member 
 * would be set to true if the node has been remove()-d from the tree. During a remove operation in a BST with lazy deletion, 
 * the node is marked as deleted instead of physically being removed from the tree. Insert, find, findMin(), findMax()
 * operations are adjusted accordingly. Also provides 'hard' versions of the above methods which consider both deleted
 * and undeleted nodes for debugging purposes. While this makes some operations like remove simpler, it slows down some 
 * operations like find(), findMin(), findMax() etc.
 * 
 * @author anuva
 *
 * @param <E>
 */
public class LazySearchTree<E extends Comparable< ? super E > > implements Cloneable
{
	protected int mSize;
	protected int mSizeHard;
	protected LazySTNode mRoot;

	/**
	 * Constructor. Creates an empty bst which uses lazy deletion 
	 */
	public LazySearchTree() 
	{ 
		clear(); 
	}

	/**
	 * Tests if this tree is empty.
	 * @return true if the tree is empty
	 */
	public boolean empty() 
	{ 
		return (mSize == 0); 
	}

	/**
	 * Returns the number of undeleted nodes in the tree
	 * @return number of undeleted nodes in the tree
	 */
	public int size() 
	{ 
		return mSize; 
	}
	
	/**
	 * Returns the number of hard nodes in the tree, i.e., both deleted and undeleted
	 * @return number of nodes in the tree including deleted and undeleted nodes
	 */
	public int sizeHard() {
		
		return mSizeHard;
	}

	/**
	 * Reset the tree
	 */
	public void clear() 
	{ 
		mSize = 0; 
	    mSizeHard = 0;
		mRoot = null; 
	}

	/**
	 * Return the height of the tree
	 * @return
	 */
	public int showHeight() 
	{ 
		return findHeight(mRoot, -1); 
	}

	/**
	 * Starts searching from the root and returns the minimum node that has NOT been deleted. 
	 * findMin() throws an exception in case of an empty tree. 
	 * @return minimum node of the tree that has not been deleted
	 */
	public E findMin() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		
		LazySTNode resultNode = findMin(mRoot);
		
		if (resultNode == null)
			throw new NoSuchElementException();
		
		return resultNode.data;
	}
	
	/**
	 * Starts searching from the root and returns the minimum of all nodes in the tree. 
	 * @return minimum node of the tree 
	 */
	public E findMinHard() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMinHard(mRoot).data;
	}

	/**
	 * Starts searching from the root and returns the maximum node that has NOT been deleted. 
	 * @return maximum node of the tree that has not been deleted
	 */
	public E findMax() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		
		LazySTNode resultNode = findMax(mRoot);
		
		if (resultNode == null)
			throw new NoSuchElementException();
		
		return resultNode.data;
	}
	
	/**
	 * Starts searching from the root and returns the maximum of all nodes in the tree. 
	 * @return maximum node of the tree 
	 */
	public E findMaxHard() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMaxHard(mRoot).data;
	}

	/**
	 * Finds the specified item in nodes that are marked as "undeleted". Ignores deleted nodes. 
	 * @param x item to find in the tree
	 * @return item 
	 */
	public E find( E x )
	{
		LazySTNode resultNode;
		resultNode = find(mRoot, x);
		if (resultNode == null)
			throw new NoSuchElementException();
		return resultNode.data;
	}
	
	/**
	 * Finds the specified item in nodes that are marked as deleted and undeleted. Considers all nodes in the tree.
	 * @param x item to find in the tree
	 * @return item 
	 */
	public E findHard( E x )
	{
		LazySTNode resultNode;
		resultNode = findHard(mRoot, x);
		if (resultNode == null)
			throw new NoSuchElementException();
		return resultNode.data;
	}
	
	/**
	 * Returns true if the tree contains the item
	 * @param x item to find in the tree
	 * @return true if the tree contains the item
	 */
	public boolean contains(E x)  
	{ return find(mRoot, x) != null; }
	
	/**
	 * Returns true if the tree contains the item in deleted or undeleted nodes
	 * @param x item to find in the tree
	 * @return true if the tree contains the item
	 */
	public boolean containsHard(E x)  
	{ return findHard(mRoot, x) != null; }

	/**
	 * Insert item into the tree. Uses binary search tree with lazy deletion. Updates the count of mSize (undeleted nodes) and mSizeHard (deleted and undeleted nodes)
	 * @param x item to insert in the tree
	 * @return true if the item was successfully inserted (checks if the private insert method modified mSize)
	 */
	public boolean insert( E x )
	{
		int oldSize = mSize;
		mRoot = insert(mRoot, x);
		return (mSize != oldSize);
	}

	/**
	 * Remove item from tree. Uses binary search tree with lazy deletion. Marks the node to be deleted as true instead of actually physically deleting the node and adjusting tree
	 * @param x item to remove from tree
	 * @return true if the item was successfully removed (checks if the private insert method modified mSize)
	 */
	public boolean remove( E x )
	{
		int oldSize = mSize;
		remove(mRoot, x);
		return (mSize != oldSize);
	}
	
	/**
	 * Remove item from tree. Physically deletes the node and adjusts tree.
	 * @param x item to remove from tree
	 * @return true if the item was successfully removed
	 */
	public boolean removeHard( E x )
	{
		int oldSize = mSize;
		mRoot = removeHard(mRoot, x);
		return (mSize != oldSize);
	}

	/**
	 * Garbage collection mechanism that provides ability to prune all the deleted nodes from the tree.
	 */
	public void collectGarbage() {
		collectGarbage(mRoot, 0);
	}

	/**
	 * Garbage collection mechanism. Traverse the tree, looking for nodes that have deleted == true. 
	 * When found, apply the real ("hard") remove().
	 * @param treeNode
	 * @param level
	 */
	protected void collectGarbage(LazySTNode treeNode, int level) {

		if (mSize == 0 || treeNode == null)
			return;

		if (treeNode.deleted) {
			removeHard(treeNode.data);
		}

		// recursive step done here
		collectGarbage(treeNode.lftChild, level + 1);
		collectGarbage(treeNode.rtChild, level + 1);

	}
	
	/**
	 * Traverse undeleted nodes of the tree. Accepts a generic argument for the functor to use while traversing the current instance of LazySearchTree. 
	 * @param func functor(function object) to use to traverse the tree
	 */
	public < F extends Traverser<? super E > > void traverseSoft(F func)
	{
		traverseSoft(func, mRoot);
	}
	
	/**
	 * Traverse deleted and undeleted nodes of the tree. Accepts a generic argument for the functor to use while traversing the current instance of LazySearchTree. 
	 * @param func functor(function object) to use to traverse the tree
	 */
	public < F extends Traverser<? super E > > void traverseHard(F func)
	{
		traverseHard(func, mRoot);
	}
	
	
	/**
	 * Clone the tree. Creates a deep copy of the current tree. Since we are using lazy deletion, we need to prune lazily 
	 * deleted nodes from the cloned tree.
	 */
	public Object clone() throws CloneNotSupportedException
	{
		// LazySearchTree implements Cloneable and provides an official conforming clone method.
		LazySearchTree<E> newObject = (LazySearchTree<E>)super.clone();
		newObject.clear();  // can't point to other's data

		newObject.mRoot = cloneSubtree(mRoot);
		newObject.mSize = mSize;

		newObject.collectGarbage();
		
		return newObject;
	}
	
	// private helper methods ----------------------------------------
	/*private LazySTNode findMin( LazySTNode root ) 
	{
		if (root == null)
			return null;
		
		if ((root.lftChild == null || root.lftChild.deleted ) && !root.deleted)
			return root;
		else if((root.lftChild == null || root.lftChild.deleted ) && root.deleted)
			return findMin(root.rtChild);
		return findMin(root.lftChild);
	}*/
	
	/**
	 * Find minimum of the undeleted nodes of the tree. 
	 * To find the minimum, follow the left children all the way down the tree until we hit a null.
	 * If a node has been found but is marked deleted, then check if the root is not deleted since that is the 
	 * next smallest. If the root is also marked deleted, start looking in the right subtree for the next minimum
	 * @param root LazySTNodenode of the subtree of interest 
	 * @return the minimum node that has NOT been deleted.
	 */
	protected LazySTNode findMin(LazySTNode root) {
		if (root == null)
			return null;

		LazySTNode candidate = findMin(root.lftChild);

		if (candidate != null)
			return candidate;

		if (!root.deleted)
			return root;

		return findMin(root.rtChild);
	}
	
	/**
	 * Find minimum of the tree. Considers both deleted and undeleted nodes of the tree.
	 * @param root LazySTNode node of the subtree of interest 
	 * @return the minimum node of the tree.
	 */
	
	protected LazySTNode findMinHard( LazySTNode root ) 
	{
		if (root == null)
			return null;
		if (root.lftChild == null)
			return root;
		return findMinHard(root.lftChild);
	}

	/**
	 * Find maximum of the undeleted nodes of the tree.
	 * To find the maximum, follow the right children all the way down the tree until we hit a null.
	 * If a node has been found but is marked deleted, then check if the root is not deleted since that is the 
	 * next largest. If the root is also marked deleted, start looking in the left subtree for the next largest
	 * @param root LazySTNode node of the subtree of interest
	 * @return the maximum node that has NOT been deleted.
	 */
	protected LazySTNode findMax( LazySTNode root ) 
	{
		if (root == null)
			return null;
		
		LazySTNode candidate = findMax(root.rtChild);
		
		if (candidate != null)
			return candidate;

		if (!root.deleted)
			return root;

		return findMax(root.lftChild);
	}
	
	/**
	 * Find maximum of the nodes of the tree
	 * @param root LazySTNode node of the subtree of interest  
	 * @return the maximum node of the tree.
	 */
	protected LazySTNode findMaxHard( LazySTNode root ) 
	{
		if (root == null)
			return null;
		if (root.rtChild == null)
			return root;
		return findMaxHard(root.rtChild);
	}
	
	/**
	 * Add specified item to the tree. Updates the count of mSize (undeleted nodes) and mSizeHard (deleted and undeleted nodes)
	 * If an attempt is made to insert() a node that is already there, but marked deleted, deleted member is changed back to false
	 * @param root LazySTNode root node of the subtree of interest 
	 * @param x item to add to the tree
	 * @return LazySTNode node 
	 */
	
	protected LazySTNode insert(LazySTNode root, E x) {
		int compareResult; // avoid multiple calls to compareTo()

		if (root == null) {
			mSize++;
			mSizeHard++;
			return new LazySTNode(x, null, null, false);
		}

		compareResult = x.compareTo(root.data);
		if (compareResult < 0)
			root.lftChild = insert(root.lftChild, x);
		else if (compareResult > 0)
			root.rtChild = insert(root.rtChild, x);
		else if (root.deleted) {
			root.deleted = false;
			mSize++;
		}

		return root;
	}
	
	/**
	 * Remove item from tree. Uses binary search tree with lazy deletion. Marks the node to be deleted as true instead of actually physically deleting the node and adjusting tree
	 * Updates count of undeleted nodes
	 * @param root LazySTNode node
	 * @param x item to remove
	 * @return LazySTNode node
	 */
	protected LazySTNode remove( LazySTNode root, E x ) {
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if ( compareResult < 0 )
			root.lftChild = remove(root.lftChild, x);
		else if ( compareResult > 0 )
			root.rtChild = remove(root.rtChild, x);

		// found the node
		else
		{
			root.deleted = true;
			mSize--;
		}
		return root;
	}
	
	
	/**
	 * Remove item from tree. Uses binary search tree with lazy deletion. Physically deletes the node and adjusts tree
	 * Adjusts number of deleted and undeleted nodes
	 * @param root LazySTNode node
	 * @param x item to remove
	 * @return LazySTNode node
	 */
	protected LazySTNode removeHard( LazySTNode root, E x  )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if ( compareResult < 0 )
			root.lftChild = removeHard(root.lftChild, x);
		else if ( compareResult > 0 )
			root.rtChild = removeHard(root.rtChild, x);

		// found the node
		else if (root.lftChild != null && root.rtChild != null)
		{
			root.data = findMin(root.rtChild).data;
			root.rtChild = removeHard(root.rtChild, root.data);
		}
		else
		{
			root =
					(root.lftChild != null)? root.lftChild : root.rtChild;
			mSize--;
			mSizeHard--;
		}
		return root;
	}

	/**
	 * Traverse the tree including both deleted and undeleted nodes. Accepts a generic argument for the functor to use while traversing the current instance of LazySearchTree. 
	 * @param func function object to use while traversing the tree.
	 * @param treeNode LazySTNode node
	 */
	protected <F extends Traverser<? super E>> void traverseHard(F func, LazySTNode treeNode)
	{
		if (treeNode == null)
			return;

		traverseHard(func, treeNode.lftChild);
		func.visit(treeNode.data);
		traverseHard(func, treeNode.rtChild);
	}
	
	/**
	 * Traverse only undeleted nodes of the tree. Accepts a generic argument for the functor to use while traversing the current instance of LazySearchTree. 
	 * @param func function object to use while traversing the tree.
	 * @param treeNode LazySTNode node
	 */
	protected <F extends Traverser<? super E>> void traverseSoft(F func, LazySTNode treeNode)
	{
		if (treeNode == null)
			return;

		traverseSoft(func, treeNode.lftChild);
		if(!treeNode.deleted)
			func.visit(treeNode.data);
		traverseSoft(func, treeNode.rtChild);
	}

	/**
	 * Finds the specified item in nodes that are marked as "undeleted". Ignores deleted nodes. 
	 * Every recursive call will change the parameter root to move it further down the tree toward its leafs, so that we are searching in an ever shrinking tree
	 * @param root LazySTNode node representing root of the subtree in which to look for x
	 * @param x item to search
	 * @return LazySTNode node
	 */
	protected LazySTNode find( LazySTNode root, E x )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if (compareResult < 0)
			return find(root.lftChild, x);
		if (compareResult > 0)
			return find(root.rtChild, x);
		if(!root.deleted)
			return root;   
		return null;
	}
	
	/**
	 * Finds the specified item in nodes that are marked as "deleted" and "undeleted".
	 * @param root LazySTNode node
	 * @param x item to search
	 * @return LazySTNode node
	 */
	protected LazySTNode findHard( LazySTNode root, E x )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if (compareResult < 0)
			return findHard(root.lftChild, x);
		if (compareResult > 0)
			return findHard(root.rtChild, x);
		
		return root;  
	}

	/**
	 * Clone tree. Creates deep copy by recursively cloning right and left subtrees.
	 * @param root LazySTNode node representing the subtree of interest
	 * @return LazySTNode node representing cloned subtree
	 */
	protected LazySTNode cloneSubtree(LazySTNode root)
	{
		LazySTNode newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller
		newNode = new LazySTNode
		(
				root.data, 
				cloneSubtree(root.lftChild), 
				cloneSubtree(root.rtChild), 
				root.deleted
				);
		return newNode;
	}
	
	/**
	 * Find height of tree
	 * @param treeNode LazySTNode node
	 * @param height level of the node 
	 * @return height of the tree
	 */
	protected int findHeight( LazySTNode treeNode, int height ) 
	{
		int leftHeight, rightHeight;
		if (treeNode == null)
			return height;
		height++;
		leftHeight = findHeight(treeNode.lftChild, height);
		rightHeight = findHeight(treeNode.rtChild, height);
		return (leftHeight > rightHeight)? leftHeight : rightHeight;
	}
	
	/**
	 * Represents each node of the binary search tree which implements lazy deletion. Each node has the data, a pointer to a left 
	 * child, a pointer to the right child and a boolean attribute denoting whether the node has been marked as deleted.
	 * @author anuva
	 *
	 */
	private class LazySTNode
	{
	   // use public access so the tree or other classes can access members 
	   public LazySTNode lftChild, rtChild;
	   public E data;
	   public LazySTNode myRoot;  // needed to test for certain error
	   public boolean deleted;

	   /**
	    * Constructor. Creates a new node of the tree.
	    * @param d
	    * @param lft
	    * @param rt
	    * @param dltd
	    */
	   public LazySTNode( E d, LazySTNode lft, LazySTNode rt, boolean dltd )
	   {
	      lftChild = lft; 
	      rtChild = rt;
	      data = d;
	      deleted = dltd;
	   }
	   
	   public LazySTNode()
	   {
	      this(null, null, null, false);
	   }
	}
}
