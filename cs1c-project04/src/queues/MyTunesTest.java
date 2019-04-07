package queues;


import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;
import queues.Jukebox;
import queues.Queue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * Creates an object of type MyTunes which simulates a playlist queue.
 * Enqueues and dequeues SongEntry objects from each playlist. 
 * Simulates playing each song and finally checks the state of each playlist.
 * Simulates testing with large input files
 * Generates large test file tunes4.txt using Math.random()
 * Times amount of time taken
 * @author Foothill College, Anuva Banwasi
 */
public class MyTunesTest 
{
	public static final boolean SHOW_DETAILS = false;

	// holds the name of each playlist
	private static final String [] namesOfPlayLists = {"favorites", "lounge", "road trip"};

	// the three playlists, where songs are played in FIFO order
	private Queue<SongEntry> favoritePL;
	private Queue<SongEntry> roadTripPL;
	private Queue<SongEntry> loungePL;

	// Define the class Jukebox such that the constructor reads the file with user's request for
	//       what the song to add to which playlist.
	// 		 Possible valid playlists are "favorites", "lounge" and "road trip", 
	//		 called favoritesPL, loungePL and roadTripPL respectively.
	//       Then accesses the three playlists and sets them to the appropriate class attributes.
	private Jukebox jukebox;

	private int N = 50000;

	/**
	 * Creates an object of type Jukebox to read the input file used to fill the 
	 * three Queue objects with SongEntry items.
	 * @param requestedFile	The name of the test file to read from.
	 * @param allSongs	The array of songs read from the JSON file.
	 */
	public MyTunesTest(String requestedFile, SongEntry[] allSongs)
	{
		// Define the constructor of class Jukebox such that initializes the playlists.
		jukebox = new Jukebox();	

		// Define the class Jukebox fillPlaylists() method such that it reads the test file
		// and then adds songs to the appropriate Queue<SongEntry>.
		// For example, if the file contains line: 
		// favorites,title
		// Then the first song found that equals the title will be placed in the favorites playlist.
		
		createRequestedFile(requestedFile, allSongs);
		
		jukebox.fillPlaylists(requestedFile, allSongs);	

		favoritePL = jukebox.getFavoritePL();
		loungePL = jukebox.getLoungePL();
		roadTripPL = jukebox.getRoadTripPL();
	}
	
	private void createRequestedFile(String requestedFile, SongEntry[] allSongs) {
		List<String> entries = new ArrayList<String>();
		
		for(int i = 0; i < N; i++) {
			int playListIndex = (int)(Math.random() * namesOfPlayLists.length );
			int songListIndex = (int)(Math.random() * allSongs.length );
	
			String playList = namesOfPlayLists[playListIndex];
			String songTitle = allSongs[songListIndex].getTitle();
			
			String entry = playList + "," + songTitle;
			
			entries.add(entry);
		} 
		
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(requestedFile);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for(String entry : entries) {
				printWriter.print(entry);
				printWriter.print("\n");
			}
		    printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}

	/**
	 * Sets the total number of songs to play.
	 * @return	number of songs to play.
	 */
	private int getTotalSongsToPlay() 	
	{
		return favoritePL.size() + loungePL.size() + roadTripPL.size();
	}

	/**
	 * Returns the playlist which matches the given input name.
	 * @param name	the name of the playlist to return.
	 * @return	the playlist which matches the input name.
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
	 * Prints out the contents of playlist with the corresponding name, if any
	 * @param name	the name of the playlist to print.
	 */
	private void printPlayList(String name) 
	{
		switch(name)
		{
		case "favorites": System.out.println("\n" + favoritePL); break;
		case "lounge": System.out.println("\n" + loungePL); break;
		case "road trip": System.out.println("\n" + roadTripPL); break;
		}
	}


