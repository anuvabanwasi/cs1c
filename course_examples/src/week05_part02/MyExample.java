package week05_part02;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};


public class MyExample
{

   public static void main(String[] args) throws Exception
   {
      int k;
      FHavlTree<Integer> searchTree = new FHavlTree<Integer>();
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();

      searchTree.traverse(intPrinter);

      System.out.println( "\ninitial size: " + searchTree.size() );
      searchTree.insert(50);
      searchTree.insert(20);
      searchTree.insert(30);
      searchTree.insert(70);
      searchTree.insert(10);
      searchTree.insert(60);

      System.out.println( "After populating -- traversal and sizes: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
            + "  height: " + searchTree.showHeight() );

      // test assignment operator
      FHavlTree<Integer> searchTree2 
         = (FHavlTree<Integer>)searchTree.clone();
      System.out.println( "\ntree 2 size: " + searchTree2.size() 
            + "  height: " + searchTree2.showHeight() );

      System.out.println( "\n\nAttempting 100 removals in tree 1: " );
      for (k = 100; k > 0; k--)
      {
         if (searchTree.remove(k))
            System.out.println( "removed " + k );
      }

      System.out.println( "\nsearchTree 1 now:");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
            + "  height: " + searchTree.showHeight() );

      searchTree2.insert(500);
      searchTree2.insert(200);
      searchTree2.insert(300);
      searchTree2.insert(700);
      searchTree2.insert(100);
      searchTree2.insert(600);
      System.out.println( "\nsearchTree2:\n" );
      searchTree2.traverse(intPrinter);
      System.out.println( "\ntree 2 size: " + searchTree2.size() 
            + "  height: " + searchTree2.showHeight() );
   }
}