project folder:
anuvabanwasi-cs1c-project07/

Brief description of submitted files:
		
src/cs1c/TimeConverter.java 
	- Converts duration into a string representation.
	
src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.
    
src/cs1c/SongEntry.java 
	- One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.
	
src/hashTables/FHhashQP.java
	- One object of FHhashQP class represents a hash table implementation that uses quadratic probing to resolve collisions
	
src/hashTables/FHhashQPwFind.java
	- Implementation of hash table that checks if a key is in the hash table.
	- FHhashQPwFind from the class FHhashQP.
	- Implements find(), myHashKey() and findPosKey() methods based on key instead of object

src/hashTables/HashEntry.java
	- New data type to act as the underlying type for the array representing the hash table.
	
src/hashTables/SongCompInt.java
	- Wrapper class that encloses a SongEntry. 
	- Used to compare objects based on the song's integer id field.
	
src/hashTables/SongsCompGenre.java
	- Wrapper class that encloses a SongEntry. 
	- Compares objects based on the songs String genre field.
	- Stores a genre and the number of songs in that genre
	- Used to determine the number of songs in each genre.
	
src/hashTables/TableGenerator.java
	- Creates and populate two hash tables of type FHhashQPwFind class, one for each wrapper class.
 	- tableOfSongIDs a hash table based on SongCompInt
 	- tableOfSongGenres a hash table based on SongsCompGenre
		 
src/hashTables/MyTunes.java
	- Tests the functionality of FHhashQPwFind.java.
 	- Contains main() method
 	
src/hashTables/Benchmarking.java
	- Tests the functionality of FHhashQPwFind.java.
	- Performs benchmarking to check if quadratic probing hash table yields faster access in a search compared with a sequential find on the ArrayList of SongEntry objects. 
 	- Contains main() method
 	
resources/findGenres.txt
    - An input file to test find by genre name as key

resources/findGenres2.txt
    - An input file to test find by genre name as key with invalid genres as input
    
resources/findIDs.txt
    - A smaller input file to test find by song id as key
   
resources/findIDs2.txt
    - A smaller input file to test find by song id as key with invalid Ids as input
    
resources/findIDs_10000.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains 1000 random song ids
  
resources/findIDs_20000.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains 2000 random song ids

resources/findIDs_30000.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains 3000 random song ids 
    
resources/findIDs_40000.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains 4000 random song ids  

resources/findIDs_50000.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains 5000 random song ids 
 
resources/findIDs_All.txt
    - An input file to use to test benchmarking of quadratic probing versus sequential array list
    - contains all song ids 
           
resources/Benchmarking1_2_3_Average.png
	- A graphical representation of the time taken by hash table using quadratic probing vs sequential array list
	- Analysis of the graphical representation
	
	I compared the search times of the hash table and the sequential array list as we vary N 
	the number of elements in each data structure.
	I ran the Benchmarking.java file on data sets of size 10000, 20000, 30000, 40000 and 50000. 
	In addition, I ran this 3 times creating output files RUN_Benchmarking_1.txt, RUN_Benchmarking_2.txt 
	and RUN_Benchmarking_3.txt. 
	
	I found that the hash table implementation was much faster than the sequential array list. 
	This is because hashing of the search key leads to the location where the object corresponding 
	to the search key is stored in constant time or declares the element to be absent in constant time. 
	On the other hand, the sequential list uses linear search, therefore the search has to examine each element 
	in the array list till either the element is found or if end of the list is reached declared to be not 
	present in the list. 
	
	From the graph, we can see that array list is O(n) as the growth is linear with increase in N. 
	On the other hand, hashing is O(1) since the growth is constant with increase in N. 	

resources/music_genre_subset.json
	- A JSON (JavaScript Object Notation) file.
    - JSON file contains genre, artist_name, title, duration, song_id for each song
    
resources/music_genre_subset_2.json
	- A JSON (JavaScript Object Notation) file.
    - A smaller JSON file contains genre, artist_name, title, duration, song_id for each song
	
resources/RUN.txt
    - console output of MyTunes.java (Output of Part 1 of Assignment)
    
resources/RUN_Benchmarking_run1.txt
    - console output of Benchmarking.java (Part 2 Output Benchmarking Results - Quadratic Probing vs Sequential array list)
    
resources/RUN_Benchmarking_run2.txt
    - console output of Benchmarking.java (Part 2 Output Benchmarking Results - Quadratic Probing vs Sequential array list)

resources/RUN_Benchmarking_run3.txt
    - console output of Benchmarking.java (Part 2 Output Benchmarking Results - Quadratic Probing vs Sequential array list)
