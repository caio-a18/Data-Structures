import java.util.*;

/**
 * {@author Caio Albuquerque}
 *
 * the ArtMuseum class
 */

public class ArtMuseum {

    /*
    private field ArrayList to store all Art objects
    private field to store museumName
     */
    private ArrayList<Art> list = new ArrayList<>();
    private String museumName;


    /**
     * ArtMuseum Constructor
     * @param museumName --> String
     */
    public ArtMuseum(String museumName) {
        this.museumName = museumName;
    }

    /**
     * add() which given an Art object, adds it to the database
     * @param art --> Art
     * @return boolean --> true if can be added, false if not
     */
    public boolean add(Art art) {
        /*
        Returns whether the art was added to the database or not
         */
        return list.add(art);
    }

    /**
     * Given a name of an attribute in the Art class,
     * sort the current collection in ascending order if direction >= 0
     * or descending order if direction < 0
     * @param attribute --> name of an attribute
     * @param direction --> ascending order or descending order
     * @return sorted list of the arts or an empty list if there is no art in database
     */
    public List<Art> sort(String attribute, int direction) {
        /*
        Calls the private helper method that sorts the list with the passed attribute
         */
        callSort(attribute, list);
        /*
        If the direction is negative, uses binary search (log(n) runtime)
         */
        if (direction < 0) {
            /*
            For loop that parses through half of list
            Calls the private helper swap method that swaps two elements in the list
            In this case it swaps the first and last then increments the first by 1 and decrements the last by 1
             */
            for (int i = 0; i < list.size() / 2; i++) {
                swap(i, list.size() - 1 - i, list);
            }
        }
        /*
        Returns the new list
         */
        return list;
    }

    /**
     * Given a name of an artist, returns all arts with the same name as input
     * @param artistName --> String
     * @return List<Art> --> list with Arts
     */
    public List<Art> findArts(String artistName) {
        /*
        Creates a new ArrayList<Art>()
         */
        ArrayList<Art> newList = new ArrayList<>();
        /*
        Parses through the current list and adds every list with the given artistName into the new list
         */
        for (Art art : list) {
            if (art.getArtistName().equals(artistName)) {
                newList.add(art);
            }
        }
        /*
        Returns the new list
         */
        return newList;
    }

    /**
     * Randomly generates a list of n Art objects, returns an empty list if n <= 0
     * @param n --> int
     * @return --> List<Art>
     */
    public List<Art> randomizeArts(int n) {
        /*
        If list is equal to or less than 0, an empty ArrayList is returned
         */
        if (n <= 0) {
            return new ArrayList<>();
        }
        /*
        If n is greater than the list.size(), sets n to size of list
         */
        if (n > list.size()) {
            n = list.size();
        }
        /*
        Instantiates a new list and shuffles the original list
         */
        ArrayList<Art> newList = new ArrayList<>();
        Collections.shuffle(list);
        /*
        For loop that parses through n elements
         */
        for (int i = 0; i < n; i++) {
            /*
            If the Art at list.get(i % list.size()) is null, decrements i
            and goes back into the for loop setting i to the value it was at
             */
            if (list.get(i % list.size()) == null) {
                i--;
            }
            /*
            If the Art at list.get(i % list.size()) is not null,
            adds the Art object into the newList
             */
            if (list.get(i % list.size()) != null) {
                newList.add(list.get(i % list.size()));
            }
        }
        /*
        Returns the newList
         */
        return newList;
    }

    /**
     * Given a list of Art objects, sort and return the list such:
     * 1/5 based on height
     * 1/5 based on price
     * 1/5 based on width
     * 1/5 based on name
     * 1/5 based on artistName
     * @param arts --> List<Art>
     * @return List<Art>
     */
    public List<Art> randomSort(List<Art> arts) {
        /*
        If the list has less than 5 elements, it returns the list
         */
        if (arts.size() < 5) {
            return arts;
        }
        /*
        Calls private helper quickSort method 5 times
        First call --> start - 1/5 --> height
        Second call --> 1/5 - 2/5 --> price
        Third call --> 2/5 - 3/5 --> width
        Fourth call --> 3/5 - 4/5 --> name
        Fifth call --> 4/5 - end --> artistName
         */
        else {
            int i = (arts.size() / 5);
            quickSort(arts, 0, i - 1, "height");
            quickSort(arts, i, i * 2 - 1, "price");
            quickSort(arts, i * 2, i * 3 - 1, "width");
            quickSort(arts, i * 3, i * 4 - 1, "name");
            quickSort(arts, i * 4, arts.size() - 1, "artistName");
        }
        /*
        Returns newly sorted list
         */
        return arts;
    }


