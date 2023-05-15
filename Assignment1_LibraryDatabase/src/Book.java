/**
 * {@author Caio Albuqerque}
 * The Book class that sets the title, ISBN, and author to the
 * respective specified values.
 */
public class Book {
    /**
     * private fields for the title, isbn, and author of a Book
     */
    private String title;
    private String isbn;
    private String author;

    /**
     * creates the constructor for Book
     * Runtime is O(1)
     * @param title --> title of Book
     * @param isbn --> isbn number (string) of Book
     * @param author --> author of Book
     */
    public Book(String title, String isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    /**
     * getter for the title of the Book
     * Runtime is O(1)
     * @return title
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * getter for the isbn of the Book
     * Runtime is O(1)
     * @return isbn
     */
    public String getISBN() {
        /*
          if the isbn does not have at least 13 numeric characters,
          an IllegalArgumentException is thrown
         */
        if (this.isbn.length() == 13) {
            return this.isbn;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * getter for the author of the Book
     * Runtime is O(1)
     * @return author
     */
    public String getAuthor() {
        return this.author;
    }
}
