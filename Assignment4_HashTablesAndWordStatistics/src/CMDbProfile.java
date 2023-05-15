/**
 * {@author Caio Albuquerque}
 *
 * the CMDbProfile class that stores movies and ratings for a user
 * uses the PriorityQueue class to implement such actions
 */
public class CMDbProfile {

    /*
    Private field for userName of CMDbProfile
     */
    private String userName;

    /**
     * Constructor for CMDbProfile
     * @param userName --> String
     */
    public CMDbProfile(String userName) {
        this.userName = userName;
    }

    /**
     * Setter method for userName
     * @param userName --> String
     */
    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public boolean rate(String movie, int rating) {
        return false;
    }

    public boolean changeRating(String movie, int newRating) {
        return false;
    }

    public boolean removeRating(String movie) {
        return false;
    }

    public String[] favorite() {
        return null;
    }

    public String[] favorite(int k) {
        return null;
    }

    public String profileInformation() {
        return null;
    }

    /**
     * Method that compares 2 object instances
     * @param o --> object to be compared
     * @return boolean --> true if equal, false if not equal
     */
    public boolean equals(Object o) {
        /*
        If o is an instanceof CMDbProfile
         */
        if (o instanceof CMDbProfile) {
            /*
            Typecasts o to CMDbProfile
            Returns true or false depending on:
            if the username of CMDbProfile c is equal to o
             */
            CMDbProfile c = (CMDbProfile) o;
            return c.getUserName() == this.getUserName();
        }
        else {
            return false;
        }
    }

    /**
     * Private helper method to get userName
     * @return userName
     */
    private String getUserName() {
        return this.userName;
    }

    /**
     * Private helper method to set userName
     * @param userName --> userName to be set
     */
    private void setUserName(String userName) {
        this.userName = userName;
    }
}
