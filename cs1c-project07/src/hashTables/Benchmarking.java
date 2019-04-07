package hashTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * Tests the functionality of FHhashQPwFind.java.
 * Specifically checks for implementation of find() function to return an object associated with a given key input.
 *
 * @author Foothill College, Bita M , Anuva B
 */
public class Benchmarking
{
	public static final boolean SHOW_DETAILS = false;

	// Two hash tables of type FHhashQPwFind which extends parent class FHhashQP --------

	// Define the wrapper class SongCompInt for SongEntry objects,
	//       which would compare SongEntry objects based on the song's int id field.
	private FHhashQPwFind<Integer, SongCompInt>  tableOfSongIDs;

	// Define the wrapper class SongCompGenre for SongEntry objects,
	//       which would compare SongEntry objects based on the String genre field and
	//       determines the number of songs in each genre.
	private FHhashQPwFind<String, SongsCompGenre> tableOfGenres; 

	// List of genres found while populating tableOfGenres field
	private ArrayList<String> genreNames;
	
	// List of SongEntry to test sequential find
	private ArrayList<SongEntry> songEntryList;


	/**
	 * Populates two hash tables:
	 * - a hash table for comparing songs based on their int field ID.
	 * - a hash table for comparing songs based on their String field genre.
	 * @param allSongs	Array of SongEntry objects
	 */
	public Benchmarking(SongEntry[] allSongs)
	{		
		// Define the TableGenerator class to have two class fields of type
		//       FHhashQPwFind which extend the parent class FHhashQP.
		TableGenerator g = new TableGenerator();

		// Populates a hash table for comparing songs based on their int field ID.
		tableOfSongIDs = g.populateIDtable(allSongs);

		// Populates a hash table for comparing songs based on their String field genre.
		//       Uses this table to also populates list of genre names with unique keys.
		tableOfGenres = g.populateGenreTable(allSongs);		

		// Return the unique genre names found when populating genre table
		genreNames = g.getGenreNames();
		
		// Populates arraylist of songs for sequential find
		populateSongEntryList(allSongs);
	}

	private void populateSongEntryList(SongEntry[] allSongs) {
		songEntryList = new ArrayList<SongEntry>();
		for(SongEntry song : allSongs) {
			songEntryList.add(song);
		}
	}

	/**
	 * Uses MillionSongDataSubset to read all songs from data file.
	 * @param jsonFile		A JSON file to parse
	 * @return				The array of SongEntry objects
	 */
	public static SongEntry [] readSongsFromDataFile(String jsonFile)
	{
		// parses the JSON input file
		MillionSongDataSubset msd = new MillionSongDataSubset(jsonFile);

		// retrieves the parsed objects
		SongEntry [] allSongs = msd.getArrayOfSongs();

		// displays the number of songs read from the input file
		System.out.printf("Total number of songs read %d \n", allSongs.length);

		return allSongs;
	}


	/**
	 * Basic file reader which reads a file with format:
	 * [value to find]
	 * [value to find]
	 * etc.
	 * And stores each value into a list.
	 * @param filename	The input file to read.
	 * @return Read lines as a list.
	 */
	public ArrayList<String> readFindRequests(String filename)
	{
		ArrayList<String> requests = new ArrayList<>();
		Scanner input = null;

		try 
		{
			File infile = new File(filename);
			input = new Scanner(infile);

			String line = "";
			while (input.hasNextLine()) 
			{
				line = input.nextLine(); 

				requests.add("" + line);
			} // while more lines in file
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 	
		finally
		{
			if (input != null)
				input.close();
		}
		return requests;
	}

	/**
	 * Tests the FHhashQPwFind and wrapper class SongCompInt.
	 * @param filename The input file to read.
	 */
	public void testIDtable(String filename) 
	{
		if(SHOW_DETAILS)
			System.out.printf("Test file for id table: %s \n", filename);

		ArrayList<String> idsToFind = readFindRequests(filename);

		long startTime = System.nanoTime();
		
		for (String idStr : idsToFind)
		{
			int id;
			try
			{
				id = Integer.parseInt(idStr);
			}
			catch (NumberFormatException e)
			{
				if(SHOW_DETAILS)
					System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping.\n", idStr);
				continue;
			}

			if(SHOW_DETAILS)
				System.out.printf( "\nFinding song id: %d\n", id);

			try
			{
				// Define the wrapper class for a SongEntry object such that 
				//       it compares objects based on a song's integer id field.
				SongCompInt compResult  = tableOfSongIDs.find(id);
				if (compResult != null)
				{
					if(SHOW_DETAILS)
						System.out.printf("Song id %d found in tableOfSongIDs. \n", id);
				}
			}
			catch (NoSuchElementException e)
			{ 
				if(SHOW_DETAILS)
					System.out.printf("Song id %d NOT found in tableOfSongIDs.\n", id);
			} 
		} // for all requested IDs to find

		long endTime = System.nanoTime();
		
		// stop the timer
		long estimatedTime = endTime - startTime;
				
		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
				+ TimeConverter.convertTimeToString(estimatedTime));
		
		System.out.println("Done with testing table of ids.\n");
	}
	
