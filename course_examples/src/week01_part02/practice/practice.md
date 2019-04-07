Week 1 Part 2
========================

Learning Objectives
-------------------
- Generics

<hr>

<br>

*Discuss your answers in the discussion forum titled "Questions on Modules".*

<br>


Problem 1
---------
**Which of the following statements is correct?**<br>
 (Circle all that apply.)

a. Generics can help detect type errors at compile time, thus make programs more robust.
b. The parameter to a generic class can be an Object or a primitive.
c. By using generic objects we can avoid cumbersome castings.
d. Generics can make programs run faster.


<br><br>


Problem 2
---------
**Which of the following creates a class name Container with a generic type?**<br>
 (Circle all that apply?)

a. public class Container<T> { ... }
b. public class Container { ... }
c. public class Container(T) { ... }
d. public class Container() { ... }


<br><br>


Problem 3
---------
**Which of the following creates a generic type bounded by Number?**<br>
 (Circle all that apply?)

 a. <E extends Number>
 b. <E extends Object>
 c. <E>
 d. <E extends Integer>


<br><br>


Problem 4
---------
**Does the JVM have information regarding type parameters and type arguments?**<br>
  Provide a brief description. Include an example.


<br><br>


Problem 5
---------
**( True / False ) Given the statement the JVM loads two different types for `employeeNames` and `employeeIDs`.**

```java
    ArrayList<String> employeeNames = new ArrayList<String>();
    
    ArrayList<Integer> employeeIDs = new ArrayList<Integer>();
```
 
 
<br><br>


Problem 6
---------
**Define a generic class called `Room` with the following attributes and methods described below.**

- a width and a `height` of type `int`.
- two generic fields of the same type called `firstContainer` and `secondContainer`.
- A constructor which takes in two integers and sets it to `width` and `height`.
- A method called `setConents` that takes in two generic parameters and sets `firstContainer` and `secondContainer` respectively.


<br><br>


Problem 7
---------
**What is the following converted to after type erasure?**<br>
 Rewrite with modifications.

```java
class Tuple<K, V> 
{
    private final K k;
    private final V v;
   
    public Tuple(K key, V value) 
    {
        k = key;
        v = value;
    }
 
    public String toString() 
    {
        return String.format("KEY: '%s', VALUE: '%s'", k, v);
    }
```

<br><br>


Problem 8
---------
**What does the compiler generate after translating the following?**<br>
 Rewrite with modifications.

```java
class Vehicle { ... } 


class Motorcycle extends Vehicle { ... } 


class Tesla extends Vehicle { ... } 


class Garage<X extends Vehicle>
{   
    public void parkVehicle( X someVar )
    { ... }
    
    public X getVehicle()
    { ... }
}


    // somewhere in main
    Motorcycle ducati = new Motorcycle(...);
    Tesla p85d = new Tesla(...);
    
    Garage<Vehicle> showroom = new Garage<Vehicle>();
    showroom.parkVehicle(ducati);
    showroom.parkVehicle(p85d);
    
    Garage<Motorcycle> cycleRoom = new Garage<Motorcycle>();
    
```

<br><br>



Problem 9
---------
**What does the compiler generate after translating the following?**<br>
 Rewrite with modifications.

```java
interface Comparable<T> 
{  
    public int compareTo(T that); 
} 


class Queue<T extends Comparable <T>>
{
    ArrayList<T> tasks;
    
    public T getTask(int i)
    { ... }
    
    public int compareTo(T other)
    { ... }
}
```

<br><br>
