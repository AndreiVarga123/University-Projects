import unittest
from Graph import Graph,GraphError

class Tests(unittest.TestCase):
    def test_add_edge(self):
        g=Graph(10)
        g.add_edge(1,2)
        self.assertEqual(g.get_nr_of_edges(),1)
        self.assertRaises(GraphError,lambda:g.add_edge(1,2))

    def test_remove_edge(self):
        g=Graph(10)
        g.add_edge(1,2)
        g.remove_edge(1,2)
        self.assertEqual(g.get_nr_of_edges(), 0)
        self.assertRaises(GraphError, lambda: g.remove_edge(1, 2))

    def test_add_vertex(self):
        g=Graph(10)
        g.add_vertex(11)
        self.assertEqual(g.get_nr_of_vertices(),11)
        self.assertRaises(GraphError,lambda:g.add_vertex(11))

    def test_remove_vertex(self):
        g=Graph(10)
        g.add_vertex(11)
        g.add_edge(11, 5)
        g.add_edge(5, 11)
        g.remove_vertex(11)
        self.assertEqual(g.get_nr_of_vertices(),10)
        self.assertRaises(GraphError,lambda: g.remove_vertex(11))

    def test_parse_vertices(self):
        g=Graph()
        g.add_vertex(1)
        g.add_vertex(3)
        g.add_vertex(5)
        test_list=list(g.parse_vertices())
        self.assertEqual([1,3,5],test_list)

    def test_parse_nout(self):
        g=Graph(10)
        g.add_edge(1,3)
        g.add_edge(1, 5)
        g.add_edge(1, 7)
        test_list=list(g.parse_nout(1))
        self.assertEqual([3,5,7],test_list)
        try:
            test_list = list(g.parse_nout(11))
            self.assertEqual(1, 0)
        except GraphError:
            pass

    def test_parse_nin(self):
        g=Graph(10)
        g.add_edge(3, 1)
        g.add_edge(5, 1)
        g.add_edge(7, 1)
        test_list=list(g.parse_nin(1))
        self.assertEqual([3,5,7],test_list)
        try:
            test_list=list(g.parse_nin(11))
            self.assertEqual(1,0)
        except GraphError:
            pass

    def test_in_degree(self):
        g=Graph(10)
        g.add_edge(1,5)
        g.add_edge(1,6)
        self.assertEqual(g.out_degree(1),2)
        self.assertRaises(GraphError,lambda:g.out_degree(11))

    def test_out_degree(self):
        g=Graph(10)
        g.add_edge(5,1)
        g.add_edge(6,1)
        self.assertEqual(g.in_degree(1),2)
        self.assertRaises(GraphError,lambda :g.get_edge_cost(1,2))
        self.assertRaises(GraphError,lambda: g.in_degree(11))

    def test_get_set_cost(self):
        g=Graph(10)
        g.add_edge(1,5)
        g.set_edge_cost(1,5,10)
        self.assertEqual(g.get_edge_cost(1,5),10)
        self.assertRaises(GraphError, lambda: g.set_edge_cost(1, 2,20))

    def test_copy(self):
        g1=Graph(10)
        g1.add_edge(5, 1)
        g1.add_edge(6, 1)
        g2=g1.__copy__()
        g2.remove_vertex(1)
        self.assertEqual(g1.get_nr_of_edges(),2)
        self.assertEqual(g2.get_nr_of_edges(),0)

