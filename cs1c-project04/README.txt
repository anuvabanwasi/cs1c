project folder:
anuvabanwasi-cs1c-project04/

Brief description of submitted files:

src/queues/MyTunes.java
    - One object of class MyTunes simulates a digital jukebox. 
    - It uses a queue which holds playlist
    - Tests the implementation of Queue and Jukebox classes.
    - Starts by creating an object of type MyTunes which initializes three playlists favoritesPL, loungePL, and roadTrip by reading input file
   	- Prompts the user for total songs they want to play.
    - Simulates playing one song at a time from each playlist until either total requested number of songs have been played or all playlists are empty.
    - Includes main() for debugging
    
src/queues/Jukebox.java
    - One object of class Jukebox manages 3 objects of type Queue. 
    - An instance of the class reads a file which includes the user's requests for a the name of a song to be added to a specific playlist. 
    - It will then add songs to the three playlists "favorites", "lounge", and "road trip" accordingly.
    - Sorts all known songs on song title and uses binary search to efficiently look for songs with the specified title. Growth rate O(log n)
    - Tests the implementation of Queue class.
 	
src/stacks/Queue.java
    - Objects of type Queue manage items in a queue.
    - Represents a first-in-first-out (FIFO) queue of objects. 
    - Objects of type Queue manage items in a singly linked list where we can enqueue() items to the end and dequeue() items from the front of the queue.
    - It extends LinkedList with enqueue, dequeue, peek, isEmpty, size and toString operations.
    - Enqueue operation only allowed at end of queue
    - Dequeue operation only allowed at head of the queue.
	
src/stacks/LinkedList.java
    - Singly-linked list implementation of the Linked list. 
    - Provides operations to remove at the head, add at tail, retrieve from the list.
    - Also provides operations to remove at the head, remove at the tail and in the middle of the linked list. 
    - Provides a fail fast iterator to iterate through the linked list and fail in case the list is structurally modified at 
      any time after the iterator is created.

src/queues/MyTunesTest.java
    - One object of class MyTunes simulates a digital jukebox. 
    - It uses a queue which holds playlist
    - Tests the implementation of Queue and Jukebox classes.
    - Starts by creating an object of type MyTunes which initializes three playlists favoritesPL, loungePL, and roadTrip by reading input file
   	- Prompts the user for total songs they want to play.
    - Simulates playing one song at a time from each playlist until either total requested number of songs have been played or all playlists are empty.
    - Simulates testing with large input files(50000)
 	- Times amount of time taken
    - Includes main() for debugging
    
resources/tunes.txt
resources/tunes3.txt
resources/tunes4.txt
resources/tunes_truncated.txt
    - Different input files with test data in the format playlist,songTitle indicating that the user wants to add a SongEntry object with a specific songTitle to the playlist.

resources/music_genre_subset_2.json
	- Shorter JSON source file containing attributes of songs
	- Smaller subset of 14 songs

resources/RUN.txt
    - console output of MyTunes.java based on the above test input data

README.txt
    - description of submitted files
