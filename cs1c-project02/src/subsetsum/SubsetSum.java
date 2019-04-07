package subsetsum;

import java.util.ArrayList;
import cs1c.SongEntry;

/**
 * Algorithm to solve the Subset Sum problem. As per class modules, the subset sum problem is a pair (S, t), 
 * where S = {x1, x2, ... xn} is a set of positive integers and t (the target) is a positive integer. 
 * The problem asks for a subset of S whose sum is as large as possible, but not larger than t. 
 * The below algorithm has exponential time complexity but has modest efficiency improvements.
 * @author anuva
 *
 */
public class SubsetSum {
	
	/**
	 * Computes the subset L' of shoppingList with the sum(L') as large as possible but less than or equal to duration.
	 * Solves Subset Sum Problem with efficiency improvements.
	 * @param shoppingList ArrayList of Double representing the prices of items in the shopping list
	 * @param budget double representing user's target budget
	 * @return an ArrayList representing a list of items to buy
	 */
	public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {
		
		if(shoppingList == null) {
			System.out.println("Please provide valid input!");
			System.exit(1);
		}
		
		// Only keep items with price <= target budget from master shopping list
		ArrayList<Double> trimmedList = trimShoppingList(shoppingList, budget);
		
		// if target budget > total price of all items, return entire shopping list
		if(budget > getTotal(trimmedList)) 
			return trimmedList;
		
		// if target budget < price of cheapest item, return empty list
		if(budget < getMinimum(trimmedList)) 
			return new ArrayList<Double>();
		 
		// Run Subset Sum Algorithm
		Sublist currentBest = runSubsetSum(trimmedList, budget);
		
		// Get sublist of groceries that is either an exact match or closest to meeting the user's budget.
		return currentBest.getListFromIndices();
	}

	/**
	 * Use Subset Sum algorithm to generate sublist of groceries closest to user's target budget
	 * @param list ArrayList of Double representing the prices of items in the shopping list. 
	 * This list only has items with price less than or equal to target budget from shopping list
	 * @param budget double representing user's target budget
	 * @return Sublist representing subset closest to target budget
	 */
	private static Sublist runSubsetSum(ArrayList<Double> list, double budget) {
		// Empty set
		Sublist empty = new Sublist(list);
		
		// Collection to store sub lists, initialized with empty set
		ArrayList<Sublist> candidates = new ArrayList<Sublist>();
		candidates.add(empty);
		
		Sublist currentBest = empty;
		
		// Loop over all elements in the shopping list
		for(int i = 0; i < list.size(); i++) {
			
			double price = getPrice(list, i);
					
			// store number of elements going into inner loop
			int currentSize = candidates.size(); 
				
			// Loop over subsets that are already members of candidates
			for(int j = 0; j < currentSize; j++) {
				
				Sublist sublist = candidates.get(j);
						
				// if [sum(sublist) + x == t] return items in candidates since budget met
				if((sublist.getSum() + price) == budget) 
					return handleExactMatch(candidates, i, sublist);
				
				// if [sum(sublist) + x <= t], create sublist (sublist + x) and add to candidates
				else if((sublist.getSum() + price) < budget) 
					currentBest = handleClosestMatch(candidates, currentBest, i, sublist);
			}
		}
		//Of all the subsets that end up in candidates, find the sub-list currentBest L' with the largest sum()		
		return currentBest;
	}

	/**
	 * Get sublist of groceries that is an exact match to user's target budget.
	 * @param candidates List of potential candidate subsets
	 * @param i index of item in trimmed grocery list to add to list of potential candidates
	 * @param sublist subset representing groceries that are an exact match to budget
	 * @return sublist of groceries that is an exact match to user's target budget.
	 */
	private static Sublist handleExactMatch(ArrayList<Sublist> candidates, int i, Sublist sublist) {
		Sublist entry = sublist.addItem(i);
		
		candidates.add(entry);
		
		return entry;
	}

