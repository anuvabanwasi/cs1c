package lazyTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * Simulates the scenario of buying and adding songs to a store's inventory.
 * FoothillTunesStore which will store a binary search tree. The implementation of BST will use lazy deletion
 * to store SongEntry objects.
 * @author Foothill College, Bita M, Anuva B
 */
public class FoothillTunesStore 
{	
	public static final boolean SHOW_DETAILS = true;

	// Define the functor class PrintObject to traverse and print out data 
	//       from LazySearchTree.
	PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();

	// The data structure, which we use to add and remove songs.
	private LazySearchTree<SongEntry> tunes;
	

	/**
	 * Instantiates tunes to be a LazySearchTree of SongEntry objects.
	 */
	public FoothillTunesStore()
	{
		tunes = new LazySearchTree<SongEntry>();
	}

	/**
	 * Add a new SongEntry with the title as a parameter into a LazySearchTree of SongEntry objects. If there is 
	 * a song already with same title, increase amount by one, if not create a new title.
	 * @param title		The title of the song to be added to the LazySearchTree of SongEntry objects.
	 */
	public void addToTunes(String title, int duration, String artist_name, String genre)
	{
		// Create a temporary object to hold the song.
		SongEntry tmp = new SongEntry(title, duration, artist_name, genre);

		// Check if the song is in the tunes tree.
		boolean isFound = tunes.contains(tmp);

		// If the song is not found, add the temporary object as another node (category) to the tree.
		if (!isFound)
		{
			// Modify insert method to work with lazy deletion such that it updates
			//       both hard and soft sizes. 
			tunes.insert(tmp);

			// NOTE: Need to check if the song was lazily deleted, then we need to increment the count
			SongEntry found = tunes.find(tmp);
			if (found.getCount() == 0)
			{
				found.incrementCount();
			}
			return;
		}

		// If the item is found, increase the number of songs in that title.
		SongEntry found = tunes.find(tmp);

		// item was previously in tree, so increment the count
		found.incrementCount();
	}

	/**
	 * If the song is in the tunes tree, decrease the count by one. 
	 * If only one song is left, remove it from the tunes tree. 
	 * @param title		The title to be removed from the tunes tree.
	 */
	public void removeFromTunes(String title, int duration, String artist_name, String genre)
	{
		SongEntry tmp = new SongEntry(title, duration, artist_name, genre);
		boolean isFound = tunes.contains(tmp);

		// check if the songs exists in the tunes tree disregarding lazy deletion
		if (!isFound)
		{
			throw new NoSuchElementException();
		}

		SongEntry found = tunes.find(tmp);

		// if the song has zero left in stock, 
		// then treat it as if it does not exist in the tree.
		if (found.getCount() == 0)
		{
			throw new NoSuchElementException();
		}
		// if the song has one left in stock, 
		// then decrement its count and lazy delete it in the tree. 
		else if (found.getCount() == 1)
		{
			found.decrementCount();

			// Modify to be a lazy deletion remove method, which marks 
			//       found nodes as "deleted".
			tunes.remove(tmp);	
		}
		// otherwise, simply decrement its count.
		else
		{
			found.decrementCount();
		}
	}


	/** 
	 * Display the first item and last time of the soft tree in lexical order.
	 */
	public void showFirstAndLastTitle(String message)
	{
		System.out.println("\n" + message);

		// Modify the protected methods findMin() and findMax() to implement lazy deletion. 
		//       Searches from the root of the tree and returns the minimum and maximum node that 
		//       has NOT been "deleted". 
		try
		{
			SongEntry min = tunes.findMin();
			System.out.println ( "First title: " + min.toString());
		} 
		catch (Exception NoSuchElementException)
		{
			System.out.println("Warning: minimum element not found!");
		}

		try
		{
			SongEntry max = tunes.findMax();
			System.out.println ( "Last title: " + max.toString());
		} 
		catch (Exception NoSuchElementException)
		{
			System.out.println("Warning: maximum element not found!");
		}

	}

	/**
	 * Shows the state of the tree by displaying:
	 * - the *hard* inventory, which includes deleted nodes.
	 * - the *soft* inventory, which excludes deleted nodes.
	 * @param message	Additional details about the state.
	 * @param showTree	Set to true if we want to display the contents of the tree
	 */
	protected void displayTunesStore(String message, boolean showTree)
	{
		System.out.println("\n" + message);
		System.out.println("\"hard\" number of unique songs (i.e. mSizeHard) = " + tunes.sizeHard());
		System.out.println("\"soft\" number of unique songs (i.e. mSize) = " + tunes.size());

		if (!showTree)
			return;

		System.out.println( "\nTesting traversing \"hard\" song list:");

		// First, rename the public/private pair traverse() method of FHsearch_tree to traverseHard() method.
		//       Then, reuse this public/private pair of methods to traverses the tree
		//       and displays all the nodes.
		// NOTE: Here, we call the public version.
		tunes.traverseHard(printObject);


		System.out.println( "\n\nTesting traversing \"soft\" song list:");

		// Define a public/private pair of methods that traverses the tree
		//       and displays only nodes that have not been lazily deleted. 
		// NOTE: Here, we call the public version.
		tunes.traverseSoft(printObject);
		System.out.println("\n");
	}

