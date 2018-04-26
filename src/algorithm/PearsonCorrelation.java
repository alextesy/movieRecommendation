package algorithm;


import DB.Movie;
import DB.User;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.*;


public class PearsonCorrelation {
    private final static PearsonsCorrelation PC = new PearsonsCorrelation();

    private double getCorrelation(double[] userA, double[] userB){
        return Math.abs(PC.correlation(userA, userB));
    }

    public User getMaxUser(List<User> users, User newUser){
        User userMaxMatch = null;
        double maxCorrelation = 0;
        for (User u : users){
            Set<Movie> matchMovies = new HashSet<Movie>(newUser.getRatings().keySet());
            matchMovies.retainAll(u.getRatings().keySet());
            double[] newUserRating = new double[matchMovies.size()];
            double[] oldUserRating = new double[matchMovies.size()];
            int counter = 0;
            for(Movie m : matchMovies){
                newUserRating[counter] = newUser.getRatings().get(m);
                oldUserRating[counter] = u.getRatings().get(m);
                counter++;
            }
            double correlation = getCorrelation(newUserRating,oldUserRating);
            if(correlation>maxCorrelation){
                maxCorrelation = correlation;
                userMaxMatch = u;
            }
        }
        return userMaxMatch;
    }
}