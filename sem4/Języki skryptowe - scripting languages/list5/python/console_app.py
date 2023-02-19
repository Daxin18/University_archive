from graph import Graph

running = True
graph_menu = False

JGraph = Graph()

def setup():
    JGraph.add_node("N1")
    JGraph.add_node("N2")
    JGraph.add_node("N3")
    JGraph.add_node("N4")
    JGraph.add_node("N5")
    JGraph.add_edge("N1", "N2", 20)
    JGraph.add_edge("N1", "N3", 15)
    JGraph.add_edge("N3", "N4", 30)
    JGraph.add_edge("N2", "N4", 30)
    JGraph.add_edge("N4", "N5", 1)
    JGraph.add_edge("N5", "N4", 1)
    JGraph.add_edge("N5", "N1", 50)

while running:
    print("----- Menu -----")
    print("0. add graph")
    print("1. print existing graphs")
    print("2. choose graph to edit")
    print("3. exit")

    try:
        choice = int(input("Choose option:"))
    except ValueError:
        print("Choice is not a number!")
        choice = -1

    if choice == 0:
        name = str(input("New graph name:"))
        JGraph.add_graph(name)
        print(f"Graph '{name}'added")
    elif choice == 1:
        JGraph.show_graphs()
    elif choice == 2:
        name = str(input("Graph name:"))
        if JGraph.choose_graph(name):
            graph_menu = True
        else:
            print(f"Graph '{name}' does not exist yet")
    elif choice == 3:
        running = False
        JGraph.stop()
    else:
        print("Invalid choice, try again")

    while graph_menu:
        print("----- Graph management menu -----")
        print("0. add Node")
        print("1. add Edge")
        print("2. remove Node")
        print("3. remove Edge")
        print("4. change Node's name")
        print("5. change Edge's weight")
        print("6. show graph info")
        print("7. DFS")
        print("8. BFS")
        print("9. auto-setup a graph")
        print("10. go back")

        try:
            choice = int(input("Choose option:"))
        except ValueError:
            print("Choice is not a number!")
            choice = -1
        if choice == 0:
            name = str(input("New Node name:"))
            JGraph.add_node(name)
        elif choice == 1:
            nfrom = str(input("Starting node:"))
            nto = str(input("Destination node:"))
            try:
                weight = int(input("Weight:"))
                JGraph.add_edge(nfrom, nto, weight)
            except ValueError:
                print("Invalid input")
        elif choice == 2:
            name = str(input("Node to be removed name:"))
            if JGraph.remove_node(name):
                print(f"Node '{name}' removed")
            else:
                print(f"Couldn't remove node '{name}'")
        elif choice == 3:
            try:
                idx = int(input("Edge index:"))
                if JGraph.remove_edge(idx):
                    print(f"Edge '{idx}' removed")
                else:
                    print(f"Couldn't remove edge '{idx}'")
            except ValueError:
                print("Invalid input")
        elif choice == 4:
            oname = str(input("Old Node name:"))
            nname = str(input("New Node name:"))
            if JGraph.change_node_name(oname, nname):
                print(f"Name of the node '{oname}'changed to '{nname}'")
            else:
                print(f"Couldn't change the name of the node")
        elif choice == 5:
            try:
                idx = int(input("Edge index:"))
                weight = int(input("New Edge weight:"))
                if JGraph.change_edge_weight(idx, weight):
                    print(f"Changed the weight of the edge {idx} to {weight}")
                else:
                    print(f"Couldn't change the weight of the edge")
            except ValueError:
                print("Invalid input")
        elif choice == 6:
            JGraph.show_current_graph_info()
        elif choice == 7:
            node = str(input("Starting Node name:"))
            start = JGraph.get_node(node)
            if start is not None:
                JGraph.print_current_graph_nodes_DFS(start)
            else:
                print(f"Invalid starting node '{node}'")
        elif choice == 8:
            node = str(input("Starting Node name:"))
            start = JGraph.get_node(node)
            if start is not None:
                JGraph.print_current_graph_nodes_BFS(start)
            else:
                print(f"Invalid starting node '{node}'")
        elif choice == 9:
            setup()
        elif choice == 10:
            graph_menu = False
        else:
            print("Invalid choice, try again")

