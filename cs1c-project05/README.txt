project folder:
anuvabanwasi-cs1c-project05/

Brief description of submitted files:
	
src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.
  
src/cs1c/SongEntry.java 
	- One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.
	
src/cs1c/TimeConverter.java 
	- Converts duration into a string representation.
	
src/cs1c/TimeConverter.java 
	- General interface to traverse the tree to compute something
	
src/lazyTrees/Item.java
	- One object of Item class represents one item in the inventory, with two class members - name and count
	- Stores the name of the inventory item and the associated count of the item
	
src/lazyTrees/LazySearchTree.java
	- Binary Search Tree which implements lazy deletion.
	- Each node includes a boolean member called deleted. This member is set to true if the node has been remove()-d from the tree.
	- During a remove operation in a BST with lazy deletion, the node is marked as deleted instead of being physically removed from the tree
	- Provides 'hard' and 'soft' versions of find(), findMin(), findMax(), traverse(), remove(), clone() etc

src/lazyTrees/LazySearchTreeClient.java	
	- Sample test program to test clone(), findMin(), findMinHard(), findMax(), findMaxHard() etc

src/lazyTrees/PrintObject.java
	- Function objects that is useful for passing around single methods. 
	- Has a visit() method which prints the contents of the node
	
src/lazyTrees/SuperMarket.java
	- Simulates the market scenario of buying and adding items to a store's inventory.
	- Represents store's inventory as a binary search tree using lazy deletion
	- Has methods to add and remove from inventory
	- Contains main() method
	
src/lazyTrees/FoothillTunesStore.java
	- Simulates the scenario of buying and adding songs to a store's inventory. 
	- Reads an input file that contains all possible songs. Parses a JSON data set with a given file path. The parsed data set is stored in an array of SongEntry objects.
	- Then reads an inventory file containing titles of songs to add to the iTunes store inventory.
	- If the selection is an "add", then looks up the title of the song in the master array of SongEntry objects and adds the appropriate SongEntry to the iTunes store inventory. If the song entry already exists in the inventory, increments the count of the song entry.
	- If the selection is an "buy", then looks up the title of the song in the master array of SongEntry objects and decrements the count of the appropriate SongEntry to the iTunes store inventory
	- Contains main() method
	
resources/music_genre_subset.json
    - A JSON (JavaScript Object Notation) file.
    - JSON file contains genre, artist_name, title, duration, song_id for each song
    
resources/music_genre_subset2.json
    - A smaller JSON (JavaScript Object Notation) file.
    - JSON file contains genre, artist_name, title, duration, song_id for each song
  
resources/inventory_all_delete.txt
    - A smaller input file to test deletion of all inventory items.
    
resources/inventory_invalid_removal.txt
    - A smaller input file to test deletion of non existing inventory item.
    
resources/inventory_log.txt
	- An input file to test insertion and deletion of inventory items.
	
resources/inventory_no_delete.txt
	- An input file to test only insertion of inventory items.
	
resources/inventory_short.txt
	- A smaller input file to test insertion and deletion of inventory items.

resources/inventory_test_deletion_all.txt
	- An input file to test deletion of nodes with 2 children, 1 child and no children
	
resources/songs_short.txt
	- A smaller input file to test insertion and deletion of song entry objects.

resources/songs_all_delete.txt
	- An even smaller input file to test insertion and deletion of song entry objects.

resources/RUN.txt
    - console output of SuperMarket.java and FoothillTunesStore.java
    - JSON file contains genre, artist_name, title, duration, song_id for each song