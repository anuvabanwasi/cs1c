package week05_part01;

public class FHs_treeNode<E extends Comparable< ? super E > >
{
   // use public access so the tree or other classes can access members 
   public FHs_treeNode<E> lftChild, rtChild;
   public E data;
   public FHs_treeNode<E> myRoot;  // needed to test for certain error

   public FHs_treeNode( E d, FHs_treeNode<E> lft, FHs_treeNode<E> rt )
   {
      lftChild = lft; 
      rtChild = rt;
      data = d;
   }
   
   public FHs_treeNode()
   {
      this(null, null, null);
   }
}