	/**
	 * Tests the FHhashQPwFind and wrapper class SongCompInt.
	 * @param filename The input file to read.
	 */
	public void testArrayList(String filename) 
	{
		if(SHOW_DETAILS)
			System.out.printf("Test file for id table: %s \n", filename);

		ArrayList<String> idsToFind = readFindRequests(filename);

		long startTime = System.nanoTime();
		
		for (String idStr : idsToFind)
		{
			int id;
			try
			{
				id = Integer.parseInt(idStr);
			}
			catch (NumberFormatException e)
			{
				if(SHOW_DETAILS)
					System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping.\n", idStr);
				continue;
			}

			if(SHOW_DETAILS)
				System.out.printf( "\nFinding song id: %d\n", id);

			try
			{
				// Search for song id in the song entry list
				
				for(SongEntry songEntry : songEntryList) {
					if(songEntry.getID() == id) {
						if(SHOW_DETAILS)
							System.out.printf("Song id %d found in songEntryList. \n", id);
					}
				}
				
			}
			catch (NoSuchElementException e)
			{ 
				if(SHOW_DETAILS)
					System.out.printf("Song id %d NOT found in songEntryList.\n", id);
			} 
		} // for all requested IDs to find

		long endTime = System.nanoTime();
		
		// stop the timer
		long estimatedTime = endTime - startTime;
				
		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
				+ TimeConverter.convertTimeToString(estimatedTime));
		
		System.out.println("Done with testing list of songs.\n");
	}


	/**
	 * Creates an object of type MyTunes class that, which reads the song information from a JSON input file
	 * and stores all entries in an array of SongEntry objects.
	 * Constructs an object of MyTunes, which populates two hash tables.
	 * Each tables uses different attribute of SongEntry class as the key to find. 
	 * Tests finding keys in each hash table and reports whether requested key is found.
	 */
	public static void main(String[] args) 
	{
		final String DATAFILE = "resources/music_genre_subset.json";	// Directory path for JSON file
		final String TESTFILE01 = "resources/findIDs_50000.txt";	// Example test file for hashing based on IDs

		// Note: This is similar to your previous projects.
		//		 Placed in a separate method for readability.
		// Parses the input file and stores all songs in an array of SongEntry object.
		SongEntry [] allSongs = readSongsFromDataFile(DATAFILE);

		// Create sub arrays for testing performance
		int sizes[] = {50000};
		
		for(int N : sizes) {
			System.out.printf( "\nTesting with size : %d\n", N);
			SongEntry [] subAllSongs = new SongEntry[N];
			System.arraycopy(allSongs, 0, subAllSongs, 0, N);
			
			// The constructor of class builds the hash tables
			// Insert into hash tables and sequential array list
			Benchmarking theStore = new Benchmarking(subAllSongs);
	
			System.out.println("\nBenchmarking IDs hash table for datafile of size " +  N + " and testfile " + TESTFILE01 + " of size " + (N/10));
			// Tests FHhashQPwFind and SongCompInt
			theStore.testIDtable(TESTFILE01);
	
			System.out.println("Benchmarking sequential array list for datafile of size " +  N + " and testfile " + TESTFILE01 + " of size " + (N/10));
			// Test arraylist of SongEntry
			theStore.testArrayList(TESTFILE01);
			
		}
		// flush the error stream
		System.err.flush();

		System.out.println("\nDone with MyTunes.");
	}
	
	public static void writeFindRequests(List<Integer> songIds, String filename, int N)
	{
		PrintWriter input = null;

		try 
		{
			File file = new File(filename);
			input = new PrintWriter(file);
			
			Random rand = new Random();
			
			for(int i = 0 ; i < N; i++) {
				int randomIndex = rand.nextInt(songIds.size());
				input.println(songIds.get(randomIndex));
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 	
		finally
		{
			if (input != null)
				input.close();
		}
	}
}
