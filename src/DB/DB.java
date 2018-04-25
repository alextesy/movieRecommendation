package DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection conn = null;

    public static void connect() {
        try {
            // db parameters
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:src\\database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<User> getUsers(){
        try {
            Statement st = conn.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM ratings ;");
            User temp=new User(resSet.getString("userID"));
            temp.addMovie(resSet.getString("movieId"),resSet.getDouble("rating"));

            List<User> Users = new ArrayList<>();
            while (resSet.next()) {
                if(temp.getUserID().equals(resSet.getString("userID"))){
                    temp.addMovie(resSet.getString("movieId"),resSet.getDouble("rating"));


                }
                else{
                    Users.add(temp);
                    temp=new User(resSet.getString("userID"));
                    temp.addMovie(resSet.getString("movieId"),resSet.getDouble("rating"));
                }

            }
            Users.add(temp);


            return Users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
