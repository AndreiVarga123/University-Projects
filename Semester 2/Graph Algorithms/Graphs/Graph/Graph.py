import copy
from queue import Queue


class Graph:
    def __init__(self, n=0):
        self.__out_vertices = dict()
        self.__in_vertices = dict()
        self.__cost = dict()
        for i in range(0, n):
            self.__out_vertices[i] = list()
            self.__in_vertices[i] = list()

    def get_nr_of_vertices(self):
        return len(self.__out_vertices)

    def get_nr_of_edges(self):
        return len(self.__cost)

    def parse_vertices(self):
        for vertex in self.__out_vertices:
            yield vertex

    def is_vertex(self, x):
        return x in self.__out_vertices

    def is_edge(self, x, y):
        return y in self.__out_vertices[x]

    def in_degree(self, x):
        if not self.is_vertex(x):
            raise GraphError("Vertex doesn't exist")
        return len(self.__in_vertices[x])

    def out_degree(self, x):
        if not self.is_vertex(x):
            raise GraphError("Vertex doesn't exist")
        return len(self.__out_vertices[x])

    def parse_nout(self, x):
        if not self.is_vertex(x):
            raise GraphError("Vertex doesn't exist")
        for y in self.__out_vertices[x]:
            yield y

    def parse_nin(self, x):
        if not self.is_vertex(x):
            raise GraphError("Vertex doesn't exist")
        for y in self.__in_vertices[x]:
            yield y

    def parse_edges(self):
        for key in self.__cost:
            yield key[0], key[1], self.__cost[key]

    def add_edge(self, x, y, cost=0):
        if self.is_edge(x, y):
            raise GraphError("Edge already exists")
        self.__out_vertices[x].append(y)
        self.__in_vertices[y].append(x)
        self.__cost[(x, y)] = cost

    def remove_edge(self, x, y):
        if not self.is_edge(x, y):
            raise GraphError("Edge doesn't exist")
        self.__out_vertices[x].remove(y)
        self.__in_vertices[y].remove(x)
        self.__cost.pop((x, y))

    def get_edge_cost(self, x, y):
        if not self.is_edge(x, y):
            raise GraphError("Edge doesn't exist")
        return self.__cost[(x, y)]

    def set_edge_cost(self, x, y, cost):
        if not self.is_edge(x, y):
            raise GraphError("Edge doesn't exist")
        self.__cost[(x, y)] = cost

    def add_vertex(self, x):
        if self.is_vertex(x):
            raise GraphError("Vertex already exists")
        self.__out_vertices[x] = list()
        self.__in_vertices[x] = list()

    def remove_vertex(self, x):
        if not self.is_vertex(x):
            raise GraphError("Vertex doesn't exist")
        for i in self.parse_vertices():
            if self.is_edge(x, i):
                self.__in_vertices[i].remove(x)
                self.__cost.pop((x, i))
            if self.is_edge(i, x):
                self.__out_vertices[i].remove(x)
                self.__cost.pop((i, x))
        self.__out_vertices.pop(x)
        self.__in_vertices.pop(x)

    def shortest_walk(self, v1, v2):
        if v1 not in self.parse_vertices() or v2 not in self.parse_vertices():
            raise GraphError("Please input valid vertices")

        inf = float('inf')
        walk = list()
        walk.append(v1)
        walk.append(v2)
        w = dict()

        for vertex1 in self.parse_vertices():
            for vertex2 in self.parse_vertices():
                if vertex1 == vertex2:
                    w[vertex1, vertex2] = 0
                elif self.is_edge(vertex1, vertex2):
                    w[vertex1, vertex2] = self.__cost[vertex1, vertex2]
                else:
                    w[vertex1, vertex2] = inf

        for vertex1 in self.parse_vertices():
            for vertex2 in self.parse_vertices():
                for vertex3 in self.parse_vertices():
                    if w[vertex2, vertex3] > w[vertex2, vertex1] + w[vertex1, vertex3]:
                        w[vertex2, vertex3] = w[vertex2, vertex1] + w[vertex1, vertex3]
                        if (vertex2 in walk) and (vertex3 in walk) and (walk.index(vertex3) == walk.index(vertex2) + 1):
                            walk.insert(walk.index(vertex3), vertex1)

        cost = w[v1, v2]
        return walk, cost

    def topo_sort_dfs(self, vertex, sortedList, fullyProcessed, inProcess):

        inProcess.add(vertex)  # add to the set of processing vertices the current vertex
        for other_vertex in self.__in_vertices[vertex]:  # process all the inbound vertices of the current vertex
            if other_vertex in inProcess:
                return False
            elif other_vertex not in fullyProcessed:  # if one of the inbound vertices of the current one is not processed
                # process it and its inbound vertices
                ok = self.topo_sort_dfs(other_vertex, sortedList, fullyProcessed, inProcess)
                if not ok:  # if we get to a vertex that is in process again we have a loop, so the graph is not a DAG
                    # we return False and the algorithm stops
                    return False
        inProcess.remove(vertex)
        sortedList.append(vertex)  # add the processed vertex to the topological sort
        fullyProcessed.add(vertex)  # add the processed vertex to the set of all the processed vertices
        return True

    def base_topo_sort(self):
        sorted_list = []  # list for the topological sort
        fully_processed = set()  # set for all the processed vertices
        in_process = set()  # set for the vertices that are in process
        for vertex in self.parse_vertices():  # go through all the vertices of the graph
            if vertex not in fully_processed:
                ok = self.topo_sort_dfs(vertex, sorted_list, fully_processed, in_process)
                if not ok:  # the graph is not a DAG so we return an empty list
                    sorted_list.clear()
                    return []
        return sorted_list

    def highest_cost_path(self, vertex_begin, vertex_end):

        topological_order_list = self.base_topo_sort()  # get the topological sort
        if len(topological_order_list) == 0:
            raise GraphError("Graph is not a DAG")
        dist = {}  # dictionary of max costs from vertex begin
        prev = {}  # dictionary that stores for each vertex the previous vertex from the max path
        m_inf = float('-inf')
        for vertex in topological_order_list:  # initialize all the values of the dictionaries
            dist[vertex] = m_inf
            prev[vertex] = -1
        dist[vertex_begin] = 0

        for vertex in topological_order_list:  # go through all the vertices
            if vertex == vertex_end:  # stop the loop if we get to the end vertex
                break
            for other_vertex in self.__out_vertices[vertex]:  # parse the outbound vertices of the current vertex
                if dist[other_vertex] < dist[vertex] + self.__cost[(vertex, other_vertex)]:
                    # if the cost thru the vertex is greater than the current one update the dictionary
                    dist[other_vertex] = dist[vertex] + self.__cost[(vertex, other_vertex)]
                    prev[other_vertex] = vertex

        return dist[vertex_end], prev

    def __copy__(self):
        return copy.deepcopy(self)


