project folder:
anuvabanwasi-cs1c-project02/


Brief description of submitted files:

src/subsetsum/Sublist.java
    - One object of class Sublist represents a subset or sublist
    - Has a reference to the master list of prices.
    - Stores the indices of the items in the master list.
    - Uses an array of ints to represent the actual sublist.
    
src/subsetsum/SublistSong.java
    - One object of class SublistSong represents a subset or sublist
    - Has a reference to the master list of songs.
    - Stores the indices of the songs in the master list.
    - Uses an array of ints to represent the actual sublist of songs.    

src/subsetsum/ShoppingBag.java
    - Parses a CSV file with prices of the different items. After parsing, given the user's budget, returns a list of items to buy.
    - Captures and outputs metrics regarding the estimated run time of implementation.
    - Includes main() for debugging.

src/subsetsum/FoothillTunesStore.java
    - The main application.
    - Parses a JSON file that contains audio-features and meta data for ~60000 music tracks and creates an object of type MillionSongSubset.
    - After parsing, given the user's play list target duration, returns a list of songs to add to the play list.
    - Captures and outputs metrics regarding the estimated run time of implementation.
    - Includes main() for running the application.

src/subsetsum/GroceriesFileReader.java
	- One instance of class GroceriesFileReader reads a comma separated (CSV) file located in a directory called resources. 
	- Uses relative paths
	
src/subsetsum/SubsetSum.java
	- Solves Subset Sum Problem. Given a shopping list and a user's budget, returns a list of items to buy. 
	- Finds a subset of the master list whose sum is as large as possible without being larger than target budget
	- Provides efficiency improvements by returning when a new sublist whose sum() == t. Thus listing of all sublists is not needed if an exact match is found
	- Solves Subset Sum Problem for a play list of songs. Given a list of songs and a user's play list target duration, returns a list of songs to be added to play list. 
	- Provides optimization by discarding unnecessary subsets. If we are able to construct the bigger subset L + x (which is based on the smaller one), then we can throw away the smaller subset L.

resources/groceries.txt
    - A CSV (Comma Separated Value) file.
    - Each row contains the name of the grocery item and its price in the format:
      name,price
      
resources/music_genre_subset.json
    - A JSON (JavaScript Object Notation) file.
    - JSON file contains genre, artist_name, title, duration, song_id for each song

resources/RUN.txt
    - console output of ShoppingBag.java and FoothillTunesStore.java

README.txt
    - description of submitted files