	/**
	 * Returns subset of groceries that is closest to meeting the user's budget. 
	 * Invokes addItem(int) to generate a new subset from an existing subset and 
	 * adds sublist to list of potential candidates. Updates the current best known
	 * subset if the sum of the newly added sublist is closer to the user's budget.
	 * @param candidates ArrayList of Sublist objects representing potential subset candidates
	 * @param currentBest current best known subset of groceries
	 * @param i index of item in list
	 * @param sublist Sublist object that is a potential candidate
	 * @return Sublist representing current best known subset of groceries
	 */
	private static Sublist handleClosestMatch(ArrayList<Sublist> candidates, Sublist currentBest, int i, Sublist sublist) {
		Sublist entry = sublist.addItem(i);
		
		candidates.add(entry);
				
		// Update the best current known sub list
		if(entry.getSum() > currentBest.getSum()) {
			currentBest = entry;
		}
		return currentBest;
	}

	
	/**
	 * This method trims the original shopping list to only include items with price less than or equal to the budget
	 * @param shoppingList original shopping list
	 * @param budget user's budget which determines a list of items to buy
	 * @return list with only items less than or equal to the budget
	 */
	private static ArrayList<Double> trimShoppingList(ArrayList<Double> shoppingList, double budget) {
		ArrayList<Double> trimmedList = new ArrayList<Double>();
		for(double item : shoppingList) {
			if(item <= budget) {
				trimmedList.add(item);
			}
		}
		
		return trimmedList;
	}

	/**
	 * Get the minimum priced item in the list
	 * @param trimmedList list of items 
	 * @return the cheapest item in the list
	 */
	private static double getMinimum(ArrayList<Double> trimmedList) {
		
		Double minimumVal = Double.MAX_VALUE;
		
		for(Double price : trimmedList) {
			if(price < minimumVal)
				minimumVal = price;
		}
		return minimumVal;
	}
	
	/**
	 * Returns the price of element at position i in the list
	 * @param shoppingList list of items
	 * @param i index of the element in the list
	 * @return price of element at position i in the list
	 */
	private static double getPrice(ArrayList<Double> shoppingList, int i) {
		return shoppingList.get(i);
	}

