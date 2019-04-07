package queues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import cs1c.SongEntry;

/**
 * Manages 3 objects of type Queue of SongEntry - favoritePL, roadTripPL and loungePL
 * favoritePL simulates playlist referred as "favorites" in input file, 
 * roadTripPL simulates playlist referred to as "road trip" in input file,
 * loungePL simulates playlist referred to as "lounge" in input file
 * An instance of the class reads a file which includes the user's requests for a the name of a song to be added to a specific playlist.
 * It will then add songs to the three playlists "favorites", "lounge", and "road trip" accordingly.
 * Uses binary search to efficiently look for songs with the specified title
 * @author anuva
 *
 */
public class Jukebox {
	private Queue<SongEntry> favoritePL;						// simulates playlist referred as "favorites" in input file
	private Queue<SongEntry> roadTripPL;						// simulates playlist referred as "road trip" in input file
	private Queue<SongEntry> loungePL;							// simulates playlist referred as "lounge" in input file
	
	/**
	 * Constructor. Initializes favoritePL, roadTripPL and loungePL
	 */
	Jukebox(){
		favoritePL = new Queue<SongEntry>("favorites");
		roadTripPL = new Queue<SongEntry>("road trip");
		loungePL = new Queue<SongEntry>("lounge");
	}
	
	/**
	 * Reads the test file and then adds songs to one of the three queues. For example, if the file contains line: favorites,title 
	 * then this method adds the first song with the given title found in allSongs to the favorites playlist
	 * Sorts all known songs on song title and uses binary search to efficiently look for songs with the specified title. Growth rate O(log n)
	 * @param requestedFile 	String representing path of the input file
	 * @param allSongs			Array of SongEntry objects representing all known songs
	 */
	void fillPlaylists(String requestedFile, SongEntry[] allSongs) {
		BufferedReader br = null;
		
		SongEntry.setSortType(SongEntry.SORT_BY_TITLE);

		//Sort songs since we will be using binary search to look for songs with specified title
		Arrays.sort(allSongs);
		
		try {
			// wraps a buffered reader around file for buffering characters for more efficient reading
			br = new BufferedReader(new FileReader(new File(requestedFile)));
			for (int k = 0; br.ready(); k++) {
				String line = br.readLine();
				if(line == null) {
					close(br);
					throw new Exception("Please check input file contents");
				} else {
					String[] tokens = line.split("," , 2);
					if(tokens.length != 2) {
						close(br);
						throw new Exception("Please check input file contents");
					}
					String playList = tokens[0].trim();
					String songTitle = tokens[1].trim();
						
					// use binary search to find first song with the given title
					int index = binarySearch(allSongs, songTitle, 0, allSongs.length - 1);

					// if song was found, add song to the specified playlist 
					if(index != -1) {
						Queue<SongEntry> queue = getPlayList(playList);
						if(queue != null)
							queue.enqueue(allSongs[index]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			printError(e,"\nProblem opening the file! Please check file path is correct and that file exists at that location. Exiting program.");
		} catch (IOException ioe) {
			printError(ioe, "\nIOException! Exiting program.");
		} catch (Exception e) {
			printError(e, "Exception in parsing input file. Exiting program.");
		} finally {
			close(br);
		}
	}

	/**
	 * Retrieve playlist of specified name
	 * @param name			Name of the play list
	 * @return				Queue of SongEntry representing the playlist
	 */
	private Queue<SongEntry> getPlayList(String name) 
	{
		switch(name)
		{
		case "favorites": return favoritePL;
		case "lounge": return loungePL;
		case "road trip": return roadTripPL;
		}
		return null;
	}
	
	/**
	 * Close buffered reader
	 * @param br BufferedReader
	 */
	private void close(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			printError(e, "\nIO Exception! Exiting program");
		}
	}

	/**
	 * Binary search algorithm to efficiently search for a title in the sorted array of songs. Running time is logarithmic - O (Log N)
	 * @param songs				Sorted array of songs
	 * @param title				String representing title to search for
	 * @param firstIndex		integer representing smallest index of the array
	 * @param lastIndex			integer representing largest index of the array
	 * @return the index of the element in the list that matches the search key if it is contained in the list. Otherwise, it returns -1 (negative)
	 */
	private int binarySearch(SongEntry[] songs, String title, int firstIndex, int lastIndex) {

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
	 * Print stack trace and exit
	 * @param e Exception 
	 * @param msg String representing error message
	 */
	private void printError(Exception e, String msg) {
		System.out.println(msg);
		e.printStackTrace();			
		System.exit(1);
	}
	
	/**
	 * Accessor for favorites play list
	 * @return favoritePL
	 */
	public Queue<SongEntry> getFavoritePL() {
		return favoritePL;
	}
	
	/**
	 * Accessor for road trip play list
	 * @return roadTripPL
	 */
	public Queue<SongEntry> getRoadTripPL() {
		return roadTripPL;
	}

	/**
	 * Accessor for lounge play list
	 * @return loungePL
	 */
	public Queue<SongEntry> getLoungePL() {
		return loungePL;
	}
}
