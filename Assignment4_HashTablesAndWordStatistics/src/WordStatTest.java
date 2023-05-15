import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@author Caio Albuquerque}
 *
 * Testing Class for WordStat
 */

class WordStatTest {

    /*
    Creates WordStat (String file) --> w1
    Creates array of Strings --> stringArray
    Creates WordStat (String[] text) --> w2
     */
    WordStat w1 = new WordStat("C:\\Programming Projects\\CSDS 233\\Assignment4\\src\\test.txt.txt");
    String[] wordArray = {
            "apple", "banana", "cherry", "date", "eggplant", "fig",
            "grape", "honeydew", "ice cream", "juice", "kiwi", "lemon",
            "mango", "nectarine", "orange", "peach", "quince", "raspberry",
            "strawberry", "tangerine", "ugli fruit", "vanilla", "watermelon",
            "xylophone", "yellow squash", "zucchini", "apple", "banana",
            "cherry", "date", "eggplant", "fig", "grape", "honeydew",
            "ice cream", "juice", "kiwi", "lemon", "mango", "nectarine",
            "orange", "peach", "quince", "raspberry", "strawberry",
            "tangerine", "ugli fruit", "vanilla", "watermelon",
            "xylophone", "yellow squash", "zucchini", "apple",
            "banana", "cherry", "date", "eggplant", "fig", "grape",
            "honeydew", "ice cream", "juice", "kiwi", "lemon", "mango",
            "nectarine", "orange", "peach", "quince", "raspberry",
            "strawberry", "tangerine", "ugli fruit", "vanilla", "watermelon",
            "xylophone", "yellow squash", "zucchini", "apple", "banana",
            "cherry", "date", "eggplant", "fig", "grape", "honeydew",
            "ice cream", "juice", "kiwi", "lemon", "mango",
            "nectarine", "orange", "peach", "quince", "raspberry",
            "strawberry", "tangerine", "ugli fruit",
            "vanilla", "watermelon", "xylophone", "yellow squash",
            "zucchini", "apple", "banana", "cherry", "date",
            "eggplant", "fig", "grape", "honeydew", "ice cream",
            "juice", "kiwi", "lemon", "mango", "giraffe",
            "nectarine", "orange", "peach", "quince",
            "raspberry", "strawberry", "tangerine", "ugli fruit",
            "vanilla", "watermelon", "xylophone",
            "yellow squash", "zucchini"
    };
    WordStat w2 = new WordStat(wordArray);

    WordStatTest() throws IOException {

    }

    /**
     * Tests the wordCount()
     */
    @Test
    void wordCount() {
        /*
        Checks if the try part of the method works
         */
        assertEquals(2, w1.wordCount("as"));
        assertEquals(3, w1.wordCount("soon"));
        assertEquals(1, w1.wordCount("wall"));

        assertEquals(5, w2.wordCount("apple"));
        assertEquals(5, w2.wordCount("banana"));
        assertEquals(1, w2.wordCount("giraffe"));
        /*
        Checks if the catch part of the method works
         */
        assertEquals(0, w1.wordCount("caio"));
        assertEquals(0, w2.wordCount("caio"));
    }

    /**
     * Tests the wordRank()
     */
    @Test
    void wordRank() {
        /*
        Checks if the try part of the method works
         */
        assertEquals(12, w1.wordRank("as"));
        assertEquals(1, w1.wordRank("soon"));
        assertEquals(8, w1.wordRank("wall"));

        assertEquals(30, w2.wordRank("apple"));
        assertEquals(9, w2.wordRank("banana"));
        assertEquals(30, w2.wordRank("giraffe"));
        /*
        Checks if the catch part of the method works
         */
        assertEquals(0, w1.wordRank("caio"));
        assertEquals(0, w2.wordRank("caio"));
    }

    /**
     * Tests the mostCommonWords()
     */
    @Test
    void mostCommonWords() {
        String[] w1test1 = {"the", "very"};
        String[] w1test2 = {"the", "very", "soon", "is", "as"};
        String[] w1test3 = {"the"};
        String[] empty = {};

        String[] w2test1 = {"juice", "watermelon", "apple", "banana"};
        String[] w2test2 = {"juice", "watermelon", "apple", "banana", "cherry"};
        String[] w2test3 = {"juice"};

        assertEquals(Arrays.toString(empty), Arrays.toString(w1.mostCommonWords(0)));
        assertEquals(Arrays.toString(w1test1), Arrays.toString(w1.mostCommonWords(2)));
        assertEquals(Arrays.toString(w1test2), Arrays.toString(w1.mostCommonWords(5)));
        assertEquals(Arrays.toString(w1test3), Arrays.toString(w1.mostCommonWords(1)));

        assertEquals(Arrays.toString(empty), Arrays.toString(w2.mostCommonWords(0)));
        assertEquals(Arrays.toString(w2test1), Arrays.toString(w2.mostCommonWords(4)));
        assertEquals(Arrays.toString(w2test2), Arrays.toString(w2.mostCommonWords(5)));
        assertEquals(Arrays.toString(w2test3), Arrays.toString(w2.mostCommonWords(1)));

        assertThrows(IllegalArgumentException.class, () -> w1.mostCommonWords(-5));
        assertThrows(IllegalArgumentException.class, () -> w2.mostCommonWords(-10));
    }

