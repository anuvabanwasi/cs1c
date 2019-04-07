package cs1c;

/**
 * General interface to traverse the tree to compute something.Clients will implement the Traverser interface.
 * The visit step does the actual processing on the current node.
 * @author anuva
 *
 * @param <E>
 */
public interface Traverser<E>
{
   public void visit(E x);
}