	/**
	 * Creates one object of type MillionSongSubset class, which reads input from a JSON file. Uses the
	 * attributes stored in JSON object to store all entries in an array of SongEntry objects.
	 * Next, it parses a plain-text file which lists the names of each playlist. Then queues up
	 * songs for each playlist. Simulates playing a requested number of songs. Finally,
	 * it reports back if a playlist is empty.
	 *
	 * @param args	Not used.
	 */
	public static void main(String[] args) 
	{
		final String DATAFILE = "resources/music_genre_subset.json"; // Directory path for JSON file

		// TODO: Tests Jukeboxs class by enqueu-ing songs in the different playlists.
		final String TESTFILE = "resources/tunes4.txt";	// Directory path for plain-text file

		// NOTE: An example of testing the boundary condition where we dequeue from empty playlist
		//final String TESTFILE = "resources/tunes_truncated.txt";	

		// TODO:  Provide additional test input cases. That means your own tunes_CASE.txt input test file.
		//        Clarify the purpose of each test case in your README.txt file.
		//final String TESTFILE = "resources/tunes_??.txt;

		// parses the JSON input file
		MillionSongDataSubset msd = new MillionSongDataSubset(DATAFILE);

		// retrieves the parsed objects
		SongEntry [] allSongs = msd.getArrayOfSongs();

		ArrayList<SongEntry> songList = new ArrayList<SongEntry>(Arrays.asList(allSongs));

		// displays the number of songs read from the input file
		System.out.printf("Welcome! We have over %d in FoothillTunes store! \n", songList.size());

		// for measuring run time
		long startTime, estimatedTime;
						
		// capture the start time
		startTime = System.nanoTime();  
		
		/// calls the class Jukebox to read in the test file, which includes user's request
		//  of which SongEntry objects to add to which playlist queue.
		MyTunesTest tunes = new MyTunesTest(TESTFILE, allSongs);

		// displays the total number of songs in the combined playlists
		int totalSongsToPlay = tunes.getTotalSongsToPlay();
		System.out.printf("Total number of songs in playlists: %d\n", totalSongsToPlay);

		if (SHOW_DETAILS)
		{
			System.out.println("\nSongs in each playlist:");
			for (int i = 0; i < namesOfPlayLists.length; i++)
			{
				tunes.printPlayList(namesOfPlayLists[i]);
			}
		}

		// prompts the user for their budget 
		Scanner keyboard = new Scanner(System.in);
		do 
		{
			// REMINDER: Test your implementation with different number of requested songs, including boundary cases.
			System.out.println("\nEnter your the number of songs you would like to play:");
		} while(!keyboard.hasNextDouble());

		// set the number of songs to test dequeue-ing from the three playlists
		int numberOfSongsToPlay = Integer.parseInt(keyboard.nextLine());
		System.out.printf("\nPlaying %d number of songs...\n", numberOfSongsToPlay);

		// Simulates playing one song at a time from each playlist until either:
		// - we've played the requested number of songs,
		// - or all playlists are empty.
		int playListNumber = 0;
		for (int songNumber = 0;
				songNumber < numberOfSongsToPlay && songNumber < totalSongsToPlay;
				songNumber++)
		{
			// picks the next playlist to play from
			playListNumber = songNumber % (namesOfPlayLists.length);

			// Attempts to remove a song from a playlist
			Queue<SongEntry> currentPlayList = tunes.getPlayList(namesOfPlayLists[playListNumber]);

			try
			{
				SongEntry currentSong = currentPlayList.dequeue();
				if (SHOW_DETAILS)
					System.out.printf("Playing song title \"%s\"\n", currentSong.getTitle());
			}

			// NOTE: If a playlist was empty, then we don't want to count this turn.
			//       One approach is to increment the number of allowable songs.
			catch (java.util.NoSuchElementException ex)
			{
				System.out.printf("WARNING: playlist \"%s\" is empty! \n", currentPlayList.getName());
				numberOfSongsToPlay++;
				totalSongsToPlay++;
			}
			//System.out.println("songNumber " + songNumber + " numberOfSongsToPlay " + numberOfSongsToPlay + " totalSongsToPlay " + totalSongsToPlay);
		}

		// check whether a playlist is empty
		System.out.println("\nChecking the size of each playlist:");
		for (playListNumber = 0; playListNumber < namesOfPlayLists.length; playListNumber++)
		{
			Queue<SongEntry> queue = tunes.getPlayList(namesOfPlayLists[playListNumber]);
			if(queue.peek() == null)
				System.out.printf("Playlist \"%s\" has *no* songs remaining.\n", namesOfPlayLists[playListNumber]);
			else
				System.out.printf("Playlist \"%s\" is %d song(s) remaining.\n", queue.getName(), queue.size());
		}

		// finally, see if any song remained unplayed
		System.out.println("\nSongs in each list:");
		for (playListNumber = 0; playListNumber < namesOfPlayLists.length; playListNumber++)
		{
			tunes.printPlayList(namesOfPlayLists[playListNumber]);
		}

		// capture the elapsed time
		estimatedTime = System.nanoTime() - startTime;
				
		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
						+ TimeConverter.convertTimeToString(estimatedTime));
		// flush the error stream
		System.err.flush();

		System.out.println("\nDone with MyTunes.");
	}
}