    /**
     * Tests the leastCommonWords()
     */
    @Test
    void leastCommonWords() {
        String[] w1test1 = {"hahaha", "much"};
        String[] w1test2 = {"hahaha", "much", "hey", "lemonade", "a"};
        String[] w1test3 = {"hahaha"};
        String[] empty = {};

        String[] w2test1 = {"giraffe", "zucchini", "squash", "yellow"};
        String[] w2test2 = {"giraffe", "zucchini", "squash", "yellow", "xylophone"};
        String[] w2test3 = {"giraffe"};

        assertEquals(Arrays.toString(empty), Arrays.toString(w1.leastCommonWords(0)));
        assertEquals(Arrays.toString(w1test1), Arrays.toString(w1.leastCommonWords(2)));
        assertEquals(Arrays.toString(w1test2), Arrays.toString(w1.leastCommonWords(5)));
        assertEquals(Arrays.toString(w1test3), Arrays.toString(w1.leastCommonWords(1)));

        assertEquals(Arrays.toString(empty), Arrays.toString(w2.leastCommonWords(0)));
        assertEquals(Arrays.toString(w2test1), Arrays.toString(w2.leastCommonWords(4)));
        assertEquals(Arrays.toString(w2test2), Arrays.toString(w2.leastCommonWords(5)));
        assertEquals(Arrays.toString(w2test3), Arrays.toString(w2.leastCommonWords(1)));

        assertThrows(IllegalArgumentException.class, () -> w1.leastCommonWords(-5));
        assertThrows(IllegalArgumentException.class, () -> w2.leastCommonWords(-10));
    }

    /**
     * Tests the mostCommonCollocations
     */
    @Test
    void mostCommonCollocations() {
        String[] w1test1 = {"the", "very", "is", "as", "wall", "a"};
        String[] w1test2 = {"the", "very", "soon", "because", "squirrel", "lemonade"};
        String[] w1test3 = {"the", "very", "soon", "because"};
        String[] empty = {};

        String[] w2test1 = {"juice", "watermelon", "apple", "banana", "cherry", "date"};
        String[] w2test1a = {"juice", "watermelon"};
        String[] w2test2 = {"juice", "watermelon", "apple", "banana"};
        String[] w2test3 = {"juice"};

        assertEquals(Arrays.toString(empty), Arrays.toString(w1.mostCommonCollocations(0, "very", false)));
        assertEquals(Arrays.toString(w1test1), Arrays.toString(w1.mostCommonCollocations(6, "very", true)));
        assertEquals(Arrays.toString(w1test2), Arrays.toString(w1.mostCommonCollocations(6, "very", false)));
        assertEquals(Arrays.toString(w1test3), Arrays.toString(w1.mostCommonCollocations(4, "soon", false)));

        assertEquals(Arrays.toString(empty), Arrays.toString(w2.mostCommonCollocations(0, "giraffe", false)));
        assertEquals(Arrays.toString(w2test1), Arrays.toString(w2.mostCommonCollocations(6, "zucchini", true)));
        assertEquals(Arrays.toString(w2test1a), Arrays.toString(w2.mostCommonCollocations(2, "zucchini", false)));
        assertEquals(Arrays.toString(w2test2), Arrays.toString(w2.mostCommonCollocations(4, "banana", false)));
        assertEquals(Arrays.toString(w2test3), Arrays.toString(w2.mostCommonCollocations(1, "mango", true)));

        assertThrows(IllegalArgumentException.class, () -> w1.mostCommonCollocations(-5, "mango", true));
        assertThrows(IllegalArgumentException.class, () -> w1.mostCommonCollocations(20, "caio", false));

        assertThrows(IllegalArgumentException.class, () -> w2.mostCommonCollocations(-5, "mango", true));
        assertThrows(IllegalArgumentException.class, () -> w2.mostCommonCollocations(20, "caio", false));
    }
}