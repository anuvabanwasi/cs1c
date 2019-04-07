package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * Wrapper class for SongEntry object. Compares objects based on the songs String genre field.We will use this to determine the number of songs in each genre.
 * @author Anuva
 *
 */
public class SongsCompGenre implements Comparable<String>{
	private String genre;							// name of the genre.
		
	private ArrayList<SongEntry> data;				// all the songs in that genre in our data
	
	/**
	 * Constructor
	 */
	public SongsCompGenre() {
		genre = "";
		data = new ArrayList<SongEntry>();
	}
	
	/**
	 * Adds song to the list of songs for the specified genre
	 * @param genre genre of the song
	 * @param song song to add to the list of songs for specified genre
	 */
	public SongsCompGenre(String genre, SongEntry song) {
		this();
		this.genre = genre;
		addSong(song);
	}
	
	/**
	 * Adds song to the list of songs
	 * @param e
	 */
	void addSong(SongEntry e) {
		data.add(e);
		
	}
	
	/**
	 * Compares SongsCompGenre objects based on the songs' genre field
	 */
	@Override
	public int compareTo(String key) {
		return this.genre.compareTo(key);
	}

	/**
	 * Calculates hash code of the SongsCompGenre object based on hash code of genre
	 */
	@Override
	public int hashCode() {
		return genre.hashCode();
	}

	/**
	 * Override equals() to compare SongsCompGenre objects based on genre
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		return genre.equals(((SongsCompGenre)obj).genre);
	}

	@Override
	public String toString() {
		return "SongCompGenre [genre=" + genre + ", songs=" + data + "]";
	}

	
	/**
	 * Accessor for genre
	 * Returns the genre of the SongsCompGenre object
	 * @return
	 */
	public String getName() {
		return genre;
	}

	/**
	 * Accessor for arraylist of song entry
	 * Returns the list of SongEntry objects associated with the specified genre
	 * @return
	 */
	public ArrayList<SongEntry> getData() {
		return data;
	}

}
