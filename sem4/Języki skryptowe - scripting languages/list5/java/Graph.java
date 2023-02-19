import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph
{
    private class Edge
    {
        Node from;
        Node to;
        int weight;

        Edge(Node from, Node to, int dist)
        {
            this.from = from;
            this.to = to;
            this.weight = dist;
        }

        @Override
        public String toString()
        {
            return ((from.toString()) + " --> " + (to.toString()) + " [" + weight + "]");
        }
        public void setWeight(int i)
        {
            this.weight = i;
        }
    }
    private class Node implements Comparable<Node>
    {
        String name;

        Node(String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return this.name;
        }
        @Override
        public int compareTo(Node n)
        {
            return this.name.compareTo(n.name);
        }
    }

    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;

    public Graph()
    {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public int addNode(String name)
    {
        boolean unique = true;
        for(Node node : nodes)
            if(node.toString().equals(name))
            {
                unique = false;
                break;
            }
        if(unique)
        {
            Node tmp = new Node(name);
            nodes.add(tmp);
            return nodes.indexOf(tmp);
        }
        else return -1;
    }
    public int addEdge(Node from, Node to, int weight)
    {
        if (from == null || to == null || from.equals(to))
            return -1;
        for(Edge e : edges)
            if (e.from.compareTo(from) == 0 && e.to.compareTo(to) == 0)
                return -1;
        Edge tmp = new Edge(from, to, weight);
        edges.add(tmp);
        return edges.indexOf(tmp);
    }
    public boolean removeEdge(int idx)
    {
       if(edges.remove(idx) == null)
           return false;
       else
           return true;
    }
    public boolean removeNode(String name)
    {
        for(Node node : nodes)
            if(node.toString().equals(name))
            {
                nodes.remove(node);
                edges.removeIf(edge -> (edge.from.equals(node) || edge.to.equals(node)));
                return true;
            }
        return false;
    }

    public boolean changeNodeName(String oldName, String newName)
    {
        Node temp = getNode(oldName);
        if(temp != null)
        {
            temp.name = newName;
            return true;
        }
        return false;
    }
    public boolean changeEdgeWeight(int idx, int weight)
    {
        try
        {
            getEdge(idx).weight = weight;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }


    public Node getNode(String name)
    {
        for(int i=0; i<nodes.size(); i++)
            if(nodes.get(i).toString().equals(name))
                return nodes.get(i);
        return null;
    }
    public Edge getEdge(int idx)
    {
        return edges.get(idx);
    }

    public void showNodes()
    {
        System.out.println("----- nodes -----");
        for(Node node : nodes)
            System.out.println(node.toString());
    }
    public void showEdges()
    {
        System.out.println("----- edges -----");
        System.out.println("idx. from --> to [weight]");
        for(int i = 0; i < edges.size(); i++)
            System.out.println(i + ".\t" + edges.get(i).toString());
        System.out.println("-----------------");
    }
    public void getGraphInfo()
    {
        showNodes();
        showEdges();
    }

    public void DFS(Node start_node)
    {
        System.out.println("Nodes visited in DFS");
        Stack stack = new Stack();
        boolean[] visited = new boolean[nodes.size()];

        stack.push(start_node);
        for(boolean b : visited)
            b = false;

        while(!stack.isEmpty())
            DFS_visit((Node)stack.pop(), stack, visited);

    }
    private void DFS_visit(Node node, Stack stack, boolean[] visited)
    {
        if(!visited[nodes.indexOf(node)])
        {
            System.out.println(node.toString());
            //all the stuff we wanna do with the visited node happens here
        }
        visited[nodes.indexOf(node)] = true;
        for(Edge e : edges)
            if(e.from.equals(node) && !visited[nodes.indexOf(e.to)])
                stack.push(e.to);
    }

    public void BFS(Node start_node)
    {
        System.out.println("Nodes visited in BFS");
        Queue<Node> queue = new LinkedList<Node>();
        boolean[] visited = new boolean[nodes.size()];

        queue.add(start_node);
        for(boolean b : visited)
            b = false;

        while(!queue.isEmpty())
            BFS_visit(queue.poll(), queue, visited);

    }
    private void BFS_visit(Node node, Queue queue, boolean[] visited)
    {
        if(!visited[nodes.indexOf(node)])
        {
            System.out.println(node.toString());
            //all the stuff we wanna do with the visited node happens here
        }
        visited[nodes.indexOf(node)] = true;
        for(Edge e : edges)
            if(e.from.equals(node) && !visited[nodes.indexOf(e.to)] && !queue.contains(e.to))
                queue.add(e.to);
    }
}
