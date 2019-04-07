package week01_part02;

class MyInt implements Comparable<MyInt>
{
   public int the_int;
   
   public MyInt(int n) 
   { the_int = n; }
   
   
   public int compareTo(MyInt other)
   {
      return (the_int - other.the_int);
   }
}
