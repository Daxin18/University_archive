import subprocess

from py4j.java_gateway import JavaGateway


class Graph:
    def __init__(self):
        subprocess.Popen(['java', 'GraphEntry'])
        self.gateway = JavaGateway()
        self.graphs = {}
        self.currentGraph = None

    def add_graph(self, name):
        self.graphs[name] = self.gateway.entry_point.createGraph()

    def choose_graph(self, name):
        if self.graphs.__contains__(name):
            self.currentGraph = self.graphs[name]
            return True
        return False

    def show_graphs(self):
        print(self.graphs)

    def add_edge(self, node_from, node_to, distance):
        return self.currentGraph.addEdge(self.get_node(node_from), self.get_node(node_to), distance)

    def remove_edge(self, idx):
        return self.currentGraph.removeEdge(idx)

    def change_edge_weight(self, idx, weight):
        return self.currentGraph.changeEdgeWeight(idx, weight)

    def add_node(self, name):
        return self.currentGraph.addNode(name)

    def remove_node(self, name):
        return self.currentGraph.removeNode(name)

    def get_node(self, name):
        return self.currentGraph.getNode(name)

    def change_node_name(self, old, new):
        return self.currentGraph.changeNodeName(old, new)

    def show_current_graph_nodes(self):
        self.currentGraph.showNodes()

    def show_current_graph_edges(self):
        self.currentGraph.showEdges()

    def show_current_graph_info(self):
        self.currentGraph.getGraphInfo()

    def print_current_graph_nodes_DFS(self, start_node):
        self.currentGraph.DFS(start_node)

    def print_current_graph_nodes_BFS(self, start_node):
        self.currentGraph.BFS(start_node)

    def stop(self):
        self.gateway.close()
        self.gateway.shutdown()
