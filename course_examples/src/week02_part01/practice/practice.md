Week 2 Part 1 Practice Problems
========================

Learning Objectives
-------------------
- FHarrayList class
- Iterator


Problem 1
---------
**Given the below list titled myString which is of type FHarrayList class.**
```java
    String line = "When Harry Met Sally X Wild Beast X La Femme Nikita X Back To The Future X";
    List<String> wordsAsList = Arrays.asList(line.split("\\s* \\s*"));
    FHarrayList<String> myStrings = new FHarrayList<String>(wordsAsList);
```
And the iterator
```java
    ListIterator<String> iter = myStrings.listIterator();
```

a) Use the iterator to print the list forward (i.e. from start to finish).

b) Use the iterator to print the list in reverse.

c) Use the iterator to replace all occurrences of "X" with "Q".<br>
&nbsp;&nbsp;&nbsp;&nbsp;Once `iter` finds the correct location use the set() method to replace the data.
```java
    if (potentialReplacee.equals("X"))
	    iter.set("Q");
```
<br>


