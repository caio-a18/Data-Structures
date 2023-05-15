import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@author Caio Albuquerque}
 * Tests the StringManipulation Class using junit testing
 */

class StringManipulationTest {

    /**
     * method that tests the toPostfix() of the StringManipulation class
     */
    @Test
    public void toPostfix() {
        assertEquals("3 4 + 5 * 6", StringManipulation.toPostfix("(3 + 4) * 5 - 6"));
        assertEquals("2 3 4 + * 5 +", StringManipulation.toPostfix("2 * (3 + 4) + 5"));
        assertEquals("5 6 2 * + 3 4 / -", StringManipulation.toPostfix("5 + 6 * 2 - 3 / 4"));
        assertEquals("3 4 + 5 / 6 - 7 8 + *", StringManipulation.toPostfix("((3 + 4) / 5 - 6) * (7 + 8)"));
    }

    /**
     * method that tests the result() of the StringManipulation class
     */
    @Test
    public void result() {
        assertEquals(-1, StringManipulation.result("3 4 5 6 - * +"));
        assertEquals(29, StringManipulation.result("3 4 + 5 * 6 -"));
        assertEquals(23, StringManipulation.result("3 4 5 * + 6"));
        assertEquals(22, StringManipulation.result("3 4 * 2 5 * +"));
        assertEquals(14, StringManipulation.result("2 3 4 * +"));
    }

    /**
     * method that tests the smallestNumber() of the StringManipulation class
     */
    @Test
    public void smallestNumber() {
        assertEquals("4", StringManipulation.smallestNumber("70004", 1));
        assertEquals("23", StringManipulation.smallestNumber("4235", 2));
        assertEquals("173", StringManipulation.smallestNumber("991773", 3));
        assertEquals("0", StringManipulation.smallestNumber("00000", 4));
        assertEquals("453", StringManipulation.smallestNumber("453", 0));
        assertEquals("", StringManipulation.smallestNumber("453", 3));
        assertEquals("9", StringManipulation.smallestNumber("9999", 3));
    }

    /**
     * method that tests the unbelievableString() of the StringManipulation class
     */
    @Test
    public void unbelievableString() {
        assertEquals("abdE", StringManipulation.unbelievableString("abDDdddE"));
        assertEquals("", StringManipulation.unbelievableString("AaBbcCdD"));
        assertEquals("ABCCba", StringManipulation.unbelievableString("ABCDZzdCba"));
        assertEquals("", StringManipulation.unbelievableString("Aa"));
        assertEquals("Bc", StringManipulation.unbelievableString("Bc"));
        assertEquals("Ab", StringManipulation.unbelievableString("AaAbBb"));
    }
}