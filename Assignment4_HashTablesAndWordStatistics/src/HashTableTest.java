import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@author Caio Albuquerque}
 *
 * Testing Class for HashTable
 */

class HashTableTest {

    @Test
    public void testGetPutAndRemove() {
        /*
        Instantiates ht of type HashTable
         */
        HashTable<String> ht = new HashTable<>();
        /*
        Checks to see if errors work for get and remove methods
        Checks to see if size is correct
         */
        assertThrows(NoSuchElementException.class, () -> ht.get("key1"));
        assertThrows(NoSuchElementException.class, () -> ht.remove("key1"));
        assertEquals(0, ht.size());

        /*
        Puts (String key, T value) into ht
        Checks if properly placed
        Checks to see if errors work for get and remove methods
        Checks to see if size is correct
         */
        ht.put("key1", "value1");
        assertEquals("value1", ht.get("key1"));
        assertThrows(NoSuchElementException.class, () -> ht.get("key2"));
        assertThrows(NoSuchElementException.class, () -> ht.remove("key2"));
        assertEquals(1, ht.size());

        /*
        Puts (String key, T value) into ht
        Checks if properly placed
         */
        ht.put("key2", "value2");
        assertEquals("value2", ht.get("key2"));

        /*
        Puts (String key, T value) into ht
        Checks if properly placed
        Checks to see if remove method words
        Checks if value was properly removed
        Checks if size was updated after removal
         */
        ht.put("key3", "value3");
        assertEquals("value3", ht.get("key3"));
        assertEquals("value2", ht.remove("key2"));
        assertThrows(NoSuchElementException.class, () -> ht.get("key2"));
        assertEquals(2, ht.size());
    }

}