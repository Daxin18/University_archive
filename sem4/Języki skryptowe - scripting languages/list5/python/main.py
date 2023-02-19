from graph import Graph

JGraph = Graph()
JGraph.add_graph("test_graph")
JGraph.choose_graph("test_graph")
JGraph.add_node("N1")
JGraph.add_node("N2")
JGraph.add_edge("N1", "N2", 20)
JGraph.show_current_graph_info()

JGraph.change_node_name("N1", "N3")
JGraph.show_current_graph_info()

JGraph.add_node("N4")
JGraph.add_edge("N4", "N3", 20)

JGraph.show_current_graph_info()
JGraph.remove_node("N4")

JGraph.show_current_graph_info()

JGraph.change_node_name("N3", "N1")
JGraph.add_node("N3")
JGraph.add_node("N4")
JGraph.add_node("N5")
JGraph.add_edge("N1", "N3", 15)
JGraph.add_edge("N3", "N4", 30)
JGraph.add_edge("N2", "N4", 30)
JGraph.add_edge("N4", "N5", 1)
JGraph.add_edge("N5", "N4", 1)
JGraph.add_edge("N5", "N1", 50)

JGraph.show_current_graph_info()
JGraph.print_current_graph_nodes_DFS(JGraph.get_node("N5"))
JGraph.print_current_graph_nodes_BFS(JGraph.get_node("N5"))

JGraph.add_graph("second_one")
JGraph.show_graphs()

JGraph.stop()
