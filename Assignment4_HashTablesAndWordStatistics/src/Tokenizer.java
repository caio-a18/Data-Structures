import java.io.StringReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@author Caio Albuquerque}
 *
 * the Tokenizer class which provides the functionality to read text and store
 * normalized words using an ArrayList composed of String elements
 */
public class Tokenizer {

    /*
    Initializes a private field ArrayList of type String to hold all normalized words (returned in wordList())
     */
    private ArrayList<String> stringList = new ArrayList<String>();
    /**
     * Runtime: O(H) --> H is number of characters in input file
     * Throws IOException if String file has an error or null value
     * Tokenizer constructor that reads a '.txt' file and parses all normalized words
     * @param file --> file to be parsed
     */
    public Tokenizer(String file) throws IOException {

        /*
        Creates br BufferedReader that passes the current String
        Creates sb StringBuilder that will parse through all normalized words in file and clear after every word
        Sets stringList to a new ArrayList<> --> String
         */
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        stringList = new ArrayList<>();
        /*
        Creates an int to check the current character
        While loop such that br is ready to be read
         */
        int c;
        while (br.ready()) {
            /*
            Sets c equal to the current character
            If c is a space, newline, carriage return, or tab && sb has a length greater than 0
                                                              --> word is added to stringList
             */
            c = br.read();
            if (c == (int) ' ' || c == (int) '\n' || c == (int) '\r' || c == (int) '\t') {
                if (sb.length() > 0) {
                    stringList.add(sb.toString().toLowerCase());
                    sb = new StringBuilder();
                }
                /*
                Continues while loop --> br.ready()
                 */
                continue;
            }
            /*
            Appends the character to sb if it is a letter or digit
             */
            if (Character.isLetterOrDigit((char) c)) {
                sb.append((char) c);
            }
        }
        /*
        Adds the last word of the file into the stringList
         */
        stringList.add(sb.toString().toLowerCase());
    }

    /**
     * Runtime: O(H) --> H is total number of characters in array
     * Throws IOException if String[] text has an error or null value
     * Tokenizer constructor that reads an input sequence (array) of Strings and parses all normalized words
     * @param text --> input sequence (array) to be parsed
     */
    public Tokenizer(String[] text) throws IOException {
        /*
        For each loop to parse through every String in text
         */
        for (String s : text) {
            /*
            Creates br BufferedReader that passes the current String
            Creates sb StringBuilder that will parse through all normalized words in String and clear after every word
             */
            BufferedReader br = new BufferedReader(new StringReader(s));
            StringBuilder sb = new StringBuilder();
            /*
            For loop that will parse through every word in given string
             */
            for (int i = 0; i < s.length() + 1; i++) {
                /*
                Sets c to the current read character
                 */
                Character c = (char) br.read();
                /*
                If c is a letter, appends the lowercase version of that letter
                 */
                if (Character.isLetter(c)) {
                    sb.append(Character.toLowerCase(c));
                }
                /*
                If c is a space, newline, carriage return, or tab --> word is added to stringList
                sb is then cleared and restarted
                 */
                else if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                    stringList.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
            /*
            Adds sb to stringList
             */
            stringList.add(sb.toString());
        }
    }

    /**
     * Runtime: O(1)
     * method that returns the ArrayList<String> containing all normalized words parsed
     * @return stringList --> ArrayList containing all normalized words parsed
     */
    public ArrayList<String> wordList() {
        /*
        Returns the stringList
         */
        return stringList;
    }
}
