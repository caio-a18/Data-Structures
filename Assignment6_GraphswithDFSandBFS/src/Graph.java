import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/**
 * {@author Caio Albuquerque}
 *
 * the Graph<K, V> class
 */

public class Graph<K, V> {
    /*
    Private field for adjacencyList
     */
    private ArrayList<LinkedList<Node>> adjacencyList = new ArrayList<>();

    /**
     * Adds a node to the graph and checks for duplicates
     * @param name K
     * @param data V
     * @return boolean
     */
    public boolean addNode(K name, V data) {
        /*
        Sets node to a new Node with name and data
         */
        Node<K,V> n = new Node<>(name, data);
        /*
        If adjacencyList already has n.getName(), then it cannot be added --> return false
         */
        for (LinkedList<Node> node : adjacencyList) {
            if (node.get(0).getName().equals(name)) {
                return false;
            }
        }
        /*
        Creates a new LinkedList
         */
        LinkedList<Node> l = new LinkedList<>();
        l.add(n);
        /*
        Returns a boolean whether n was successfully added or not
         */
        return adjacencyList.add(l);
    }

    /**
     * Adds a list of nodes to the graph
     * @param names K[]
     * @param data V[]
     * @return boolean
     */
    public boolean addNodes(K[] names, V[] data) {
        /*
        If the given arrays do not have the same length, IllegalArgumentException is thrown
         */
        if (names.length != data.length) {
            throw new IllegalArgumentException();
        }
        /*
        For loop that parses through names and data
        If the node at the current index cannot be added calling addNode --> returns false
         */
        for (int i = 0; i < names.length; i++) {
            if (!addNode(names[i], data[i])) {
                return false;
            }
        }
        /*
        Returns true if every node was successfully added
         */
        return true;
    }

    /**
     * Adds an undirected edge from node from to node to
     * @param from K
     * @param to K
     * @return boolean
     */
    public boolean addEdge(K from, K to) {
        /*
        Sets fromNode to the node found from 'from'
        Sets toNode to the node found from 'to'
         */
        Node<K, V> fromNode = getNode(from);
        Node<K, V> toNode = getNode(to);
        /*
        If either one is false, returns false
         */
        if (fromNode == null || toNode == null) {
            return false;
        }
        /*
        Creates fromL to check for fromL
        Creates toL to check for toL
         */
        LinkedList<Node> fromL = new LinkedList<>();
        LinkedList<Node> toL = new LinkedList<>();
        /*
        For each that parses through every linked list in adjacency list
        If the current linked list has fromNode, sets fromL to n
         */
        for (LinkedList<Node> n : adjacencyList) {
            if (n.contains(fromNode)) {
                fromL = n;
            }
        }
        /*
        For each that parses through every linked list in adjacency list
        If the current linked list has fromNode, sets toL to x
         */
        for (LinkedList<Node> x : adjacencyList) {
            if (x.contains(toNode)) {
                toL = x;
            }
        }
        /*
        Adds toNode to fromNode
        Adds fromNode to toNode
        Returns true
         */
        fromL.add(toNode);
        toL.add(fromNode);
        return true;
    }

    /**
     * Adds an undirected edge from node from to each node in toList
     * @param from K
     * @param toList K...
     * @return boolean
     */
    public boolean addEdges(K from, K... toList) {
        /*
        For loop that parses through toList
        If an edge cannot be added --> returns false
         */
        boolean check = true;
        for (K k : toList) {
            check &= addEdge(from, k);
        }
        /*
        Returns true
         */
        return check;
    }

    /**
     * Removes a node from the graph along with all connected edges
     * @param name K
     * @return boolean
     */
    public boolean removeNode(K name) {
        /*
        Sets temp to the getNode(name)
         */
        Node<K, V> temp = getNode(name);
        /*
        If n1 is null, it cannot be removed --> returns false
         */
        if (temp == null) {
            return false;
        }
        LinkedList<Node> l = new LinkedList<>();
        LinkedList<Node> listOfName = new LinkedList<>();
        /*
        Parses through every LinkedList<Node> in adjacencyList
         */
        for (int i = 0; i < adjacencyList.size(); i++) {
            /*
            l is set to the current linked list
             */
            l = adjacencyList.get(i);
            /*
            If the first element of the linked list is temp, listOfName equals l
             */
            if (l.get(0).equals(temp)) {
                listOfName = l;
            }
            /*
            Parses through every element, if l is not listOfName, removes every value that is equal to temp
             */
            for (int j = 0; j < l.size(); j++) {
                if (l != listOfName) {
                    if (l.get(j).equals(temp)) {
                        l.remove(j);
                    }
                }
            }
        }
        /*
        Returns whether the node was removed or not
         */
        return adjacencyList.remove(listOfName);
    }

