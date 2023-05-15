import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtMuseumTest {

    /*
    Instantiates ArtMuseum and 10 Art objects
     */
    ArtMuseum am1 = new ArtMuseum("Le Louvre");
    Art art1 = new Art(30, 1000, 24, "Blue Sunrise", "John");
    Art art2 = new Art(48, 2000, 36, "Purple Mountains", "Jane");
    Art art3 = new Art(16, 500, 20, "Red Forest", "Samantha");
    Art art4 = new Art(24, 1200, 30, "Green Fields", "Robert");
    Art art5 = new Art(40, 3000, 48, "Orange Sunset", "Emily");
    Art art6 = new Art(18, 800, 22, "Yellow Meadow", "William");
    Art art7 = new Art(36, 1500, 42, "Pink Blossoms", "Theo");
    Art art8 = new Art(28, 900, 32, "Black Night", "Daniel");
    Art art9 = new Art(52, 4000, 60, "White Mountains", "Ava");
    Art art10 = new Art(12, 300, 16, "Brown River", "Jacob");

    /**
     * Tests the add method of ArtMuseum
     */
    @org.junit.jupiter.api.Test
    void add() {
        assertTrue(am1.add(art1));
        assertTrue(am1.add(art2));
        assertTrue(am1.add(art3));
        assertTrue(am1.add(art4));
        assertTrue(am1.add(art5));
        assertTrue(am1.add(art6));
        assertTrue(am1.add(art7));
        assertTrue(am1.add(art8));
        assertTrue(am1.add(art9));
        assertTrue(am1.add(art10));
    }

    /**
     * Tests the sort() of ArtMuseum
     */
    @org.junit.jupiter.api.Test
    void sort() {
        /*
        Adds all arts into am1
         */
        am1.add(art1);
        am1.add(art2);
        am1.add(art3);
        am1.add(art4);
        am1.add(art5);
        am1.add(art6);
        am1.add(art7);
        am1.add(art8);
        am1.add(art9);
        am1.add(art10);

        /*
        Tests the heightSorting with positive direction
         */
        List<Art> heightArray1 = am1.sort("height", 40);
        List<Art> heightArray2 = new ArrayList<>();
        heightArray2.add(art10);
        heightArray2.add(art3);
        heightArray2.add(art6);
        heightArray2.add(art4);
        heightArray2.add(art8);
        heightArray2.add(art1);
        heightArray2.add(art7);
        heightArray2.add(art5);
        heightArray2.add(art2);
        heightArray2.add(art9);
        assertEquals(heightArray2, heightArray1);

        /*
        Tests the priceSorting with negative direction
         */
        List<Art> priceArray1 = am1.sort("price", -20);
        List<Art> priceArray2 = new ArrayList<>();
        priceArray2.add(art9);
        priceArray2.add(art5);
        priceArray2.add(art2);
        priceArray2.add(art7);
        priceArray2.add(art4);
        priceArray2.add(art1);
        priceArray2.add(art8);
        priceArray2.add(art6);
        priceArray2.add(art3);
        priceArray2.add(art10);
        assertEquals(priceArray2, priceArray1);

        /*
        Tests the widthSorting in negative and positive direction
         */
        List<Art> widthArray1 = am1.sort("width", 0);
        List<Art> widthArray2 = new ArrayList<>();
        widthArray2.add(art10);
        widthArray2.add(art3);
        widthArray2.add(art6);
        widthArray2.add(art1);
        widthArray2.add(art4);
        widthArray2.add(art8);
        widthArray2.add(art2);
        widthArray2.add(art7);
        widthArray2.add(art5);
        widthArray2.add(art9);
        assertEquals(widthArray2, widthArray1);

        List<Art> widthArray3 = am1.sort("width", -1241);
        List<Art> widthArray4 = new ArrayList<>();
        widthArray4.add(art9);
        widthArray4.add(art5);
        widthArray4.add(art7);
        widthArray4.add(art2);
        widthArray4.add(art8);
        widthArray4.add(art4);
        widthArray4.add(art1);
        widthArray4.add(art6);
        widthArray4.add(art3);
        widthArray4.add(art10);
        assertEquals(widthArray4, widthArray3);

        /*
        Tests the nameSorting with positive direction
         */
        List<Art> nameArray1 = am1.sort("name", 15);
        List<Art> nameArray2 = new ArrayList<>();
        nameArray2.add(art8);
        nameArray2.add(art1);
        nameArray2.add(art10);
        nameArray2.add(art4);
        nameArray2.add(art5);
        nameArray2.add(art7);
        nameArray2.add(art2);
        nameArray2.add(art3);
        nameArray2.add(art9);
        nameArray2.add(art6);
        assertEquals(nameArray2, nameArray1);

        /*
        Tests the artistNameSorting with negative direction
         */
        List<Art> artistNameArray1 = am1.sort("artistName", -40);
        List<Art> artistNameArray2 = new ArrayList<>();
        artistNameArray2.add(art6);
        artistNameArray2.add(art7);
        artistNameArray2.add(art3);
        artistNameArray2.add(art4);
        artistNameArray2.add(art1);
        artistNameArray2.add(art2);
        artistNameArray2.add(art10);
        artistNameArray2.add(art5);
        artistNameArray2.add(art8);
        artistNameArray2.add(art9);
        assertEquals(artistNameArray2, artistNameArray1);

    }

    /**
     * Tests the findArts method
     */
    @org.junit.jupiter.api.Test
    void findArts() {
        am1.add(art1);
        am1.add(art2);
        am1.add(art3);
        am1.add(art4);
        am1.add(art5);
        am1.add(art6);
        am1.add(art7);
        am1.add(art8);
        am1.add(art9);
        am1.add(art10);
        Art art11 = new Art(4, 10000, 18, "Grey Giraffe", "Jacob");
        am1.add(art11);

        /*
        AssertEquals for artistName with 2+ Art
         */
        List<Art> a1 = am1.findArts("Jacob");
        List<Art> a2 = new ArrayList<>();
        a2.add(art10);
        a2.add(art11);
        assertEquals(a2, a1);

        /*
        AssertEquals for artistName with 1 Art
         */
        List<Art> a3 = am1.findArts("Theo");
        List<Art> a4 = new ArrayList<>();
        a4.add(art7);
        assertEquals(a4, a3);

        /*
        AssertEquals for artistName with 0 Art
         */
        List<Art> a5 = am1.findArts("Caio");
        List<Art> a6 = new ArrayList<>();
        assertEquals(a6, a5);
    }

    /**
     * Tests randomizeArts()
     */
    @org.junit.jupiter.api.Test
    void randomizeArts() {
        am1.add(art1);
        am1.add(art2);
        am1.add(art3);
        am1.add(art4);
        am1.add(art5);
        am1.add(art6);
        am1.add(art7);
        am1.add(art8);
        am1.add(art9);
        am1.add(art10);
        /*
        Randomizes am1 with the same n and checks if they are the same
        Not fully efficient because there is a slight possibility that it randomizes array8 and array7 has the same
        values as array8, but still randomizes
         */
        Art[] array7 = am1.randomizeArts(4).toArray(new Art[4]);
        Art[] array8 = am1.randomizeArts(4).toArray(new Art[4]);
        assertNotEquals(array7, array8);
        assertEquals(array7.length, array8.length);

        List<Art> a9 = am1.randomizeArts(0);
        List<Art> a10 = new ArrayList<>();
        assertEquals(a9, a10);
        List<Art> a11 = am1.randomizeArts(-40);
        assertEquals(a11, a10);

        Art[] array12 = am1.randomizeArts(8).toArray(new Art[8]);
        Art[] array13 = am1.randomizeArts(8).toArray(new Art[8]);
        assertNotEquals(array12, array13);
        assertEquals(array12.length, array13.length);

        Art[] array14 = am1.randomizeArts(237180301).toArray(new Art[10]);
        Art[] array15 = am1.randomizeArts(10).toArray(new Art[10]);
        assertNotEquals(array14, array15);
        assertEquals(array14.length, array15.length);
    }

    /**
     * Tests randomSort()
     */
    @org.junit.jupiter.api.Test
    void randomSort() {
        List<Art> a1 = new ArrayList<>();
        a1.add(art1);
        a1.add(art2);
        a1.add(art3);
        a1.add(art4);
        a1.add(art5);
        a1.add(art6);
        a1.add(art7);
        a1.add(art8);
        a1.add(art9);
        a1.add(art10);
        List<Art> a2 = am1.randomSort(a1);
        List<Art> a3 = new ArrayList<>();
        a3.add(art1);
        a3.add(art2);
        a3.add(art3);
        a3.add(art4);
        a3.add(art6);
        a3.add(art5);
        a3.add(art8);
        a3.add(art7);
        a3.add(art9);
        a3.add(art10);
        assertEquals(a3, a2);

        ArtMuseum am2 = new ArtMuseum("Giza");
        List<Art> a4 = new ArrayList<>();
        a4.add(art1);
        a4.add(art2);
        a4.add(art3);
        List<Art> a5 = am2.randomSort(a4);
        List<Art> a6 = new ArrayList<>();
        a6.add(art1);
        a6.add(art2);
        a6.add(art3);
        assertEquals(a6, a5);

        /*
        Not perfectly divisible by 5
         */
        a4.add(art4);
        a4.add(art5);
        a4.add(art6);
        a4.add(art7);
        a4.add(art8);
        a4.add(art9);
        a4.add(art10);
        Art art11 = new Art(4, 10000, 18, "Grey Giraffe", "Barry");
        a4.add(art11);
        List<Art> a7 = am2.randomSort(a4);
        List<Art> a8 = new ArrayList<>();
        a8.add(art1);
        a8.add(art2);
        a8.add(art3);
        a8.add(art4);
        a8.add(art6);
        a8.add(art5);
        a8.add(art8);
        a8.add(art7);
        a8.add(art9);
        a8.add(art11);
        a8.add(art10);
        assertEquals(a8, a7);

        /*
        Empty list
         */
        List<Art> a9 = new ArrayList<>();
        ArtMuseum am3 = new ArtMuseum("The Skies");
        List<Art> a10 = am3.randomSort(a9);
        List<Art> a11 = new ArrayList<>();
        assertEquals(a11, a10);
    }
}