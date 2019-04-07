package lazyTrees;

import cs1c.Traverser;

/**
 * Functor that implements Traverser interface. Client needs to instantiate an object of PrintObject and pass the object to the traverse method.
 * Function objects are useful for passing around single methods.
 * @author anuva
 *
 * @param <E>
 */
class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};