class UndirectedGraph:
    def __init__(self, n):
        self._vertices = dict()
        self._edges = dict()
        for i in range(n):
            self._vertices[i] = list()

    def vertices_iterator(self):
        for vertex in self._vertices:
            yield vertex

    def edges_iterator(self):
        for key in self._edges:
            yield key[0], key[1], self._edges[key]

    def neighbours_iterator(self, vertex):
        if not self.is_vertex(vertex):
            raise GraphError("Vertex doesn't exist")
        for v in self._vertices[vertex]:
            yield v

    def is_vertex(self, vertex):
        return vertex in self._vertices

    def is_edge(self, v1, v2):
        return v1 in self._vertices[v2]

    def get_nr_vertices(self):
        return len(self._vertices)

    def get_nr_edges(self):
        return len(self._edges)

    def degree(self, vertex):
        if not self.is_vertex(vertex):
            raise GraphError("Vertex doesn't exist")
        return len(self._vertices[vertex])

    def get_edge_cost(self, v1, v2):
        if not self.is_edge(v1, v2):
            raise GraphError("Edge doesn't exist")
        if self._edges[v1, v2] is not None:
            return self._edges[v1, v2]
        return self._edges[v2, v1]

    def set_edge_cost(self, v1, v2, cost):
        if not self.is_edge(v1, v2):
            raise GraphError("Edge doesn't exist")
        if self._edges[v1, v2] is not None:
            self._edges[v1, v2] = cost
        else:
            self._edges[v2, v1] = cost

    def add_vertex(self, vertex):
        if self.is_vertex(vertex):
            raise GraphError("Vertex already exists")
        self._vertices[vertex] = list()

    def add_edge(self, v1, v2, cost=0):
        if self.is_edge(v1, v2):
            raise GraphError("Edge already exists")
        self._vertices[v1].append(v2)
        self._vertices[v2].append(v1)
        if v1 > v2:
            aux = v2
            v2 = v1
            v1 = aux
        self._edges[v1, v2] = cost

    def remove_vertex(self, vertex):
        if not self.is_vertex(vertex):
            raise GraphError("Vertex doesn't exist")
        self._vertices.pop(vertex)
        for v in self._vertices:
            if self.is_edge(v, vertex):
                self._vertices[v].remove(vertex)
                self._edges.pop(v, vertex)
            if self.is_edge(vertex, v):
                self._vertices[v].remove(vertex)
                self._edges.pop(vertex, v)

    def remove_edge(self, v1, v2):
        if not self.is_edge(v1, v2):
            raise GraphError("Edge doesn't exist")
        self._edges.pop(v1, v2)
        self._vertices[v1].remove(v2)
        self._vertices[v2].remove(v1)

    def connected_components(self):
        met = set()
        comps = list()
        for vertex in self._vertices:
            if vertex not in met:
                q = Queue()
                q.put(vertex)
                met.add(vertex)
                comps.append([vertex])
                while not q.empty():
                    node = q.get()
                    for neighbour in self._vertices[node]:
                        if neighbour not in met:
                            met.add(neighbour)
                            q.put(neighbour)
                            comps[-1].append(neighbour)
        return comps

    def vertex_cover(self):
        vertex_cover = set()
        covered_edges = set()
        for edge in self._edges.keys():
            if edge not in covered_edges:
                vertex_cover.add(edge[0])
                vertex_cover.add(edge[1])
                for vertex in self._vertices[edge[0]]:
                    if edge[0] < vertex:
                        covered_edges.add((edge[0], vertex))
                    else:
                        covered_edges.add((vertex, edge[0]))
                for vertex in self._vertices[edge[1]]:
                    if edge[1] < vertex:
                        covered_edges.add((edge[1], vertex))
                    else:
                        covered_edges.add((vertex, edge[1]))

        return vertex_cover

    def __copy__(self):
        return copy.deepcopy(self)


class GraphError(Exception):
    def __init__(self, message=""):
        super().__init__(message)
