package week04_part02;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class TestFHTree
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      FHtree<String> sceneTree = new FHtree<String>(), sceneTree2;
      FHtreeNode<String> tn;
      PrintObject<String> g = new PrintObject<String>();
      
      // create a scene in a room
      tn = sceneTree.addChild(null, "room");
      
      // bm start
      tn = sceneTree.addChild(null, "alice");
      sceneTree.traverse(g);
      // bm end
      
      // ... code deleted - same as previous program ...

      // test cloning
      sceneTree2 = (FHtree<String>)sceneTree.clone();

      // remove something from the first tree
      sceneTree.remove("spare mutant paw");
      sceneTree.remove("right hand");
     
      // add some things to the second tree
      tn = sceneTree2.find("right hand");
      sceneTree2.addChild(tn, "6TH FINGER");
      sceneTree2.addChild(tn, "7TH FINGER");

      System.out.println("\n----- Original: should have removed items -----");
      sceneTree.traverse(g);
      System.out.println("\n\n----- Clone: should have extra items -----");
      sceneTree2.traverse(g);
   }
}

/* ------------------ RUN -------------

----- Original: should have removed items -----
room table south west leg south east leg north west leg north east leg Miguel th
e human torso right arm left arm left hand pinky ring finger middle finger index
 finger thumb Lily the canine torso wagging tail right left paw right rear paw l
eft front paw right front paw 

----- Clone: should have extra items -----
room table south west leg south east leg north west leg north east leg Miguel th
e human torso right arm right hand 7TH FINGER 6TH FINGER pinky ring finger mid
dle finger index finger thumb left arm left hand pinky ring finger middle finger
 index finger thumb Lily the canine torso wagging tail spare mutant paw right le
ft paw right rear paw left front paw right front paw 
-------------------------------------------- */