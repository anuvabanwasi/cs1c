Week 6 Part 2 Practice Problems
========================

Learning Objectives
-------------------
- HashCode


Problem 1
---------

**Assume that the client wants to search by employee `name`. Rewrite the hash function to determine the index based on employee `name`.**

```java
public class Employee
{
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id) 
    {
        this.name = name;
        this.id = id;
        salary = 1500.00;
    }

    public String getName()
    {    return name;  }
    
    public int getID() 
    {    return this.id;  }
    
    public double getSalary() 
    {    return salary;  }
    
    public void setSalary(double salary) 
    {    this.salary = salary;  }

    public String toString() 
    {    return " name = " + name + " id = " + id; }
}
```

<br><br>


Problem 2
---------

**What are the advantages and disadvantages of the various collision resolution strategies?**

<br><br>

