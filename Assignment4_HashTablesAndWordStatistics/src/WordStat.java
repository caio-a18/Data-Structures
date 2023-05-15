import java.util.Collections;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * {@author Caio Albuquerque}
 *
 * the WordStat class that is used to compute various statistics about words
 * preset in certain texts --> each instance of this class corresponds to one text
 */
public class WordStat {

    /*
    Instantiates tableCounter of type HashTable<Integer> --> Used to track how often the word is found
    Instantiates tableRanker of type HashTable<Integer> --> Used to track the rank of the word
    Instantiates stats of type ArrayList<HashTable<Integer>.Node>
                 --> used in private helper method to organize HashTables
    Instantiates list of type ArrayList<String> --> Used to store all words from tableCounter or tableRanker
     */
    private HashTable<Integer> tableCounter = new HashTable<>();
    private HashTable<Integer> tableRanker = new HashTable<>();
    private ArrayList<HashTable<Integer>.Node> stats = new ArrayList<>();
    private ArrayList<String> list;

    /**
     * Average Runtime: O(W log W)
     * WordStat Constructor that creates an instance of WordStat to calculate
     * the statistics in WordStat methods based on normalized words
     *
     * @param file --> file to be read
     * @throws IOException --> throws error if issue is found
     */
    public WordStat(String file) throws IOException {
        /*
        Instantiates list as a new Tokenizer(file)).wordList() --> ArrayList<String> of file
        Instantiates tableCounter as a new HashTable double the size of list
        Runs private helper method to sort words in readable manner
         */
        list = (new Tokenizer(file)).wordList();
        tableCounter = new HashTable<>(list.size() * 2);
        this.wordStats();
    }

    /**
     * Average Runtime: O(W log W)
     * WordStat Constructor that creates an instance of WordStat to calculate
     * the statistics in WordStat methods based on normalized words
     *
     * @param text --> array of Strings to be read
     * @throws IOException --> throws error if issue is found
     */
    public WordStat(String[] text) throws IOException {
        /*
        Instantiates list as a new Tokenizer(text)).wordList() --> ArrayList<String> of text
        Instantiates tableCounter as a new HashTable double the size of list
        Runs private helper method to sort words in readable manner
         */
        list = (new Tokenizer(text)).wordList();
        tableCounter = new HashTable<>(list.size() * 2);
        this.wordStats();
    }

    /**
     * Average Runtime: O(1) | Worst Case Runtime: O(W)
     * Returns the number of times a word is found in a normalized text
     *
     * @param word --> word to be checked
     * @return int --> number of times the word is found
     */
    public int wordCount(String word) {
        /*
        Uses try catch to get the specified @param word from tableCounter --> if found, then it is returned
         */
        try {
            return tableCounter.get(word);
        }
        /*
        If word is not found in tableCounter --> return 0 --> word is found '0' times in tableCounter
         */
        catch (NoSuchElementException e) {
            return 0;
        }
    }

    /**
     * Average Runtime: O(1) | Worst Case Runtime: O(W)
     * Returns the rank of the specified word in respect to how often it occurs relative
     * to the other words in the normalized text
     *
     * @param word --> word to be checked
     * @return int --> rank of frequencies
     */
    public int wordRank(String word) {
        /*
        Uses try catch to get the specified @param word from tableRanker --> if found, then it is returned
         */
        try {
            return tableRanker.get(word);
        }
        /*
        If word is not found in tableCounter --> return 0 --> word has no rank
         */
        catch (NoSuchElementException e) {
            return 0;
        }
    }

    /**
     * Required Runtime: O(k)
     * Returns String array of the most common words in the normalized text in decreasing order of frequency
     * Throws IllegalArgumentException() if k is negative
     *
     * @param k --> k the most common words in normalized text
     * @return String[] --> array
     */
    public String[] mostCommonWords(int k) {
        /*
        If k is a negative value, an IllegalArgumentException() is thrown
         */
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        /*
        Initializes stringArray as an array of String
        for loop that parses from index 0 of arrayString until it reaches k or if it reaches the end of size
         */
        String[] stringArray = new String[k];
        for (int i = 0; i < k && i < stats.size(); i++) {
            /*
            Puts the String key of every value of stats into stringArray
            Since stats is sorted using Collections.sort, it just inputs every value from the ArrayList into the Array
             */
            stringArray[i] = stats.get(i).getKey();
        }
        /*
        Returns stringArray
         */
        return stringArray;
    }

    /**
     * Required Runtime: O(k)
     * Returns String array of the least common words in the normalized text in increasing order of their frequency
     * Throws IllegalArgumentException() if k is negative
     *
     * @param k --> k the least common words in normalized text
     * @return String[] --> array
     */
    public String[] leastCommonWords(int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        /*
        Initializes stringArray as an array of String
        for loop that parses from index 0 of arrayString until it reaches k or if it reaches the end of stats.size()
         */
        String[] stringArray = new String[k];
        for (int i = 0; i < k && i < stats.size(); i++) {
            /*
            Puts the String key of every value of stats into stringArray
            Since stats is sorted using Collections.sort, it just inputs every value from the ArrayList into the Array
            However, it starts at the end of the sorted ArrayList
             */
            stringArray[i] = stats.get(stats.size() - i - 1).getKey();
        }
        /*
        Returns stringArray
         */
        return stringArray;
    }

