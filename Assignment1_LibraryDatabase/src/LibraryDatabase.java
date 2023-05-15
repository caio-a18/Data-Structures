/**
 * {@author Caio Albuqerque}
 * The Book class that sets the title, ISBN, and author to the
 * respective specified values.
 */
public class LibraryDatabase {
    /**
     * private fields that declare:
     * the array of type Book
     * the number of Books in the array
     */
    private Book[] database;
    private int size;

    /**
     * constructor for LibraryDatabase that initializes an empty
     * list that will store Book objects
     * Runtime is O(1)
     * @param capacity --> sets the capacity for the list
     */
    public LibraryDatabase(int capacity) {
        /*
          if int 'capacity' is negative, an IllegalArgumentException is thrown.
         */
        if (capacity < 0){
            throw new IllegalArgumentException("Capacity cannot be less than 0");
        }
        /*
          creates an array of type Book of size 'capacity'
         */
        this.database = new Book[capacity];
    }

    /**
     * method adds a specified book to a database then uses the private helper method
     * to sort the database by ascending numeric ISBN after the insertion
     * Runtime is O(N)
     * @param book --> book to be added into the database
     */
    public void add(Book book) {
        /*
        creates an array of Books which will update the database
         */
        Book[] arr = new Book[size() + 1];
        /*
        copies Book[] database to Book[] arr, but the last value of arr is left blank
        --> this is for the @param book to be added into the arr
         */
        for (int i = 0; i < size(); i++) {
            arr[i] = database[i];
        }
        /*
        sets the last value of the array to the new book
        updates the size to an appropriate value
        sets database equal to arr
        sorts the database by ascending numeric ISBN
         */
        arr[arr.length - 1] = book;
        size++;
        database = arr;
    }

