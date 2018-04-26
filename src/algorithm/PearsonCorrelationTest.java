package algorithm;
import DB.Movie;
import DB.User;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PearsonCorrelationTest {
    @Test
    public void test1(){
        PearsonCorrelation tester = new PearsonCorrelation();
        List<String> genre = new ArrayList<String>();
        genre.add("Action");
        User userA = new User("Avi");
        User userB = new User("Eli");
        User userC = new User("Yossi");
        Movie a = new Movie(" Homot Shel Tikva", " Homot Shel Tikva", genre, "Homot Shel Tikva", "Homot Shel Tikva");
        Movie b = new Movie("Hasandak", "Hasandak", genre, "Hasandak", "Hasandak");
        Movie c = new Movie(" The Godfather", " The Godfather", genre, " The Godfather", " The Godfather");
        Movie d = new Movie("The Dark Knight", "The Dark Knight", genre, "The Dark Knight", "The Dark Knight");
        Movie e = new Movie("12 Angry Men", "12 Angry Men", genre, "12 Angry Men", "12 Angry Men");
        userA.addRating(a,8);
        userA.addRating(b,7);
        userA.addRating(c,6);

        userB.addRating(a, 8);
        userB.addRating(b, 7);
        userB.addRating(c, 5);
        userB.addRating(d, 5);
        userB.addRating(e, 3);

        userC.addRating(a, 8);
        userC.addRating(b, 7);
        userC.addRating(c, 6);
        userC.addRating(d, 5);
        userC.addRating(e, 3);

        List<User> oldUsers = new ArrayList<User>();
        oldUsers.add(userB);
        oldUsers.add(userC);
        assertEquals(userC,tester.getMaxUser(oldUsers, userA));
    }

}
