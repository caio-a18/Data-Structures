/**
 * {@author Caio Albuquerque}
 *
 * the Stack class which does not have a limit in capacity
 * has three main functions of push(), pop(), and peek()
 * @param <T> --> Generic T
 */

public class Stack<T extends Comparable<? super T>>{
    /*
    creates a Node<T> which stores the value of the first element in the stack,
    will change from null to the first element when an element enters the stack
    creates an int size which stores the size of the stack,
    increments when an element is pushed and decrements when an element is popped
     */
    private Node<T> top = null;
    private int size = 0;

    /**
     * empty Stack constructor
     */
    public Stack() {

    }
    /**
     * method that pushes a value into the front of the stack given an input 'value'
     * @param value --> the item to be inserted into the stack
     * @return boolean --> if T value was inserted into the stack
     */
    public boolean push(T value) {
        /*
        sets top equal to the new element of type T in the stack
        increments the count of how many elements in the stack
        returns true since the element was properly inserted in the stack
         */
        top = new Node<>(value, top);
        size++;
        return true;
    }

    /**
     * method that deletes the element at the front of the stack
     * which returns the value of the element deleted, and null if no element is deleted
     * @return T --> the element at the front of the stack
     */
    public T pop() {
        /*
        checks to see if the top element is null or not (if the element is null, it means the stack is empty),
        if null, the method returns null
        if not null, the method pops the top element and returns it
         */
        if (top == null) {
            return null;
        }
        /*
        instantiates an element of type T to hold the value of the top element
        sets the top equal to the next element in the stack
        decrements the count of how many elements in the stack
        returns the element popped
         */
        T element = top.getElement();
        top = top.getNext();
        size--;
        return element;
    }

    /**
     * method that returns but does not delete the value of the front
     * element of the stack, returns null if there is no value
     * @return T --> the element at the front of the stack
     */
    public T peek() {
        /*
        checks to see if the top element is null or not (if the element is null, it means the stack is empty),
        if null, the method returns null
        if not null, the method returns the top element
         */
        if (top == null) {
            return null;
        }
        return top.getElement();
    }

    /**
     * creates toArray() method which will be used to check the methods
     * made protected since rubric disallows public methods
     * @return arr --> an array version of all the elements in the stack
     */
    protected Object[] toArray() {
        /*
        creates an array the size of the stack
        creates an int i which will be used for the index of the array
         */
        Object[] arr = new Object[this.getSize()];
        int i = 0;
        /*
        starts at the front node and parses through the stack adding each
        element into an array until the end of the stack is reached
        sets the next element of the array equal to the next node
         */
        for (Node<T> node = top; node != null; node = node.getNext()) {
            arr[i++] = node.getElement();
        }
        return arr;
    }

    /**
     * protected helper getSize method which returns the size of the stack
     * @return size
     */
    protected int getSize() {
        return this.size;
    }

    /**
     * protected helper method that checks if a stack is empty or not
     * @return boolean --> if the stack is empty or not
     */
    protected boolean isEmpty() {
        if (this.getSize() == 0) {
            return true;
        }
        return false;
    }

    /**
     * creates the private static class called 'Node' which will be implemented into the Stack
     * @param <T> --> generic type 'T' which can be used for any Data Type for the node
     */
    private static class Node<T> {
         /*
         creates an element which will hold the value of T
         creates the Node which points to the next node
          */
         private T element = null;
         private Node<T> next = null;

        /**
         * Node constructor which creates the instances of the element and next
         * @param element --> the element of type T
         * @param next --> the Node that points to the next element
         */
        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * getter method for the T element
         * @return element
         */
        public T getElement() {
            return this.element;
        }

        /**
         * getter method for the Node<T>
         * @return next --> gets the next element of the stack
         */
        public Node<T> getNext() {
            return this.next;
        }

    }
}
