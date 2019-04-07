package lazyTrees;


import java.util.NoSuchElementException;

/**
 * Sample test program to test clone(), findMin(), findMinHard(), findMax(), findMaxHard() etc
 * @author anuva
 *
 */
public class LazySearchTreeClient {

	public static void main(String[] args) throws CloneNotSupportedException {

		LazySearchTree<Integer> lst = new LazySearchTree<Integer>();
		PrintObject<Integer> printer = new PrintObject<Integer>();

	    lst.traverseHard(printer);
		System.out.println("initial size: " + lst.size());
		lst.insert(10);
		lst.insert(5);
		lst.insert(15);
		lst.insert(3);
		lst.insert(6);
		lst.insert(4);
		System.out.println("new size: " + lst.size());
		
		
		System.out.println("\n\nTesting findMin() when all nodes are deleted");
		LazySearchTree<Integer> lst2 = new LazySearchTree<Integer>();
		PrintObject<Integer> printer1 = new PrintObject<Integer>();

	    lst2.traverseHard(printer1);
		System.out.println("lst 2 initial size: " + lst2.size());
		lst2.insert(10);
		lst2.insert(5);
		lst2.insert(15);
		lst2.insert(3);
		lst2.insert(6);
		lst2.insert(12);
		lst2.remove(10);
		lst2.remove(5);
		lst2.remove(15);
		lst2.remove(3);
		lst2.remove(6);
		lst2.remove(12);
		System.out.println("new size: " + lst2.size());
		try {
			lst2.findMin();
		} catch(NoSuchElementException e) {
			System.out.println("\nWarning: Unable to fulfill request to find minimum: \n");
		}
		
		
		lst.remove(3);
		System.out.println("\n\nTraverse Hard orig list: ");
		lst.traverseHard(printer);
		System.out.println();
		System.out.println("\n\nTraverse soft orig list: ");
		lst.traverseSoft(printer);
		System.out.println();

		System.out.println("\n\nTesting cloning of tree");
		LazySearchTree<Integer> lst1 = (LazySearchTree<Integer>) lst.clone();
		lst1.insert(20);
		
		
		System.out.println("Hard traversal of cloned list: ");
		lst1.traverseHard(printer);
		System.out.println();
		System.out.println();
		
		
		System.out.println("Hard traversal of original list: ");
		lst.traverseHard(printer);
		System.out.println();

		System.out.println("find() " + lst.find(10));
		System.out.println("findHard() " + lst.findHard(10));
		System.out.println("findMin() " + lst.findMin());
		System.out.println("findMax() " + lst.findMax());
		System.out.println("findMinHard() " + lst.findMinHard());
		System.out.println("findMaxHard() " + lst.findMaxHard());

		
		System.out.println("traversal: ");
	    lst.traverseHard(printer);
	    
	}
}
