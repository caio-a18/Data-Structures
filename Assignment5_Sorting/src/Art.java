/**
 * {@author Caio Albuquerque}
 *
 * the Art class that will be used in Cleveland Museum of Art Class
 */

public class Art {

    /*
    Private fields of type int and String used in Art class
     */
    private int height;
    private int price;
    private int width;
    private String name;
    private String artistName;

    /**
     * Art Constructor with appropriate variables
     * @param height --> int
     * @param price --> int
     * @param width --> int
     * @param name --> String
     * @param artistName --? String
     */
    public Art(int height, int price, int width, String name, String artistName) {
        this.height = height;
        this.price = price;
        this.width = width;
        this.name = name;
        this.artistName = artistName;
    }

    /**
     * Getter method for height
     * @return height --> int
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Setter method for height
     * @param height --> int
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter method for price
     * @return price --> int
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Setter method for price
     * @param price --> int
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter method for width
     * @return width --> int
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setter method for width
     * @param width --> int
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter method for name
     * @return name --> String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for name
     * @param name --> String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for artistName
     * @return artistName --> String
     */
    public String getArtistName() {
        return this.artistName;
    }

    /**
     * Setter method for artistName
     * @param artistName --> String
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}
