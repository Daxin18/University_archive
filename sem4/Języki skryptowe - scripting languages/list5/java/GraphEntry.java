import py4j.GatewayServer;

public class GraphEntry
{
    public Graph createGraph()
    {
        return new Graph();
    }

    public static void main(String[] args)
    {
        GatewayServer server = new GatewayServer(new GraphEntry());
        server.start();
    }
}