    /**
     * Removes each node in nodelist and their edges from the graph
     * @param nodelist K...
     * @return boolean
     */
    public boolean removeNodes(K... nodelist) {
        /*
        For loop that parses through nodeList
         */
        for (K k : nodelist) {
            /*
            If removeNode cannot be removed --> returns false
             */
            if (!removeNode(k)) {
                return false;
            }
        }
        /*
        Returns true if all nodes were successfully removed
         */
        return true;
    }

    /**
     * Prints the graph
     */
    public void printGraph() {
        /*
        Parses through the adjacencyList
         */
        for (LinkedList<Node> l : adjacencyList) {
            /*
            Parses through every node in n
             */
            for (int j = 0; j < l.size(); j++) {
                /*
                Prints the name of every node in n
                Prints every neighbor of the current l
                 */
                System.out.print(" " + l.get(j).getName());
            }
            System.out.println();
        }
    }

    /**
     * Constructs a graph from a text file
     * @param filename --> String
     * @return Graph
     * @param <V> --> Type V
     * @throws FileNotFoundException if no file is found
     */
    public static <V> Graph<String, V> read(String filename) throws FileNotFoundException {
        /*
        Creates private fields
        Sets file to filename
        Sets scanner to file
        Sets an empty StringBuilder
        Sets a Graph with the given parameters <String, V>
         */
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        Graph <String, V> graph = new Graph<>();
        /*
        While the scanner has the next line
         */
        while (scanner.hasNextLine()) {
            /*
            Adds the scanner to the list
             */
            list.add(scanner.nextLine());
        }
            /*
            Parses through every string in the list
             */
            for (String string : list) {
                /*
                Resets the StringBuilder
                 */
                sb = new StringBuilder();
                /*
                Parses through every char in string
                 */
                for (int i = 0; i < string.length(); i++) {
                    /*
                    If the character is not a blank character
                     */
                    if (string.charAt(i) != ' ') {
                        sb.append(string.charAt(i));
                    }
                    /*
                    Breaks the loop if not
                     */
                    else {
                        break;
                    }
                }
                /*
                Adds the node to the graph
                 */
                graph.addNode(sb.toString(), null);
            }
            /*
            Parses through every string in the list again
             */
            for (String s : list) {
                /*
                Creates int j which will check the index at the getName
                Creates a StringBuilder name which parses through the word to get the node.getName()
                 */
                int j = 0;
                StringBuilder name = new StringBuilder();
                /*
                While j has not passed the length of s and the character at j is not a blank character
                 */
                while (j < s.length() && s.charAt(j) != ' ') {
                    /*
                    Appends the character to the name and increments j
                     */
                    name.append(s.charAt(j));
                    j++;
                }
                /*
                Increments j to ensure that the last character is not parsed twice
                Resets sb to a new StringBuilder()
                 */
                j++;
                sb = new StringBuilder();
                /*
                For loop that parses through s
                 */
                for (int x = j; x < s.length(); x++) {
                    /*
                    If the character at x in s is not a blank character, appends the character to sb
                     */
                    if (s.charAt(x) != ' ') {
                        sb.append(s.charAt(x));
                    }
                    /*
                    If sb.toString() is not a blank string
                    an Edge is added to the graph with the given name and sb
                    Resets sb after the Edge is added
                     */
                    else if (!sb.toString().equals(" ")) {
                        graph.addEdge(name.toString(), sb.toString());
                        sb = new StringBuilder();
                    }
                }
                /*
                Outside for loop, as long as sb is not a blank character, the remaining edge is then added to the graph
                 */
                if (!sb.toString().equals(" ")) {
                    graph.addEdge(name.toString(), sb.toString());
                }
            }
        /*
        Returns the graph
         */
        return graph;
    }

    /**
     * Returns the result of depth-first search between Node from and Node to
     * @param from --> K
     * @param to --> K
     * @return K[]
     */
    public K[] DFS(K from, K to) {
        /*
        Creates a visited which will track every value that has been visited
        Created a queue of type Stack of type LIFO
        Recursively calls helperDFS
        Returns K[] version of visited.toArray()
         */
        ArrayList<K> visited = new ArrayList<>();
        Stack<K> stack = new Stack<>();
        helperDFS(from, to, visited, stack);
        return (K[]) visited.toArray();
    }

