package DB;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String [] args)
    {
        testgetAllmovies();

    }
    public void getAllusers(){
        DB.connect();
        List<User> users=DB.getUsers();
        for (User u:users) {
            System.out.println(u.getUserID());
            for(Map.Entry<String,Double> e:u.getRatings().entrySet()){
                System.out.println(e.getKey()+" "+e.getValue());
            }
        }
    }
    public static void testgetAllmovies(){
        DB.connect();

        List<String> test=  DB.getAllMovies();
        for (String s:test
             ) {
            System.out.println(test);

        }
    }
}