    /**
     * Required Runtime: O(W^2)
     * Returns the k most common words (collocations) that precede the first instance of baseWord if precede is true
     * or the k most that follow the first instance of baseWord if precede is false
     * Throws IllegalArgumentException() if k is negative or if baseWord is not in normalized text
     *
     * @param k        --> k most common collocations that precede the first instance of baseWord
     * @param baseWord --> baseWord to be checked
     * @param precede  --> boolean that will determine to check the preceding
     *                 or non-preceding words in reference to baseWord
     * @return String[] --> array of most common collocations
     */
    public String[] mostCommonCollocations(int k, String baseWord, boolean precede) {
        /*
        If k is a negative value, an IllegalArgumentException() is thrown
         */
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        /*
        Initializes String[] stringArray with the size of k
        Initializes int curr to track current position of index in reference to k
        If statement to check if list contains the baseWord
                                  --> If baseWord is not in list, an IllegalArgumentException() is thrown
         */
        String[] stringArray = new String[k];
        int curr = 0;
        if (!list.contains(baseWord)) {
            throw new IllegalArgumentException();
        }
        /*
        Initializes int indexOfBaseWord which stores the indexOf baseWord in list
         */
        int indexOfBaseWord = list.indexOf(baseWord);
        /*
        For loop that parses keeps parses through nested for loop such that x < stats.size() and curr position < k
        Nested for loop that keeps parses ArrayList<String> list such that j is not negative and j < list.size()
        j goes to the left of the ArrayList<String> list if boolean precede is true
        j goes to the right of the ArrayList<String> list if boolean precede is false
         */
        for (int x = 0; x < stats.size() && curr < k; x++) {
            if (precede == true) {
                for (int j = indexOfBaseWord; j >= 0 && j < list.size(); j++) {
                /*
                If the k most common collocations that precede or !precede the first instance of baseWord is found,
                curr increments by 1 and the updated curr index in stringArray is set to the found value
                the loop then breaks and the stringArray of the most common collocations is returned
                 */
                    if (list.get(j).equals(stats.get(x).getKey())) {
                        stringArray[curr++] = list.get(j);
                        break;
                    }
                }
            }
            else {
                for (int j = indexOfBaseWord; j >= 0 && j < list.size(); j--) {
                /*
                If the k most common collocations that precede or !precede the first instance of baseWord is found,
                curr increments by 1 and the updated curr index in stringArray is set to the found value
                the loop then breaks and the stringArray of the most common collocations is returned
                 */
                    if (list.get(j).equals(stats.get(x).getKey())) {
                        stringArray[curr++] = list.get(j);
                        break;
                    }
                }
            }
        }
        /*
        Returns stringArray
         */
        return stringArray;
    }

    /**
     * Protected helper method to be used in wordStat methods
     * Prepares the String file or String[] text to be properly read allowing all methods to have proper runtimes
     * Uses Collections.sort() to organize words in natural ordering
     */
    protected void wordStats() {
        /*
        Parses through every string in list --> ArrayList<String>
         */
        for (String s : list) {
            /*
            Puts every string in list into tableCounter, with T value as '1'
            if tableCounter.get(s) --> String is equal to 1 --> then it is a new word
            the new word is then added to stats as a Node
             */
            int initialCount = 1;
            tableCounter.put(s, initialCount);
            if (tableCounter.get(s) == 1) {
                stats.add(tableCounter.new Node(s, initialCount));
            }
        }
        /*
        For every HashTable<Integer>.Node in stats
        a new value is set, which is the temps key from the tableCounter
         */
        for (HashTable<Integer>.Node temp : stats) {
            temp.setValue(tableCounter.get(temp.getKey()));
        }
        /*
        Sorts stats in ascending order according to the natural ordering of elements
        Sets tableRanker to a new HashTable in order to be used in wordRank()
         */
        Collections.sort(stats);
        tableRanker = new HashTable<>();
        /*
        Sets currentRank of each word to 1 --> rank of Words
        For loop that parses through all elements in stats
         */
        int currentRank = 1;
        for (int i = 0; i < stats.size(); i++) {
            /*
            Puts every String value of stats into tableRanker along with rank 1
             */
            tableRanker.put(stats.get(i).getKey(), currentRank);
            /*
            If the current index is less than the number of elements in size (makes it so there is no null exception)
            and the next value in stats is not equal to the current value in stats, then currentRank increments by 2
             */
            if (i + 1 < stats.size() && stats.get(i + 1).getValue() != stats.get(i).getValue()) {
                currentRank = i + 2;
            }
        }
    }
}