    /**
     * Final private Helper method for the overall quicksort that is called when sorting
     * @param attribute --> String attribute to be passed
     * @param arts --> List
     */
    private static void callSort(String attribute, List<Art> arts) {
        quickSort(arts, 0, arts.size() - 1, attribute);
    }

    /**
     * Private helper method that uses quickSort for the passed list with an initial start, end, and attribute
     * @param arts --> List<Art>
     * @param start --> int
     * @param end --> int
     * @param attribute --> String
     */
    private static void quickSort(List<Art> arts, int start, int end, String attribute) {
        /*
        If the start is greater to or equal to the end, it just returns the list
         */
        if (start >= end) {
            return;
        }
        /*
        If the passed attribute is height, calls heightPartition()
        Recursively calls quick sort with qs which is the *pivot*
         */
        if (Objects.equals(attribute, "height")) {
            int qs = heightPartition(arts, start, end);
            quickSort(arts, start, qs - 1, attribute);
            quickSort(arts, qs + 1, end, attribute);
        }
        /*
        If the passed attribute is price, calls pricePartition()
        Recursively calls quick sort with qs which is the *pivot*
         */
        if (Objects.equals(attribute, "price")) {
            int qs = pricePartition(arts, start, end);
            quickSort(arts, start, qs - 1, attribute);
            quickSort(arts, qs + 1, end, attribute);
        }
        /*
        If the passed attribute is width, calls widthPartition()
        Recursively calls quick sort with qs which is the *pivot*
         */
        if (Objects.equals(attribute, "width")) {
            int qs = widthPartition(arts, start, end);
            quickSort(arts, start, qs - 1, attribute);
            quickSort(arts, qs + 1, end, attribute);
        }
        /*
        If the passed attribute is name, calls namePartition()
        Recursively calls quick sort with qs which is the *pivot*
         */
        if (Objects.equals(attribute, "name")) {
            int qs = namePartition(arts, start, end);
            quickSort(arts, start, qs - 1, attribute);
            quickSort(arts, qs + 1, end, attribute);
        }
        /*
        If the passed attribute is artistName, calls artistNamePartition()
        Recursively calls quick sort with qs which is the *pivot*
         */
        if (Objects.equals(attribute, "artistName")) {
            int qs = artistNamePartition(arts, start, end);
            /*
            quickSorts left recursively
            quickSorts right recursively
             */
            quickSort(arts, start, qs - 1, attribute);
            quickSort(arts, qs + 1, end, attribute);
        }
    }

    /**
     * heightPartition method which partitions the height
     * @param arts --> List<Art>
     * @param first --> int
     * @param last --> int
     * @return int
     */
    private static int heightPartition(List<Art> arts, int first, int last) {
        /*
        Creates a pivot from the passed arts
        Creates int s which is the right-most index value of the side it is currently on
         */
        Art curr = arts.get(last);
        int s = first - 1;
        /*
        For loop that parses from first element on the side of array until the last element
         */
        for (int t = first; t < last; t++) {
            /*
            If height at index t in arts is less than current height
            increments int s and calls swap method to swap the 2 elements
             */
            if (arts.get(t).getHeight() <= curr.getHeight()) {
                s++;
                swap(s, t, arts);
            }
        }
        /*
        Increments s again to pass onto other side and swaps the two elements
         */
        s++;
        swap(last, s, arts);
        /*
        Returns int s
         */
        return s;
    }

