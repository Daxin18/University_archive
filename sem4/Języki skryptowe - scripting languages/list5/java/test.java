

public class test
{
    public static void main(String[] args)
    {
        Graph graph = new Graph();
        graph.addNode("N1");
        graph.addNode("N2");
        graph.addNode("N3");
        graph.addNode("N4");
        graph.addNode("N5");

        graph.addEdge(graph.getNode("N1"), graph.getNode("N2"), 20);
        graph.addEdge(graph.getNode("N2"), graph.getNode("N4"), 30);
        graph.addEdge(graph.getNode("N3"), graph.getNode("N4"), 30);
        graph.addEdge(graph.getNode("N1"), graph.getNode("N3"), 15);
        graph.addEdge(graph.getNode("N4"), graph.getNode("N5"), 1);
        graph.addEdge(graph.getNode("N5"), graph.getNode("N4"), 1);
        graph.addEdge(graph.getNode("N5"), graph.getNode("N5"), 1); // cannot add an edge connecting node with itself
        graph.addEdge(graph.getNode("N5"), graph.getNode("N1"), 50);
        graph.addEdge(graph.getNode("N4"), graph.getNode("N2"), 15);

        graph.getGraphInfo();

        graph.DFS(graph.getNode("N5"));
        graph.BFS(graph.getNode("N5"));
    }
}
