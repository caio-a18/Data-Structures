import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * {@author Caio Albuquerque}
 *
 * the HashTable class that stores String keys paired with generic type T values
 * uses the built-in Java hashCode method to compute hash codes
 */
public class HashTable<T> {

    /*
    Instantiates hashtable --> an array of LinkedList<Node>
    Instantiates int size to store how many elements are in the HashTable
     */
    private LinkedList<Node>[] hashtable;
    private int size;

    /**
     * Runtime: O(1)
     * HashTable constructor that initializes the table with a small default capacity
     */
    public HashTable() {
        this(20);
    }

    /**
     * Runtime: O(1)
     * HashTable constructor that initializes the HashTable to a specific capacity
     * Throws IllegalArgumentException if capacity is negative
     *
     * @param capacity --> capacity of the HashTable
     */
    public HashTable(int capacity) {
        /*
        If capacity is a negative value, an IllegalArgumentException() is thrown
         */
        if (capacity < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        /*
        Creates hashtable of capacity
        Sets size equal to capacity
         */
        this.hashtable = new LinkedList[capacity];
    }

    /**
     * Runtime Average: O(1)
     * Runtime WorstCase: O(N), where N is size of HashTable
     * get method that returns the value associated with a specific key
     * throws NoSuchElementException if key does not exist
     *
     * @param key --> Key to be checked
     * @return T --> Value associated with specified key
     */
    public T get(String key) {
        /*
        If statement that checks if hashtable length is 0
        Creates an index which is assigned to the hashCode % the size of the array
        Creates a list at the index that was found
         */
        if (hashtable.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = Math.abs(key.hashCode() % hashtable.length);
        LinkedList<Node> list = hashtable[index];
        /*
        If the list not null, it creates a for each loop to parse through every Node in hashtable[index]
        If the current node's key is equal to the key passed in, the T value of the node is returned
         */
        if (list != null) {
            for (Node node : hashtable[index]) {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
            }
        }
        /*
        Throws NoSuchElementException() if T value is not found
         */
        throw new NoSuchElementException();
    }

    /**
     * Runtime Average: O(1)
     * Runtime WorstCase: O(N), where N is size of HashTable
     * put method that adds specified key-value pair in HashTable
     *
     * @param key   --> Key to be inserted
     * @param value --> Value to be inserted
     */
    public void put(String key, T value) {
        /*
        If the load factor is greater than 1, the table rehashes
         */
        if ( (double) size >= hashtable.length) {
            rehash();
        }
        /*
        Creates an index which is assigned to the hashCode % the size of the array
        Creates a list at the index that was found
         */
        int index = Math.abs(key.hashCode() % hashtable.length);
        LinkedList<Node> list = hashtable[index];
        /*
        If the list is null, sets the index to a new LinkedList<Node<T>>
         */
        if (list == null) {
            hashtable[index] = new LinkedList<>();
        }
        /*
        Parses through hashtable[index]
         */
        for (Node node : hashtable[index]) {
            /*
            If value is an instanceof Integer, typecasts the Integer version of node's value and the passed value
            into type T of the Integer --> this allows for insertion into hashtable[index]
            Else returns the value
             */
            if (value instanceof Integer) {
                node.setValue((T) (Integer) ((Integer) node.getValue() + (Integer) value));
            }
            else {
                return;
            }
        }
        /*
        Inserts new Node of (key, value) into hashtable[index]
        Increments the size by 1
         */
        hashtable[index].add(new Node(key, value));
        size++;
    }

    /**
     * Runtime Average: O(1)
     * Runtime WorstCase:O(N), where N is size of HashTable
     * remove method that removes specified key-value pair from HashTable
     * Returns the T value associated with the specified key
     * Throws NoSuchElementException is key DNE
     *
     * @param key --> key to be removed
     * @return T --> T value associated with the specified key
     */
    public T remove(String key) {
        /*
        If statement that checks if hashtable length is 0
        Creates an index which is assigned to the hashCode % the size of the array
        Creates a list at the index that was found
         */
        if (hashtable.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = Math.abs(key.hashCode() % hashtable.length);
        LinkedList<Node> list = hashtable[index];
        /*
        If list is not null, parses through each Node found in hashtable[index]
        If the current node's key is equal to the passed key:
        the node at hashtable[index] is removed and size is decremented
        The removed Node's T value is then returned
         */
        if (list != null) {
            for (Node node : hashtable[index]) {
                if (node.getKey().equals(key)) {
                    hashtable[index].remove(node);
                    size--;
                    return node.getValue();
                }
            }
        }
        /*
        Throws an error if no value is found
         */
        throw new NoSuchElementException();
    }

    /**
     * Runtime: O(1)
     * Returns the number of elements in the HashTable
     *
     * @return size --> number of elements in the HashTable
     */
    public int size() {
        /*
        Returns the number of elements in the HashTable
         */
        return this.size;
    }

    /**
     * Protected helper method to rehash the HashTable if loadFactor > 1
     */
    private void rehash() {
        /*
        Creates a temp HashTable double the capacity of the original hashtable.length
         */
        HashTable<T> temp = new HashTable<>(hashtable.length * 2);
        /*
        For each loop that parses every LinkedList<Node<T>> in hashtable
        For each loop that parses every Node<T> in list
        Adds every value from hashtable into temp
         */
        for (LinkedList<Node> list : hashtable) {
            if (list != null) {
                for (Node node : list) {
                    temp.put(node.key, node.value);
                }
            }
        }
        /*
        Updates hashtable
         */
        hashtable = temp.hashtable;
    }

    /**
     * Public Node class to be used in HashTable<T>
     * Implements Comparable<Node> to compare Nodes
     */
    public class Node implements Comparable<Node> {
        /*
        Creates a key of type String
        Creates a value of type T
         */
        private String key;
        private T value;

        /**
         * Node constructor with elements String key and T value
         * Adds int occ which will be used for tracking in WordStat
         * @param key   --> String key
         * @param value --> T value
         */
        public Node(String key, T value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Getter method for the key
         * @return --> String key
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Getter method for the value
         * @return --> T value
         */
        public T getValue() {
            return this.value;
        }

        /**
         * Setter method for value
         * @param value --> value to be changed
         */
        public void setValue(T value) {
            this.value = value;
        }

        /**
         * compareTo method which compares two nodes by value
         * @param o the object to be compared.
         * @return int --> positive value is greater, 0 is equal, and negative value is less
         */
        @Override
        public int compareTo(Node o) {
            /*
            If value of a node is an instanceof Integer
            Returns the typecast of o.getValue() as an Integer subtracted by
            the typecast of this.getValue() as an Integer
             */
            if (value instanceof Integer) {
                return (Integer) o.getValue() - (Integer) this.getValue();
            }
            return 0;
        }
    }
}
