project folder:
anuvabanwasi-cs1c-project06/

Brief description of submitted files:
		
src/cs1c/TimeConverter.java 
	- Converts duration into a string representation.
	
src/cs1c/Traverser.java 
	- General interface to traverse the tree to compute something
	
src/lazyTrees/Item.java
	- One object of Item class represents one item in the inventory, with two class members - name and count
	- Stores the name of the inventory item and the associated count of the item
	
src/lazyTrees/LazySearchTree.java
	- Binary Search Tree which implements lazy deletion.
	- Each node includes a boolean member called deleted. This member is set to true if the node has been remove()-d from the tree.
	- During a remove operation in a BST with lazy deletion, the node is marked as deleted instead of being physically removed from the tree
	- Provides method for garbage collection which will hard remove lazily deleted nodes
	- Provides 'hard' and 'soft' versions of find(), findMin(), findMax(), traverse(), remove(), clone() etc

src/lazyTrees/PrintObject.java
	- Function objects that is useful for passing around single methods. 
	- Has a visit() method which prints the contents of the node
	
src/lazyTrees/SuperMarket.java
	- Simulates the market scenario of buying and adding items to a store's inventory.
	- Represents store's inventory as a binary search tree using lazy deletion
	- Has methods to add and remove from inventory
	- Contains main() method
		 
resources/inventory_all_delete.txt
    - A smaller input file to test deletion of all inventory items.
    
resources/inventory_invalid_removal.txt
    - A smaller input file to test deletion of non existing inventory item.
    
resources/inventory_log.txt
	- An input file to test insertion and deletion of inventory items.
	
resources/inventory_right_subtree_all_delete.txt
	- An input file to test garbage collection of all inventory items in right subtree.
	
resources/inventory_threshold.txt
	- An input file to test garbage collection with varying thresholds
		
resources/inventory_short.txt
	- A smaller input file to test insertion and deletion of inventory items.

resources/RUN.txt
    - console output of SuperMarket.java
