package week06_part02;

/**
 * Basic Employee class
 */
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