	/**
	 * Get the total of prices of the items in the list
	 * @param shoppingList list of items
	 * @return the total of elements in the shopping list
	 */
	private static double getTotal(ArrayList<Double> shoppingList) {
		double total = 0.0;
		
		for(double price : shoppingList) {
			total = total + price;
		}
		
		return total;
	}
	
	
	/**
	 * Computes a subset of songs with total duration equal to exact match of user's target duration or closest match to the user's target duration
	 * Uses Subset Sum Algorithm with an optimization where unnecessary subsets are discarded. For example, if we are able to construct a bigger subset L+x,
	 * from the smaller subset L, the smaller subset L is discarded.
	 * This optimization helps the algorithm process larger data sets.
	 * @param songList list of SongEntry objects, where each SongEntry represents a song
	 * @param budget target duration of desired play list in minutes
	 * @return sublist of songs with total duration equal to exact match of target duration or closest match to the target duration 
	 */
	public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double budget) {

		if(songList == null) {
			System.out.println("Please provide valid input!");
			System.exit(1);
		}
		
		// Convert duration to seconds
		double duration = budget * 60;
		
		// Only keep songs with duration <= target duration from song list
		ArrayList<SongEntry> trimmedList = trimSongList(songList, duration);
		
		// if target duration > duration of all songs, return entire song list
		if(duration > getTotalDuration(trimmedList)) 
			return trimmedList;
		
		// if target duration < length of shortest song, return empty list
		if(duration < getShortestSong(trimmedList)) 
			return new ArrayList<SongEntry>();
		 
		SublistSong currentBest = runSubsetSumForSongs(trimmedList, duration);
		
		//Of all the subsets that end up in candidates, find the sub-list L' with the largest sum()		
		return currentBest.getListFromIndices();
		
	}

	/**
	 * Use Subset Sum algorithm to generate sublist of songs closest to user's target play list duration
	 * @param list ArrayList of SongEntry representing the songs in the play list. 
	 * This list only has songs with duration less than or equal to target play list duration from master song list
	 * @param duration double representing user's target play list duration
	 * @return SublistSong representing subset of songs closest to target duration
	 */
	
	private static SublistSong runSubsetSumForSongs(ArrayList<SongEntry> list, double duration) {
		// Empty set
		SublistSong empty = new SublistSong(list);
		
		// Collection to store sub lists, initialized with empty set
		ArrayList<SublistSong> candidates = new ArrayList<SublistSong>();
		candidates.add(empty);
		
		SublistSong currentBest = empty;
		
		// Loop over all elements in the list
		for(int i = 0; i < list.size(); i++) {
			
			double time = getDuration(list, i);
					
			// store number of elements going into inner loop
			int currentSize = candidates.size(); 
				
			// Loop over subsets that are already members of candidates
			for(int j = 0; j < currentSize; j++) {
				
				SublistSong sublist = candidates.get(j);
						
				// if [sum(sublist) + x == t] return items in candidates since target duration met
				if((sublist.getSum() + time) == duration) 
					return handleExactMatch(candidates, i, sublist);
				
				// if[sum(sublist) + x <= t], create sublist (sublist + x) and add to candidates
				else if((sublist.getSum() + time) < duration) 
					currentBest = handleClosestMatch(candidates, currentBest, i, sublist);
				
			}
		}

		return currentBest;
	}

	
	/**
	 * Returns subset of songs that exactly match user's target play list duration. 
	 * Invokes addItem(int) to generate a new subset from an existing subset and 
	 * adds sublist to list of potential candidates. 
	 * @param candidates ArrayList of SublistSong objects representing potential subset candidates.
	 * @param i index of item in play list
	 * @param sublist SublistSong object that is a potential candidate
	 * @return SublistSong representing current best known subset of songs
	 */
	private static SublistSong handleExactMatch(ArrayList<SublistSong> candidates, int i, SublistSong sublist) {
		SublistSong  entry = sublist.addItem(i);
		candidates.add(entry);
		return entry;
	}

	
	/**
	 * Returns subset of songs that is closest to meeting the user's target play list duration. 
	 * Invokes addItem(int) to generate a new subset from an existing subset and 
	 * adds sublist to list of potential candidates. Updates the current best known
	 * subset if the sum of the play times of newly added sublist is closer to the user's target play list duration.
	 * In order to handle large data set, optimizes by discarding unnecessary subsets. For example,  
	 * if sublist (L + x) is created from smaller subset L, we can throw away L
	 * @param candidates ArrayList of SublistSong objects representing potential subset candidates.
	 * @param currentBest current best known subset of songs
	 * @param i index of item in play list
	 * @param sublist SublistSong object that is a potential candidate
	 * @return SublistSong representing current best known subset of songs
	 */
	private static SublistSong handleClosestMatch(ArrayList<SublistSong> candidates, SublistSong currentBest, int i, SublistSong sublist) {
		SublistSong entry = sublist.addItem(i);
		candidates.add(entry);

		// Update the best current known sub list
		if(entry.getSum() > currentBest.getSum()) {
			currentBest = entry;
		}
		
		// optimization - since (sublist + x) is created from smaller subset sublist, we can throw away sublist
		candidates.remove(sublist);
		
		//Of all the subsets that end up in candidates, currentBest is the sub-list with the largest sum()		
		return currentBest;
	}
	

	/**
	 * Returns the duration of song at position i in the song list
	 * @param songList play list of songs
	 * @param i index of the song in the list
	 * @return duration of the ith song in the list
	 */
	private static double getDuration(ArrayList<SongEntry> songList, int i) {
		return songList.get(i).getDuration();
	}

	/**
	 * Get the total of duration of the songs in the list
	 * @param song play list of songs
	 * @return the total of duration of the elements in the song list
	 */
	private static double getTotalDuration(ArrayList<SongEntry> song) {
		double total = 0.0;
		
		for(SongEntry price : song) {
			total = total + price.getDuration();
		}
		
		return total;
	}


	/**
	 * Get the song with the shortest duration in the list
	 * @param songList play list of songs
	 * @return the song with the shortest duration in the list
	 */
	private static int getShortestSong(ArrayList<SongEntry> songList) {
		
		int minimumVal = Integer.MAX_VALUE;
		
		for(int i = 0; i < songList.size(); i++) {
			if(songList.get(i).getDuration() < minimumVal)
				minimumVal = songList.get(i).getDuration();
		}
		return minimumVal;
	}
	
	/**
	 * This method trims the original song list to only include songs with duration less than or equal to the target duration
	 * @param songList original song list
	 * @param duration user's target duration which determines a list of songs to include
	 * @return list with only songs less than or equal to the target duration
	 */
	private static ArrayList<SongEntry> trimSongList(ArrayList<SongEntry> songList, double duration) {
		ArrayList<SongEntry> trimmedList = new ArrayList<SongEntry>();
		for(SongEntry item : songList) {
			if(item.getDuration() <= duration) {
				trimmedList.add(item);
			}
		}
		return trimmedList;
	}
}
