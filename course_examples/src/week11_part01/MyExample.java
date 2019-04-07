package week11_part01;

public class MyExample
{
    public static void main(String[] args) throws Exception
    {
        // build graph
        FHgraph<String> myGraph1 = new FHgraph<String>();
        myGraph1.addEdge("v1","v2", 2);    myGraph1.addEdge("v1","v4", 1);
        myGraph1.addEdge("v2","v4", 3);    myGraph1.addEdge("v2","v5", 10);
        myGraph1.addEdge("v3","v1", 4);    myGraph1.addEdge("v3","v6", 5);
        myGraph1.addEdge("v4","v3", 2);    myGraph1.addEdge("v4","v5", 2);
        myGraph1.addEdge("v4","v6", 8);    myGraph1.addEdge("v4","v7", 4);
        myGraph1.addEdge("v5","v7", 6);
        myGraph1.addEdge("v7","v6", 1);

        myGraph1.showAdjTable();

        // dijkstra called from inside
        myGraph1.showDistancesTo("v2");
        System.out.println();

        myGraph1.showShortestPath("v2", "v3");
        myGraph1.showShortestPath("v2", "v6");
        myGraph1.showShortestPath("v2", "v7");
    }
}