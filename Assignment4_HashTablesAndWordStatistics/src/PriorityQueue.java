import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.IOException;
/**
 * {@author Caio Albuquerque}
 *
 * the PriorityQueue class that functions as a generic priority queue ADT
 * with keys corresponding to type K and values corresponding to type V
 * implements the Comparable interface and is used as a max-heap
 */
public class PriorityQueue<K extends Comparable<? super K>,V> {

    /*
    Private ArrayList of type PQ which has PriorityQueue elements
    Private field that stores the size of elements in the PriorityQueue
     */
    ArrayList<PQ<K, V>> maxHeap;
    private int size;

    /**
     * Constructor that creates an empty priority queue
     */
    public PriorityQueue() {
        maxHeap = new ArrayList<PQ<K, V>>();
    }

    /**
     * Constructor that creates a PriorityQueue with the specified key and value arrays
     * If the arrays have different lengths, an error is thrown
     * @param keys --> K array
     * @param values --> V array
     */
    public PriorityQueue(K[] keys, V[] values) {
        /*
        If arrays are NOT equal in length, an IllegalArgumentException() is thrown
         */
        if (keys.length != values.length) {
            throw new IllegalArgumentException();
        }
        maxHeap = new ArrayList<PQ<K, V>>();
        for (int i = 0; i < keys.length && i < values.length; i++) {
            add(keys[i], values[i]);
        }
    }

    /**
     * Add method which adds a key-value pair into the PriorityQueue
     * @param key --> K
     * @param value --> V
     */
    public void add(K key, V value) {
        PQ<K, V> pq = new PQ<K, V>(key, value);
        maxHeap.add(pq);
    }

    /**
     * Update method which sets a new corresponding key to the specified value
     * @param key --> K
     * @param value --> V
     */
    public void update(K key, V value) {
        PQ<K, V> pq = new PQ<K, V>(key, value);
        update(pq.key, pq.value);
    }

    /**
     * Returns the value corresponding to the greatest key from the priority queue
     * @return V
     */
    public V peek() {
       return null;
    }

    /**
     * Returns an array containing the k values corresponding to the greatest k keys from the PriorityQueue
     * @param k --> int
     * @return --> V[]
     */
    public V[] peek(int k) {
        return null;
    }

    /**
     * Removes the element corresponding to the greatest key from the PriorityQueue
     * @return V
     */
    public V poll() {
        size--;
        return null;
    }

    /**
     * Removes an element corresponding to the specified value and returns the corresponding key
     * @param value --> V
     * @return --> K
     */
    public K poll(V value) {
        size--;
        return null;
    }

    /**
     * Returns the number of elements stored in the PriorityQueue
     * @return --> size of the PriorityQueue
     */
    public int size() {
        return this.size;
    }

    /**
     * Protected static PQ class to be used in PriorityQueue
     */
    protected static class PQ<K, V> {
        /*
        private fields for K and V
         */
        private K key;
        private V value;

        /**
         * PQ constructor with K key and V value
         * @param key --> K
         * @param value --> V
         */
        public PQ(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Getter method for key
         * @return key --> K
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Setter method for key
         * @param key --> K
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Getter method for value
         * @return value --> V
         */
        public V getValue() {
            return value;
        }

        /**
         * Setter method for value
         * @param value --> V
         */
        public void setValue(V value) {
            this.value = value;
        }
    }
}