	/**
	 * Binary search algorithm to efficiently search for a title in the sorted array of songs. Running time is logarithmic - O (Log N)
	 * @param songs				Sorted array of songs
	 * @param title				String representing title to search for
	 * @param firstIndex		integer representing smallest index of the array
	 * @param lastIndex			integer representing largest index of the array
	 * @return the index of the element in the list that matches the search key if it is contained in the list. Otherwise, it returns -1 (negative)
	 */
	private static int binarySearch(SongEntry[] songs, String title, int firstIndex, int lastIndex) {

		int middleIndex, result;
		
		// If element not found return -1
		if (firstIndex > lastIndex)
			return -1;
		
		// determine middle of the array
		middleIndex = (firstIndex + lastIndex ) / 2;  
		
		// compare the key with the element in the middle of the array
		result = title.compareTo(songs[middleIndex].getTitle());
		
		if (result == 0)
			return middleIndex; 												// If the key is equal to the middle element, the search ends with a match.
		else if (result < 0)													//If the key is less than the middle element, only need to search the key in the first half of the array.
			return binarySearch(songs, title, firstIndex, middleIndex - 1);
		else
			return binarySearch(songs, title, middleIndex + 1, lastIndex);		//If the key is greater than the middle element, only need to search the key in the second half of the array.	
	}
	
	/**
	 * Reads an input file that contains the prices of the different items.
	 * Then stores and outputs a list of items we can buy
	 * given the condition of how much money you have in your wallet.
	 * We're at a cash only store. So, no checks or credit purchases!
	 * @param args	Not used.
	 */
	public static void main(String[] args) 
	{
		// NOTE: Make sure to use *relative* path instead of specifying the entire path. 
		//       Otherwise, your program will result in run time errors when the instructor
		//       tests your implementation.
		final String FILEPATH = "resources/music_genre_subset.json";

		// parses the JSON input file
		MillionSongDataSubset msd = new MillionSongDataSubset(FILEPATH);
		
		// retrieves the parsed objects
		SongEntry [] allSongs = msd.getArrayOfSongs();

		ArrayList<SongEntry> songList = new ArrayList<SongEntry>(Arrays.asList(allSongs));
		// displays the number of songs read from the input file
		System.out.printf("Welcome! We have over %d in FoothillTunes store! \n", songList.size());
		
		
		SongEntry.setSortType(SongEntry.SORT_BY_TITLE);
		
		// for measuring run time
		long startTime, estimatedTime;
		
		// capture the start time
		startTime = System.nanoTime();    

		//Sort songs since we will be using binary search to look for songs with specified title
		Arrays.sort(allSongs);
				
		// Tests the LazySearchTree by adding and removing items from the inventory
		//final String TESTFILE = "resources/inventory_log.txt";	// Directory path for plain-text file

		// NOTE: Short inventory file to test for removal of root node from LazySearchTree
		final String TESTFILE = "resources/songs_short.txt";	

		// NOTE: Short inventory file to test for removal of root node from LazySearchTree
		//final String TESTFILE = "resources/songs_all_delete.txt";	

		// NOTE: An example of testing the boundary condition when removing an item that may not exist
		//final String TESTFILE = "resources/inventory_invalid_removal.txt";	

		System.out.printf("Test file: %s \n", TESTFILE);

		FoothillTunesStore store = new FoothillTunesStore();

		File infile = new File(TESTFILE);

		try 
		{
			Scanner input = new Scanner(infile);

			String line = "";
			int lineNum = 0;
			while (input.hasNextLine()) 
			{
				lineNum++;
				line = input.nextLine(); 
				String [] tokens = line.split(" ", 2);

				String selection = tokens[0];
				String title = tokens[1];

				String message = "at line #" + lineNum + ": " + line;

				// When a song is added:
				// If the song is not in our tunes inventory, 
				// create a new entry in our tunes tree.
				// Otherwise, increment the count of the song.
				if (selection.equals("add"))
				{
					// use binary search to find first song with the given title
					int index = binarySearch(allSongs, title, 0, allSongs.length - 1);
					
					if(index != -1) {
						SongEntry lookup = allSongs[index];
						store.addToTunes(title, lookup.getDuration(), lookup.getArtistName(), lookup.getGenre());
					} 

					// NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
					// Suggestion: To start, enable displaying the contents of the tree to help you debug.
					if (SHOW_DETAILS)
						store.displayTunesStore("\nUpdate " + message, true);
				}

				// When an song is requested: 
				// Decrement the count of the song.
				// If the song is out of stock, 
				// remove the item from tunes.
				//
				// Note: buying an out of stock song, is invalid. Handle it appropriately.
				else if (selection.equals("buy"))
				{
					try
					{
						// use binary search to find first song with the given title
						int index = binarySearch(allSongs, title, 0, allSongs.length - 1);
						
						if(index != -1) {
							SongEntry lookup = allSongs[index];
							store.removeFromTunes(title, lookup.getDuration(), lookup.getArtistName(), lookup.getGenre());
						}
						
						// NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
						// Suggestion: To start, enable displaying the contents of the tree to help you debug.
						if (SHOW_DETAILS)
							store.displayTunesStore("\nUpdate " + message, true);						
					}
					catch (java.util.NoSuchElementException ex)
					{
						// NOTE: Ideally we'd print to the error stream,
						//       but to allow correct interleaving of the output
						//       we'll use the regular output stream.
						System.out.printf("\nWarning: Unable to fulfill request: %s \n", message);
						System.out.printf("Warning: Item %s is out of stock.\n", title);
					}
				}
				else
				{
					System.out.println("Warning: Song selection not recognized!");
				}		

				// Display the first song and the last song before checking
				// if it's time to clean up our song list.
				if (SHOW_DETAILS)
					store.showFirstAndLastTitle(message);
			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 

		// Display the song list
		System.out.println("\n");
		store.displayTunesStore("\nFinal state of tunes store:", true);

		// flush the error stream
		System.err.flush();

		// report algorithm time
		estimatedTime = System.nanoTime() - startTime;
		
		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
				+ TimeConverter.convertTimeToString(estimatedTime));
		
		System.out.println("\nDone with FoothillTunesStore.");
	
	}
}
