package DB;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String [] args)
    {
        DB.connect();
        List<User> users=DB.getUsers();
        for (User u:users) {
            System.out.println(u.getUserID());
            for(Map.Entry<String,Double> e:u.getRatings().entrySet()){
                System.out.println(e.getKey()+" "+e.getValue());
            }
        }
    }
}