    /**
     * pricePartition method which partitions the price
     * @param arts --> List<Art>
     * @param first --> int
     * @param last --> int
     * @return int
     */
    private static int pricePartition(List<Art> arts, int first, int last) {
        /*
        Creates a pivot from the passed arts
        Creates int s which is the right-most index value of the side it is currently on
         */
        Art curr = arts.get(last);
        int s = first - 1;
        /*
        For loop that parses from first element on the side of array until the last element
         */
        for (int t = first; t < last; t++) {
            /*
            If price at index t in arts is less than current price
            increments int s and calls swap method to swap the 2 elements
             */
            if (arts.get(t).getPrice() <= curr.getPrice()) {
                s++;
                swap(s, t, arts);
            }
        }
        /*
        Increments s again to pass onto other side and swaps the two elements
         */
        s++;
        swap(last, s, arts);
        /*
        Returns int s
         */
        return s;
    }

    /**
     * widthPartition method which partitions the width
     * @param arts --> List<Art>
     * @param first --> int
     * @param last --> int
     * @return int
     */
    private static int widthPartition(List<Art> arts, int first, int last) {
        /*
        Creates a pivot from the passed arts
        Creates int s which is the right-most index value of the side it is currently on
         */
        Art curr = arts.get(last);
        int s = first - 1;
        /*
        For loop that parses from first element on the side of array until the last element
         */
        for (int t = first; t < last; t++) {
            /*
            If width at index t in arts is less than current width
            increments int s and calls swap method to swap the 2 elements
             */
            if (arts.get(t).getWidth() <= curr.getWidth()) {
                s++;
                swap(s, t, arts);
            }
        }
        /*
        Increments s again to pass onto other side and swaps the two elements
         */
        s++;
        swap(last, s, arts);
        /*
        Returns int s
         */
        return s;
    }

    /**
     * namePartition method which partitions the name
     * @param arts --> List<Art>
     * @param first --> int
     * @param last --> int
     * @return int
     */
    private static int namePartition(List<Art> arts, int first, int last) {
        /*
        Creates a pivot from the passed arts
        Creates int s which is the right-most index value of the side it is currently on
         */
        Art curr = arts.get(last);
        int s = first - 1;
        /*
        For loop that parses from first element on the side of array until the last element
         */
        for (int t = first; t < last; t++) {
            /*
            If name at index t in arts is less than current name (not in alphabetical order)
            increments int s and calls swap method to swap the 2 elements
             */
            if (arts.get(t).getName().compareTo(curr.getName()) < 0) {
                s++;
                swap(s, t, arts);
            }
        }
        /*
        Increments s again to pass onto other side and swaps the two elements
         */
        s++;
        swap(last, s, arts);
        /*
        Returns int s
         */
        return s;
    }

    /**
     * artistNamePartition method which partitions the artistName
     * @param arts --> List<Art>
     * @param first --> int
     * @param last --> int
     * @return int
     */
    private static int artistNamePartition(List<Art> arts, int first, int last) {
        /*
        Creates a pivot from the passed arts
        Creates int s which is the right-most index value of the side it is currently on
         */
        Art curr = arts.get(last);
        int s = first - 1;
        /*
        For loop that parses from first element on the side of array until the last element
         */
        for (int t = first; t < last; t++) {
            /*
            If artistName at index t in arts is less than current artistName (not in alphabetical order)
            increments int s and calls swap method to swap the 2 elements
             */
            if (arts.get(t).getArtistName().compareTo(curr.getArtistName()) < 0) {
                s++;
                swap(s, t, arts);
            }
        }
        /*
        Increments s again to pass onto other side and swaps the two elements
         */
        s++;
        swap(last, s, arts);
        /*
        Returns int s
         */
        return s;
    }

    /**
     * swap method which swaps two elements in List<Art> arts
     * @param j --> int
     * @param i --> int
     * @param arts --> List<Art>
     */
    private static void swap(int j, int i, List<Art> arts) {
        /*
        Art object curr which gets the j element in arts
        Sets j with i element in arts
        Sets i with curr
         */
        Art curr = arts.get(j);
        arts.set(j, arts.get(i));
        arts.set(i, curr);
    }

}