    /**
     * Private helper method used in DFS
     *
     * @param from K
     * @param to K
     * @param visited List
     * @param stack Stack
     */
    private void helperDFS(K from, K to, List<K> visited, Stack<K> stack) {
        /*
        Base case
        Adds from to visited and returns
         */
        if (from.equals(to)) {
            visited.add(from);
            return;
        }
        /*
        Sets n to Node<K, V>
        Sets list to new LinkedList
         */
        Node<K, V> n = getNode(from);
        LinkedList<Node> list = new LinkedList<>();
        /*
        For each that parses through adjacencyList
        If the first node in l equals n.getName(), list = l
         */
        for (LinkedList<Node> l : adjacencyList) {
            if (l.get(0).getName().equals(n.getName())) {
                list = l;
                break;
            }
        }
        /*
        For loop that parses through list
         */
        for (int i = 0; i < list.size(); i++) {
            /*
            If visited nor stack contains n.getName(), n.getName() is added to stack
             */
            if (!visited.contains(n.getName()) && !stack.contains(n.getName())) {
                stack.add(n.getName());
            }
        }
        /*
        Adds from to visited
        Clears first element of queue to remove
        Recursively calls helperBFS
         */
        visited.add(from);
        from = stack.remove(0);
        helperDFS(from, to, visited, stack);
    }

    /**
     * Returns the result of breadth-first search between Node from and Node to
     * @param from --> K
     * @param to --> K
     * @return K[]
     */
    public K[] BFS(K from, K to) {
        /*
        Creates a visited which will track every value that has been visited
        Created a queue of type LinkedList which will use the FIFO concept
        Recursively calls helperBFS
        Returns K[] version of visited.toArray()
         */
        ArrayList<K> visited = new ArrayList<K>();
        LinkedList<K> queue = new LinkedList<K>();
        helperBFS(from, to, visited, queue);
        return (K[]) visited.toArray();
    }

    /**
     * Private helper method for breadth-first search
     *
     * @param from    K
     * @param to      K
     * @param visited ArrayList
     * @param queue LinkedList
     */
    private void helperBFS(K from, K to, ArrayList<K> visited, LinkedList<K> queue) {
        /*
        Base case
        Adds from to visited and returns
         */
        if (from.equals(to)) {
            visited.add(from);
            return;
        }
        /*
        Sets n to Node<K, V>
        Sets list to new LinkedList
         */
        Node<K, V> n = getNode(from);
        LinkedList<Node> list = new LinkedList<>();
        /*
        For each that parses through adjacencyList
        If the first node in l equals n.getName(), list = l
         */
        for (LinkedList<Node> l : adjacencyList) {
            if (l.get(0).getName().equals(n.getName())) {
                list = l;
                break;
            }
        }
        /*
        For loop that parses through list
         */
        for (int i = 0; i < list.size(); i++) {
            /*
            If visited nor queue contains n.getName(), n.getName() is added to queue
             */
            if (!visited.contains(n.getName()) && !queue.contains(n.getName())) {
                queue.add(n.getName());
            }
        }
        /*
        Adds from to visited
        Clears first element of queue to remove
        Recursively calls helperBFS
         */
        visited.add(from);
        from = queue.remove(0);
        helperBFS(from, to, visited, queue);
    }

    /**
     * Private helper method for getNode
     *
     * @param name --> K
     * @return Node
     */
    private Node getNode(K name) {
        /*
        For loop that parses through adjacencyList
         */
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).get(0).getName().equals(name)) {
                return adjacencyList.get(i).get(0);
            }
        }
        /*
        Returns null
         */
        return null;
    }

    /**
     * Protected Node class to be used to track K name and V data
     * @param <K> name
     * @param <V> data
     */
    protected class Node<K,V> {
        /*
        Private fields for K name, V data, and list
         */
        private K name;
        private V data;

        /**
         * Node constructor
         * @param name K
         * @param data V
         */
        public Node (K name, V data) {
            this.name = name;
            this.data = data;
        }

        /**
         * Getter for Name
         * @return --> K
         */
        public K getName() {
            return name;
        }

        /**
         * Setter for Name
         * @param name --> K
         */
        public void setName(K name) {
            this.name = name;
        }

        /**
         * Getter for Data
         * @return V
         */
        public V getData() {
            return data;
        }

        /**
         * Setter for Data
         * @param data --> V
         */
        public void setData(V data) {
            this.data = data;
        }
    }
}
