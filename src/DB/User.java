package DB;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userID;
    private Map<String,Double> ratings;

    public String getUserID() {
        return userID;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

    public User(String userID) {
        this.userID = userID;
        this.ratings = new HashMap<>();
    }
    public void addMovie(String movieID, Double rating){
        ratings.put(movieID,rating);
    }
}
