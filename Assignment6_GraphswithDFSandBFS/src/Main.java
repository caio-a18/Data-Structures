import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Graph<Integer, Integer> graph1 = new Graph<>();
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
        System.out.println("Graph 2:");

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
        graph2.printGraph();
        System.out.println("----------------");

    }
}