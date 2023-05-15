import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@author Caio Albuquerque}
 *
 * Testing Class for Tokenizer
 */

class TokenizerTest {

    Tokenizer t1 = new Tokenizer("C:\\Programming Projects\\CSDS 233\\Assignment4\\src\\test.txt.txt");;

    /*
    Creates an array of type String to test non-null Tokenizer constructor
    Initializes Tokenizer t2
     */
    String[] wordArray = {
            "apple", "banana", "cherry", "date", "eggplant", "fig",
            "grape", "honeydew", "ice cream", "juice", "kiwi", "lemon",
            "mango", "nectarine", "orange", "peach", "quince", "raspberry",
            "strawberry", "tangerine", "ugli fruit", "vanilla", "watermelon",
            "xylophone", "yellow squash", "zucchini", "apple", "banana",
            "cherry", "date", "eggplant", "fig", "grape", "honeydew",
            "ice cream", "juice", "kiwi", "lemon"
    };
    Tokenizer t2 = new Tokenizer(wordArray);

    TokenizerTest() throws IOException {

    }

    @Test
    void wordList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t1.wordList().size(); i++) {
            sb.append(t1.wordList().get(i));
        }
        assertEquals("thewallisisasaasverysoonbecausethelemonadesquirrelheyverymuchhahahasoon", sb.toString());

        StringBuilder sb2 = new StringBuilder();
        for (int x = 0; x < t2.wordList().size(); x++) {
            sb2.append(t2.wordList().get(x));
        }
        assertEquals("applebananacherrydateeggplantfiggrapehoneydewicecreamjuicekiwilemonmangonectar" +
                "ineorangepeachquinceraspberrystrawberrytangerineuglifruitvanillawatermelonxylophoneyellowsquash" +
                "zucchiniapplebananacherrydateeggplantfiggrapehoneydewicecreamjuicekiwilemon", sb2.toString());
    }
}