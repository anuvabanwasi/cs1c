package hashTables;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import cs1c.SongEntry;

/**
 * Creates and populate two hash tables of type FHhashQPwFind class, one for each wrapper class.
 * tableOfSongIDs a hash table based on SongCompInt
 * tableOfSongGenres a hash table based on SongsCompGenre
 * @author Anuva
 *
 */
public class TableGenerator {
	
	// a hash table based on SongCompInt
	private FHhashQPwFind<Integer,SongCompInt> tableOfSongIDs;
	
	// a hash table based on SongsCompGenre
	private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;
	
	// arraylist of genre names
	private ArrayList<String> genreNames = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	TableGenerator(){
		tableOfSongIDs = new FHhashQPwFind<Integer,SongCompInt>();
		tableOfSongGenres = new FHhashQPwFind<String, SongsCompGenre>();
	}
	
	/**
	 * Populates the tableOfIDs hash table.
	 * @param allSongs songs to populate into hash table
	 * @return FHhashQPwFind a hash table based on SongCompInt
	 */
	public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs) {
		//System.out.println("\n\n\nsize of the hash table of ids " + tableOfSongIDs.mSize + " with load factor " + tableOfSongIDs.mLoadSize );

		for(SongEntry song : allSongs) {
			tableOfSongIDs.insert(new SongCompInt(song));
		}
		//System.out.println("\n\n\nsize of the hash table of ids " + tableOfSongIDs.mSize + " with load factor " + tableOfSongIDs.mLoadSize );

		return tableOfSongIDs;
	}

	/**
	 * Populates the tableOfGenres hash table and ArrayList of genre names.
	 * @param allSongs songs to populate into hash table
	 * @return FHhashQPwFind a hash table based on SongsCompGenre
	 */
	public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {
		for(SongEntry song : allSongs) {
			String genre = song.getGenre();
			
			try {
				SongsCompGenre scg = tableOfSongGenres.find(genre);
				scg.addSong(song);
			} catch (NoSuchElementException e) {
				//System.out.println("exception " + e);
				SongsCompGenre scg = new SongsCompGenre(genre, song);
				tableOfSongGenres.insert(scg);
				genreNames.add(genre);

			}
		}
		
		return tableOfSongGenres;
	}
	
	/**
	 * Getter method to get all the genre names in the hash table
	 * @return
	 */
	public ArrayList<String> getGenreNames() {
		
		return genreNames;
	}
}
