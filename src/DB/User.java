package DB;

import DB.Movie;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String ID;
    private Map<Movie, Double> ratings = new HashMap<Movie, Double>();

    public User(String id){
        this.ID=id;
    }

    public String getUserID(){
        return ID;
    }

    public Map<Movie, Double> getRatings(){
        return ratings;
    }

    public void addRating(Movie movie, double rating){
        ratings.put(movie, rating);
    }

    public boolean equals(User user){
        return this.ID.equals(user.getUserID());
    }

}
