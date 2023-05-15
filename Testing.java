import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.*;

/**
 * {@author Caio Albuquerque}
 */
public class Testing {

    /**
     * tests the add(Book book) method
     */
    @Test
    public void testAdd() {
        /*
        creates five Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "2222222222222", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");;
        Book b4 = new Book("Book4", "4444444444444", "Lucas");
        Book b5 = new Book("Book5", "5555555555555", "Brian");
        // creates a new LibraryDatabase with capacity '1'
        LibraryDatabase db1 = new LibraryDatabase(1);
        // adds b1 to the database of capacity 1
        db1.add(b1);
        // checks to see if b1 is added properly
        assertEquals(b1, db1.toArray()[0]);
        // adds b2 to the database
        db1.add(b2);
        // checks to see if b2 is added properly
        assertEquals(b1, db1.toArray()[0]);
        assertEquals(b2, db1.toArray()[1]);
        // checks to see if b3, b4, and b5 are added properly
        db1.add(b3);
        db1.add(b4);
        db1.add(b5);
        assertEquals(b1, db1.toArray()[0]);
        assertEquals(b2, db1.toArray()[1]);
        assertEquals(b3, db1.toArray()[2]);
        assertEquals(b4, db1.toArray()[3]);
        assertEquals(b5, db1.toArray()[4]);
    }

    /**
     * tests the remove(String isbn) method
     */
    @Test
    public void testRemove() {
        /*
        creates three Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "2222222222222", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");
        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db1 = new LibraryDatabase(3);
        db1.add(b1);
        db1.add(b2);
        db1.add(b3);
        /*
        checks to see if the Books were added properly
         */
        assertEquals(b1, db1.toArray()[0]);
        assertEquals(b2, db1.toArray()[1]);
        assertEquals(b3, db1.toArray()[2]);
        /*
        removes b2 (the Book with the isbn "2222222222222")
         */
        db1.remove("2222222222222");
        assertEquals(b1, db1.toArray()[0]);
        assertEquals(b3, db1.toArray()[1]);
        assertNull(db1.toArray()[2]);
        /*
        removes b1 (the Book with the isbn "1111111111111")
        does not work, as the db1.toArray()[1] value is not null
         */
        db1.remove("1111111111111");
        assertEquals(b3, db1.toArray()[0]);
        assertNull(db1.toArray()[1]);
        assertNull(db1.toArray()[2]);
        /*
        removes b3 (the Book with the isbn "3333333333333")
        does not work, as the values in db1 sohuld be null
         */
        db1.remove("3333333333333");
        assertNull(db1.toArray()[0]);
        assertNull(db1.toArray()[1]);
        assertNull(db1.toArray()[2]);

        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db3 = new LibraryDatabase(3);
        db3.add(b1);
        db3.add(b2);
        db3.add(b3);
        db3.remove("44444444444444");
        /*
        checks to see if no Books were removed
         */
        assertEquals(b1, db3.toArray()[0]);
        assertEquals(b2, db3.toArray()[1]);
        assertEquals(b3, db3.toArray()[2]);
    }

    /**
     * tests to see if the size() method works
     */
    @Test
    public void testSize() {
        /*
        creates three Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "2222222222222", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");
        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db1 = new LibraryDatabase(1);
        db1.add(b1);
        db1.add(b2);
        db1.add(b3);
        int value1 = db1.size();
        assertEquals(3, value1);

        /*
        creates a new LibraryDatabase with a capacity of 200
        and does not add any Books into the database to see how size works
         */
        LibraryDatabase db2 = new LibraryDatabase(200);
        int value2 = db2.size();
        assertEquals(0, value2);
    }

    /**
     * tests the randomSelection method
     */
    @Test
    public void testRandomSelection() {
        /*
        creates three Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "2222222222222", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");
        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db1 = new LibraryDatabase(3);
        db1.add(b1);
        db1.add(b2);
        db1.add(b3);
        /*
        creates a Book object titled 'randomBook' which will hold the value of
        the randomly selected book in db1
        creates a boolean found which will be changed if the randomBook was randomly selected
         */
        Book randomBook = db1.randomSelection();
        boolean found = false;
        /*
        uses a for each loop to parse through every Book in db1
         */
        for (Book book : db1.toArray()) {
            /*
            loop breaks if the randomly found book was found
             */
            if (book.equals(randomBook)) {
                found = true;
                break;
            }
        }
        assertEquals(true, found);
    }

    /**
     * tests the findByTitle method
     */
    @Test
    public void testFindByTitle() {
        /*
        creates two Book objects
         */
        LibraryDatabase db2 = new LibraryDatabase(3);
        Book b1 = new Book("Book1", "1234567890123", "Author1");
        Book b2 = new Book("Book2", "7777777777777", "Author2");
        /*
        adds the three Books to db2
         */
        db2.add(b1);
        db2.add(b2);
        /*
        checks to see if Book1 and Book2 are found using the findByTitle()
        checks to see if a random Title that is not in db2 returns null
         */
        assertEquals(b1, db2.findByTitle("Book1"));
        assertEquals(b2, db2.findByTitle("Book2"));
        assertNull(db2.findByTitle("Book784889"));
    }

    /**
     * tests the findByISBN() method
     */
    @Test
    public void testFindByISBN() {
        /*
        creates two Book objects
         */
        LibraryDatabase db2 = new LibraryDatabase(2);
        Book b1 = new Book("Book1", "1234567890123", "Author1");
        Book b2 = new Book("Book2", "7777777777777", "Author2");
        /*
        adds the three Books to db2
         */
        db2.add(b1);
        db2.add(b2);
        /*
        checks to see if Book1 and Book2 are found using the findByISBN()
        checks to see if a random ISBN that is not in db2 returns null
         */
        assertEquals(b1, db2.findByISBN("1234567890123"));
        assertEquals(b2, db2.findByISBN("7777777777777"));
        assertNull(db2.findByISBN("3333333333333"));
    }

    /**
     * tests the getAllByAuthor()
     */
    @Test
    public void testGetAllByAuthor() {
        /*
        creates three Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "2222222222222", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");
        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db1 = new LibraryDatabase(3);
        db1.add(b1);
        db1.add(b2);
        db1.add(b3);
        db1.getAllByAuthor("Jake");
        /*
        creates an array of Books to hold the Books created by Jake
         */
        Book[] booksByJake = new Book[2];
        booksByJake[0] = b1;
        booksByJake[1] = b3;
        assertEquals(b1, booksByJake[0]);
        assertEquals(b3, booksByJake[1]);
    }

    /**
     * checks to see if the removeDuplicates() works
     */
    @Test
    public void testRemoveDuplicates() {
        /*
        creates three Book objects
         */
        Book b1 = new Book("Book1", "1111111111111","Jake");
        Book b2 = new Book("Book2", "1111111111111", "Emily");
        Book b3 = new Book("Book3", "3333333333333", "Jake");
        /*
        creates a new LibraryDatabase and adds b1, b2, and b3 to it
         */
        LibraryDatabase db1 = new LibraryDatabase(3);
        db1.add(b1);
        db1.add(b2);
        db1.add(b3);
        /*
        calls the remove duplicates method, which should remove b2 and keep b1 and b3
         */
        db1.removeDuplicates();
        assertEquals(b1, db1.toArray()[0]);
        assertEquals(b3, db1.toArray()[1]);
        assertNull(db1.toArray()[2]);
    }

}
