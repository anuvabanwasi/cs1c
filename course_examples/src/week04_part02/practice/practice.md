Week 4 Part 2 Practice Problems
========================

Learning Objectives
-------------------
- Removing a Node from a General Tree

<hr>


**Recall the following general tree where an object of type `FHtreeNode` may have at most one `chld`, one `prev` and one `sib` reference:**

```java
public class FHtreeNode<E>
{
   protected FHtreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHtreeNode<E> myRoot;  // needed to test for certain error

   public FHtreeNode( E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv )
   {
      firstChild = chld; 
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
   }
   
   // ...
}
```

<br>

**Given the folloiwng `removeNode()` method from class `FHtreeNode`:**
```java
void removeNode(FHtreeNode<E> nodeToDelete )
{
    if (nodeToDelete == null || mRoot == null)
        return;
    if (nodeToDelete.myRoot != mRoot)
        return;  // silent error, node does not belong to this tree

    // remove all the children of this node 
    /* (not needed due to Java GC, but how done in other languages)
     * while (nodeToDelete.firstChild != null)
     *    removeNode(nodeToDelete.firstChild);  
     */

    if (nodeToDelete.prev == null)
        mRoot = null;     // last node in tree
    else if (nodeToDelete.prev.sib == nodeToDelete)
        nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
    else
        nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

    // adjust the successor sib's prev pointer
    if (nodeToDelete.sib != null)
        nodeToDelete.sib.prev = nodeToDelete.prev;
}
```

<br>

**Use the following general tree to answer problems 1 through 4.**

<p>general tree:
        <img alt="" src="pix_generalTree_list_rep_wPrev.png" style="width: 800px; display: block; margin-left: auto; margin-right: auto;" /></p>
        

<br><br>

Problem 1
---------
**What does the tree look like after removing node `N`?**

- Redraw the tree after removing node N.
- List the nodes that are garbage collected (GC-ed).

<p><img alt="" src="pix_generalTree_list_remove_N.png" style="width: 391px; display: block; margin-left: auto; margin-right: auto;" /></p>
        
<br><br>



<br><br>

Problem 2
---------
**What does the tree look like after removing node `K`?**

- Redraw the tree after removing node K.
- List the nodes that are garbage collected (GC-ed).

<p><img alt="" src="pix_generalTree_list_remove_K.png" style="width: 443px; display: block; margin-left: auto; margin-right: auto;" /></p>
        
<br><br>



<br><br>

Problem 3
---------
**What does the tree look like after removing node `M`?**

- Redraw the tree after removing node M.
- List the nodes that are garbage collected (GC-ed).

<p><img alt="" src="pix_generalTree_list_remove_M.png" style="width: 391px; display: block; margin-left: auto; margin-right: auto;" /></p>
        
<br><br>



<br><br>


Problem 4
---------
**What does the tree look like after removing node `L`?**

- Redraw the tree after removing node L.
- List the nodes that are garbage collected (GC-ed).

<p><img alt="" src="pix_generalTree_list_remove_L.png" style="width: 391px; display: block; margin-left: auto; margin-right: auto;" /></p>
        
<br><br>


<br><br>


Problem 5
---------
**What does the tree look like after removing node `F`?**

- Redraw the tree after removing node F.
- List the nodes that are garbage collected (GC-ed).

<p><img alt="" src="pix_generalTree_list_remove_F.png" style="width: 600px; display: block; margin-left: auto; margin-right: auto;" /></p>
        
<br><br>




