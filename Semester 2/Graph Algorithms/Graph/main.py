import random

from Graph import Graph, GraphError,UndirectedGraph


def build_graph_from_file(filename):
    try:
        file = open(filename, "rt")
        line = file.readline()
        n, m = line.split(maxsplit=1, sep=" ")
        g = Graph(int(n))
        for i in range(int(m)):
            line = file.readline()
            x, y, cost = line.split(maxsplit=2, sep=" ")
            g.add_edge(int(x), int(y), int(cost))
        file.close()
        return g
    except FileNotFoundError as fne:
        print(fne)

def build_undirected_graph_from_file(filename):
    try:
        file = open(filename, "rt")
        line = file.readline()
        n, m = line.split(maxsplit=1, sep=" ")
        g = UndirectedGraph(int(n))
        for i in range(int(m)):
            line = file.readline()
            x, y, cost = line.split(maxsplit=2, sep=" ")
            g.add_edge(int(x), int(y), int(cost))
        file.close()
        return g
    except FileNotFoundError as fne:
        print(fne)



def write_graph_to_file(g, filename):
    file = open(filename, "wt")
    file.write(str(g.get_nr_of_vertices()) + " " + str(g.get_nr_of_edges()) + "\n")
    for key in g.parse_edges():
        file.write(str(key[0]) + " " + str(key[1]) + " " + str(key[2]) + "\n")
    file.close()


def build_random_graph(n, m):
    if n * n < m:
        print("Graph with " + str(n) + " vertices and " + str(m) + " edges can not be built")
        return Graph()
    else:
        g = Graph(n)
        for i in range(m):
            v1 = random.randrange(n)
            v2 = random.randrange(n)
            while g.is_edge(v1, v2):
                v1 = random.randrange(n)
                v2 = random.randrange(n)
            g.add_edge(v1, v2, random.randrange(1000))
        return g


def print_out_edges(g):
    for vertex in g.parse_vertices():
        print(str(vertex) + ": ", end="")
        for y in g.parse_nout(vertex):
            print(str(y) + " ", end="")
        print("")


def print_menu():
    print("\n")
    print("1. Print vertices")
    print("2. Print edges")
    print("3. Add vertex")
    print("4. Remove vertex")
    print("5. Add edge")
    print("6. Remove edge")
    print("7. Print number of vertices")
    print("8. Print number of edges")
    print("9. Print in degree of vertex")
    print("10. Print out degree of vertex")
    print("11. Check if vertex is in graph")
    print("12. Check if edge is in graph")
    print("13. Get cost of edge")
    print("14. Set cost of edge")
    print("15. Print out edges of a vertex")
    print("16. Print in edges of a vertex")
    print("17. Read directed graph from file")
    print("18. Write graph to file")
    print("19. Generate random directed graphs")
    print("20. Read undirected graph from file")
    print("21. Print connected components of an undirected graph")
    print("22. Shortest walk using Flyod-Warshall")
    print("23. Topologically sort the graph")
    print("24. Highest cost path in a DAG")
    print("25 2-Approximation vertex cover")
    print("0. Exit")


