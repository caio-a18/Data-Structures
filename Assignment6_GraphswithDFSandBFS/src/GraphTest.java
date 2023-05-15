import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * {@author Caio Albuquerque}
 *
 * the GraphTest class
 */
class GraphTest {

    Graph<Integer, Integer> graph1 = new Graph<Integer, Integer>();

    @org.junit.jupiter.api.Test
    void addNode() {
        assertTrue(graph1.addNode(1,1));
        assertTrue(graph1.addNode(2, 2));
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        assertFalse(graph1.addNode(1, 17));
    }

    @org.junit.jupiter.api.Test
    void addNodes() {
        Integer[] arr1 = new Integer[]{3, 4, 5};
        assertTrue(graph1.addNodes(arr1, arr1));
        graph1.addNodes(arr1, arr1);
        Integer[] arr2 = new Integer[]{1, 2, 3};
        assertFalse(graph1.addNodes(arr2, arr2));
        Integer[] arr3 = new Integer[]{10, 11, 12, 13, 14, 15};
        assertThrows(IllegalArgumentException.class, () -> graph1.addNodes(arr2, arr3));
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        assertFalse(graph1.addEdge(1, 2));
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        assertTrue(graph1.addEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdges() {
        Integer[] arr2 = new Integer[]{2, 3, 4};
        assertFalse(graph1.addEdges(5, arr2));
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        graph1.addNode(3, 3);
        graph1.addNode(4, 4);
        graph1.addNode(5, 5);
        assertTrue(graph1.addEdges(5, arr2));
    }

    @org.junit.jupiter.api.Test
    void removeNode() {
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        graph1.addNode(3, 3);
        graph1.addNode(4, 4);
        graph1.addNode(5, 5);
        assertTrue(graph1.removeNode(3));
    }

    @org.junit.jupiter.api.Test
    void removeNodes() {
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        graph1.addNode(3, 3);
        graph1.addNode(4, 4);
        graph1.addNode(5, 5);
        Integer[] arr3 = new Integer[]{1, 4};
        assertTrue(graph1.removeNodes(arr3));
        Integer[] arr4 = new Integer[]{99, 43, 21312};
        assertFalse(graph1.removeNodes(arr4));
    }

    /**
     * Tested with Main class
     */
    @org.junit.jupiter.api.Test
    void printGraph() {
        graph1.addNode(1, 1);
        graph1.addNode(2, 2);
        graph1.printGraph();
        System.out.println("----------------");

        Integer[] arr1 = new Integer[]{3, 4, 5};
        graph1.addNodes(arr1, arr1);
        graph1.printGraph();
        System.out.println("----------------");

        graph1.addEdge(1, 2);
        graph1.printGraph();
        System.out.println("----------------");

        Integer[] arr2 = new Integer[]{2, 3, 4};
        graph1.addEdges(5, arr2);
        graph1.printGraph();
        System.out.println("----------------");

        graph1.removeNode(3);
        graph1.printGraph();
        System.out.println("----------------");

        Integer[] arr3 = new Integer[]{1, 4};
        graph1.removeNodes(arr3);
        graph1.printGraph();
        System.out.println("----------------");

        graph1.addNode(2, 2);
        graph1.printGraph();
        System.out.println("----------------");
    }

    @org.junit.jupiter.api.Test
    void read() throws FileNotFoundException {
        Graph<String, String> graph3 =
                Graph.read("C:\\Programming Projects\\CSDS 233\\Assignment6\\src\\test.txt.txt");
        graph3.printGraph();
    }

    @org.junit.jupiter.api.Test
    void DFS() {
        Graph<Integer, Integer> graph2 = new Graph<>();
        Integer[] arr8 = new Integer[]{1, 2, 3, 4, 5};
        graph2.addNodes(arr8, arr8);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 4);
        graph2.addEdge(2, 5);
        graph2.addEdge(1, 5);
        graph2.addEdge(2, 3);
        graph2.addNode(6, 6);
        graph2.addNode(7, 7);
        graph2.addEdge(1, 6);
        Integer[] arr1 = new Integer[]{5, 2, 1, 3, 4, 6};
        assertEquals(arr1, graph2.DFS(5, 6));
        Integer[] arr2 = new Integer[]{3, 1, 4};
        assertEquals(arr2, graph2.DFS(3, 4));
    };

    @org.junit.jupiter.api.Test
    void BFS() {
        Graph<Integer, Integer> graph2 = new Graph<>();
        Integer[] arr8 = new Integer[]{1, 2, 3, 4, 5};
        graph2.addNodes(arr8, arr8);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 4);
        graph2.addEdge(2, 5);
        graph2.addEdge(1, 5);
        graph2.addEdge(2, 3);
        graph2.addNode(6, 6);
        graph2.addNode(7, 7);
        graph2.addEdge(1, 6);
        Integer[] arr1 = new Integer[]{5, 2, 1, 3, 6};
        assertEquals(arr1, graph2.BFS(5, 6));
        Integer[] arr2 = new Integer[]{3, 1, 4};
        assertEquals(arr2, graph2.BFS(3, 4));
    }
}