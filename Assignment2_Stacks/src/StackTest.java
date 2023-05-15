import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@author Caio Albuquerque}
 * Tests the Stack Class using junit testing
 */
public class StackTest {

    /**
     * tests the push(T value), pop(), and peek() methods
     */
    @Test
    public void testPushPopAndPeek() {
        /*
        creates a new Stack of type String called 's'
        uses the push(T value) to push 5 elements into s
         */
        Stack<String> s = new Stack<String>();
        s.push("Apple");
        s.push("Banana");
        s.push("Cherry");
        s.push("Dragon fruit");
        s.push("Eggplant");
        Object[] arr = s.toArray();
        /*
        arr = [Apple, Banana, Cherry, Dragon fruit, Eggplant]
        checks to see if the first element of the stack is equal to the first element of arr
         */
        assertEquals(s.peek(), arr[0]);
        /*
        pops the top element, which is "Apple"
        checks to see if the top element is now "Banana"
        arr = [Banana, Cherry, Dragon fruit, Eggplant]
         */
        s.pop();
        assertEquals(s.peek(), arr[1]);
        /*
        pops the top element, which is "Banana"
        arr = [Cherry, Dragon fruit, Eggplant]
        pops the top element, which is "Cherry"
        arr = [Dragon fruit, Eggplant]
        pops the top element, which is "Dragon fruit"
        arr = [Eggplant]
        uses peek() to see if all elements were popped properly
         */
        s.pop();
        assertEquals(s.peek(), arr[2]);
        s.pop();
        assertEquals(s.peek(), arr[3]);
        s.pop();
        assertEquals(s.peek(), arr[4]);
        /*
        pops the last element of s, which means s is now empty...
        peek should return null, instead of a defined element
        arr = []
         */
        s.pop();
        assertNull(s.peek());

        /*
        creates an empty String stack and checks if the peek() returns null
         */
        Stack<String> s2 = new Stack<String>();
        Object[] arr2 = s2.toArray();
        assertNull(s.peek());
    }


}