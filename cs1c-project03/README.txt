project folder:
anuvabanwasi-cs1c-project03/

Brief description of submitted files:

src/stacks/BrowserNavigation.java
    - One object of class BrowserNavigation manages the history of web-links the user navigates to. 
    - Tests the implementation of StackList and Navigator classes.
    - Uses two objects backLinks and forwardLinks of type StackList to simulate the back and forward buttons on browser. 
    - Includes main() for debugging.
    
src/stacks/Navigator.java
    - Provides navigation feature of class BrowserNavigation.. 
    - Uses 2 stacks backLinks and forwardLinks to store the history of links navigated by the user.
    - Methods to setCurrentLink(), goBack(), goForward() which update the currentLink to the link at the top of the 
      backLinks stack or forwardLinks stack as needed.
	
src/stacks/StackList.java
    - Objects of type StackList manage items in a singly linked list.
    - Represents a last-in-first-out (LIFO) stack of objects. 
    - It extends LinkedList with push, pop, peek, isEmpty, size and clear operations.
    - Push and pop operations are allowed only at one end of the stack.
    - Maintains top of the stack
	
src/stacks/LinkedList.java
    - Singly-linked list implementation of the Linked list. 
    - Provides operations to add at the head, add at tail, add in the middle of linked list.
    - Also provides operations to remove at the head, remove at the tail and in the middle of the linked list. 
    - Provides a fail fast iterator to iterate through the linked list and fail in case the list is structurally modified at 
      any time after the iterator is created.

resources/links.txt
resources/popEmptyStackOfLinks.txt
resources/links2.txt
resources/links3.txt
resources/links4.txt
resources/links5.txt
    - Different input files with test data containing URLs and commands such as "link", "back", "forward" and "contains" 

resources/RUN.txt
    - console output of BrowserNavigation.java based on the above test input data

README.txt
    - description of submitted files
