import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

/**
 * {@author Caio Albuquerque}
 *
 * the WordLadders class
 */
public class WordLadders {

    private static Graph<Integer, String> graph = new Graph<>();
    private static HashMap<String, Integer> map = new HashMap<>();

    /**
     * Method that readsWordGraph given a String filename
     * @param filename String
     * @return Graph<Integer, String>
     * @throws FileNotFoundException --> when file is not found
     */
    public static Graph<Integer, String> readWordGraph(String filename) throws FileNotFoundException {
        /*
        Try aspect of try-catch signature which will first try what is in the code below
        If this does not work --> Goes to catch
         */
        try {
            /*
            Creates a scanner that will read the filename
             */
            Scanner scanner = new Scanner(new File(filename));
            /*
            While loop that parses until scanner does not have a next line
             */
            while (scanner.hasNextLine()) {
                /*
                Creates a string of words separated by an empty space
                Sets int number to the integer.parseInt value returned by the first word of the current line
                Sets the data to be the second word of the next
                 */
                String[] words = scanner.nextLine().split(" ");
                int number = Integer.parseInt(words[0]);
                String data = words[1];
                /*
                Adds Node with number and data to graph
                Puts value of data, number into map
                 */
                graph.addNode(number, data);
                map.put(data, number);
                /*
                For loop that parses through rest of words (neighbors) of word
                 */
                for (int i = 2; i < words.length; i++) {
                    graph.addEdge(number, Integer.valueOf(words[i]));
                }
            }
            /*
            File is done being read --> thus closes the scanner
             */
            scanner.close();
        }
        /*
        Catch part of try-catch signature, where if the try method does not work, a FileNotFoundException is thrown
         */
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        /*
        Returns the graph
         */
        return graph;
    }

    /**
     * Main method that runs the readWordGraph given user-inputs
     * @param args String[]
     * @throws FileNotFoundException --> if file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Gets the user input for the scanner to be implemented
         */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a file.");
            /*
            Sets String f to the file chosen by the user
            Calls readWordGraph on String f
             */
            String f = scanner.nextLine();
            WordLadders.readWordGraph(f);
            /*
            Allows the user to choose a starting word and ending word for the WordLadder
            Makes both words lowercase
             */
            System.out.println("Choose a starting word.");
            String start = scanner.nextLine().toLowerCase();
            System.out.println("Choose an ending word.");
            String end = scanner.nextLine().toLowerCase();
            /*
            User chooses either 'Depth-First Search' or 'Breadth-First Search'
             */
            System.out.println("Choose which searching method you would like.");
            String chosenFirstSearch = scanner.nextLine();
            /*
            If statement --> If 'Depth-First Search is chosen, performs DFS from start to end on map
             */
            if (chosenFirstSearch.equals("Depth-First Search")) {
                System.out.println(Arrays.toString(graph.DFS(map.get(start), map.get(end))));
            }
            /*
            Else if statement --> If Depth-First Search was not chosen, performs BFS from start to end on map
             */
            else if (chosenFirstSearch.equals("Breadth-First Search")) {
                System.out.println(Arrays.toString(graph.BFS(map.get(start), map.get(end))));
            }
            else {
                System.out.println("Try again.");
                break;
            }
        }
    }

}
