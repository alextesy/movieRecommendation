package DB;

import java.util.List;

public class Movie {
    public Movie(String ID, String title, List<String> genre, String imdbID, String tmdbID) {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.imdbID = imdbID;
        this.tmdbID = tmdbID;
    }

    public boolean equals(Movie m){
        return this.ID.equals(m.getID());
    }

    public String getID(){
        return ID;
    }

    private String ID;
    private String title;
    private List<String> genre;
    private String imdbID;
    private String tmdbID;

}