def main():
    g = None
    ug=None
    while g is None:
        filename = input("File name for directed graph: ")
        g = build_graph_from_file(filename)
    while True:
        print_menu()
        option = input("Option: ")
        try:
            if option == "1":
                for vertex in g.parse_vertices():
                    print(str(vertex) + " ", end="")
            elif option == "2":
                for edge in g.parse_edges():
                    print("edge: [" + str(edge[0]) + "," + str(edge[1]) + "]" + ", cost: " + str(edge[2]) + "  ",
                          end="")
            elif option == "3":
                x = input("Input vertex:")
                g.add_vertex(int(x))
                print("Vertex added")
            elif option == "4":
                x = input("Input vertex:")
                g.remove_vertex(int(x))
                print("Vertex removed")
            elif option == "5":
                x = input("Input source:")
                y = input("Input target:")
                c = input("Input cost:")
                g.add_edge(int(x), int(y), int(c))
                print("Edge added")
            elif option == "6":
                x = input("Input source:")
                y = input("Input target:")
                g.remove_edge(int(x), int(y))
                print("Edge removed")
            elif option == "7":
                print(str(g.get_nr_of_vertices()))
            elif option == "8":
                print(str(g.get_nr_of_edges()))
            elif option == "9":
                x = input("Input vertex:")
                print(str(g.in_degree(int(x))))
            elif option == "10":
                x = input("Input vertex:")
                print(str(g.out_degree(int(x))))
            elif option == "11":
                x = input("Input vertex:")
                print(str(g.is_vertex(int(x))))
            elif option == "12":
                x = input("Input source:")
                y = input("Input target:")
                print(str(g.is_edge(int(x), int(y))))
            elif option == "13":
                x = input("Input source:")
                y = input("Input target:")
                print("Cost: " + str(g.get_edge_cost(int(x), int(y))))
            elif option == "14":
                x = input("Input source:")
                y = input("Input target:")
                c = input("Input cost:")
                g.set_edge_cost(int(x), int(y), int(c))
                print("Cost changed")
            elif option == "15":
                x = input("Input vertex:")
                for vertex in g.parse_nout(int(x)):
                    print(str(vertex) + " ", end="")
            elif option == "16":
                x = input("Input vertex:")
                for vertex in g.parse_nin(int(x)):
                    print(str(vertex) + " ", end="")
            elif option == "17":
                g = None
                while g is None:
                    filename = input("File name: ")
                    g = build_graph_from_file(filename)
                print("Graph was read from file")
            elif option == "18":
                filename = input("File name: ")
                write_graph_to_file(g, filename)
                print("Graph was written to file")
            elif option == "19":
                g1 = build_random_graph(7, 20)
                g2 = build_random_graph(6, 40)
                write_graph_to_file(g1, "random_graph1.txt")
                write_graph_to_file(g2, "random_graph2.txt")
                print("Graphs generated")
            elif option=="20":
                ug = None
                while ug is None:
                    filename = input("File name: ")
                    ug = build_undirected_graph_from_file(filename)
                print("Graph was read from file")
            elif option=="21":
                if ug is None:
                    print("Please read an undirected graph")
                else:
                    for comp in ug.connected_components():
                        print(comp)
            elif option=="22":
                x = input("Input starting vertex:")
                y = input("Input ending vertex:")
                walk,cost=g.shortest_walk(int(x), int(y))
                print("The shortest walk from "+x+" to "+y+" costs "+str(cost)+" and is composed of: "+str(walk))
            elif option=="23":
                sorted_list = g.base_topo_sort()
                if len(sorted_list)==0:
                    print("Current graph is not a DAG")
                else:
                    print("The graph is a DAG and we have the following topological order: "+str(sorted_list))
            elif option=="24":
                vertex_begin = int(input("Enter source vertex = "))
                vertex_end = int(input("Enter end vertex = "))
                if vertex_begin not in g.parse_vertices() or vertex_end not in g.parse_vertices():
                    raise ValueError("One vertex/ both vertices entered might not exist.")
                else:
                    distance, prev = g.highest_cost_path(vertex_begin, vertex_end)
                    if distance == float("-inf"):
                        raise ValueError("There is no walk from {} to {}".format(vertex_begin, vertex_end))
                    path = []
                    vertex = vertex_end
                    path.append(vertex)
                    while prev[vertex] != -1:
                        path.append(prev[vertex])
                        vertex = prev[vertex]
                    print("The cost of the highest cost path is: {}".format(str(distance)))
                    # print the path
                print("The path is: ", end=' ')
                for index in range(len(path) - 1, -1, -1):
                    print(path[index], end=' ')
            elif option == "25":
                cover=ug.vertex_cover()
                string="A vertex cover of this graph is: "
                for vertex in cover:
                    string+=str(vertex)+" "
                print(string)
            elif option == "0":
                return
            else:
                print("Invalid input")
        except (GraphError, ValueError) as msg:
            print(msg)


main()