    /**
     * method that removes a Book with a specified ISBN from the database and returns it
     * Runtime is O(N)
     * @param isbn --> uses the given param to remove a specific Book from the database
     * @return Book --> the removed Book with the given isbn
     */
    public Book remove(String isbn) {
        /*
          creates an int index to hold the index value of a Book in the array
          the for loop compares to see if the ISBN at the index is equal to the
          isbn passed in the param
          if the isbn is there, the index is set equal to the index of the one with the
          same isbn and the loop breaks
         */
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (database[i].getISBN().equals(isbn)) {
                index = i;
                break;
            }
        }
        /*
          if the index is equal to -1 after parsing through the entire loop,
          it means that the isbn in the param was not found in the database
          this then returns null
         */
        if (index == -1) {
            return null;
        }
        /*
          creates a Book removedBook and sets it equal to the value found in the for loop
         */
        Book removedBook = database[index];
        /*
          for loop that removes the Book at the index and shifts over all remaining Books
         */
        for (int x = index; x < size() - 1; x++) {
            database[x] = database[x+1];
        }
        /*
          should: sorts the database by calling the private helper method sortByAscendingNumber()
          returns the removedBook
         */
        database[database.length - 1] = null;
        size--;
        return removedBook;
    }

    /**
     * method that returns the number of Books stored in the database.
     * Runtime is O(1)
     * @return database.length --> the amount of Books currently in the database
     */
    public int size() {
        return size;
    }

    /**
     * method that returns a random Book from the database
     * Runtime is O(1)
     * @return Book --> randomly selected Book
     */
    public Book randomSelection() {
        int randomValue;
        /*
          if no books are in the database, null is returned
         */
        if (this.size() == 0) {
            return null;
        }
        /*
          since there are books inside the database,
          a book is returned from database
         */
        else {
            randomValue = (int) (Math.random() * this.size());
        }
        return database[randomValue];
    }

    /**
     * method that returns a Book with a specified title
     * Runtime is O(N) --> linearSearch
     * @param title --> given param that will be checked to see if it is in the database
     * @return Book --> the Book that has the same title as the given param: runtime is O(N)
     */
    public Book findByTitle(String title) {
        /*
          for loop that will parse through the database to see if the title is there or not
         */
        for (int i = 0; i < size() - 1; i++) {
            /*
              if the title is there, the Book with the same title is returned
             */
            if (database[i].getTitle().equals(title)) {
                return database[i];
            }
            /*
              if the title is not there, null is returned
             */
        }
        return null;
    }

    /**
     * method that returns a Book with a specified ISBN
     * Runtime is O(logN) --> binarySearch
     * @param isbn --> given param that will be checked to see if it is in the database
     * @return Book --> the Book that has the same isbn as the given param: runtime is O(logN)
     */
    public Book findByISBN(String isbn) {
        /*
          creates int head which stores the first value of the database
          creates int tail which stores the end value of the database
         */
        int head = 0;
        int tail = size() - 1;
        /*
          while loop that checks to see if the head index is less than the tail index
         */
        while (head <= tail) {
            /*
              creates a midpoint to check the value between the head and tail
             */
            int midpoint = (head + tail) / 2;
            /*
              if the isbn of the midpoint Book is equal to the passed isbn,
              the Book is returned
             */
            if (database[midpoint].getISBN().compareTo(isbn) == 0) {
                return database[midpoint];
            }
            /*
              if the isbn at the param does not equal the isbn at the Book at the midpoint,
              the else if and else statements check to see if the which way the isbn will be at
             */
            else if (isbn.compareTo(database[head].getISBN()) > 0
                && isbn.compareTo(database[midpoint].getISBN()) < 0) {
                tail = midpoint - 1;
            }
            else {
                head = midpoint + 1;
            }
        }
        /*
          returns null if the isbn at the param is not in the array
         */
        return null;
    }

    /**
     * method that finds all Books created by an author and stores them in an array
     * Runtime is O(N)
     * @param author --> given author that will be compared with each Book in the database
     * @return Book[] --> new array of Books by the same author as given in the param
     */
    public Book[] getAllByAuthor(String author) {
        /*
          creates local int 'count' which holds the value of how many Books the author has
          and places them within the newly created array
          creates an array that holds all Books of a given author
         */
        int count = 0;
        Book[] allBooksByAuthor = new Book[database.length];

        /*
          for loop that parses through the database and checks to see all the Books
          by a given author in the param
         */
        /*
          for every Book that has the same author as the given author, that Book is
          then added to the newly created array
         */
        for (Book book : database) {
            /*
              for every Book that has the same author as the given author, that Book is
              then added to the newly created array
             */
            if ((book != null) && book.getAuthor().equals(author)) {
                allBooksByAuthor[count] = book;
            }
        }
        return allBooksByAuthor;
    }

    /**
     * method that removes all Books that have the same ISBN as another Book,
     * but keeps the first Book that was added to the database
     * Runtime is O(N)
     */
    public void removeDuplicates() {

        /*
        creates a string that will be used as the hold of the duplicated ISBN
        to be removed
        creates an array of Book[] to be set to the database at the end
        creates an int count to adjust the size of the database at the end
         */
        String hold = "";
        Book[] save = new Book[database.length];
        int count = 0;

        /*
        for loop that parses through the database and saves the value of every
        duplicate, the value is then removed at the end
         */
        for (int i = 0; i < size() - 1; i++) {
            if (!hold.equals(database[i].getISBN())) {
                save[count] = database[i];
                count++;
            }
            hold = database[i].getISBN();
        }
        /*
        sets the database equal to the saved Book[]
        changes the size to the count of the Book[]
         */
        database = save;
        size = count;
    }

    /**
     * method that returns an array of all books in the database
     * Runtime is O(1)
     * @return Book[] --> array of Book that contains every book in the database
     */
    public Book[] toArray() {
        return database;
    }

    /**
     * private helper method that sorts an array of type Book by ascending numeric ISBN
     */
    private void sortByAscendingNumericISBN(Book[] arr) {
        /*
        creates a nested loop that will parse through the current value and the next value
        uses Integer.parseInt to retrieve the Integer value of the ISBN at the current value and the next value
        compares the two integer values
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int x = i + 1; x < arr.length; x++) {
                Book b1 = arr[i];
                Book b2 = arr[x];
                int value1 = Integer.parseInt(b1.getISBN());
                int value2 = Integer.parseInt(b2.getISBN());
                /*
                if the current value is greater than the next value, the values are swapped
                 */
                if (value1 > value2) {
                    Book save = arr[i];
                    arr[i] = arr[x];
                    arr[x] = save;

                }
            }
        }
    }

}
