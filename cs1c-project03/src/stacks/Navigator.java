package stacks;

import stacks.StackList;

/**
 * Provides navigation feature. Uses 2 stacks backLinks and forwardLinks to store the history of links navigated by the user
 * @author anuva
 *
 */
public class Navigator {
	
	// member variables
	private String currentLink;
	private StackList<String> backLinks;
	private StackList<String> forwardLinks;
	private boolean first;

	/**
	 * Constructor. Initializes currentLink, backLinks and forwardLinks
	 */
	Navigator() {
		currentLink = "";
		backLinks = new StackList<String>("Back");
		forwardLinks = new StackList<String>("Forward");
		first = true;
	}


	/**
	 * Sets the current link of the navigator to specified link and updates the backLinks and forwardLinks stacks.
	 * When a user requests a new link, push the current link on the backLinks stack and clear the forwardLinks stack.
	 * @param link current requested link
	 */
	public void setCurrentLink(String link) {

		if (first) {
			this.currentLink = link;
			first = false;
		} else {
			// update stacks
			backLinks.add(currentLink);
			this.currentLink = link;
			forwardLinks.clear();
		}

	}

	/**
	 * Invoked when user clicks back button on the browser
	 * Updates the currentLink to the link at the top of the backLinks stack 
	 */
	public void goBack() {
		if (!backLinks.isEmpty()) {
			forwardLinks.add(currentLink);
			currentLink = backLinks.pop();
		} else {
			System.err.println("WARNING! No back links left....");
		}
	}

	/**
	 * Invoked when user clicks forward button on the browser
	 * Updates the currentLink to the link at the top of the forwardLinks stack 
	 */
	public void goForward() {
		if (!forwardLinks.isEmpty()) {
			backLinks.push(currentLink);
			currentLink = forwardLinks.pop();

		} else {
			System.err.println("WARNING! No forward links left....");
		}

	}

	// Accessors
	public StackList<String> getBackLinks() {
		return backLinks;
	}

	public StackList<String> getForwardLinks() {
		return forwardLinks;
	}

	public String getCurrentLink() {
		return currentLink;
	}
}
