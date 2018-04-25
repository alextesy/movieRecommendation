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

    private String ID;
    private String title;
    private List<String> genre;
    private String imdbID;
    private String tmdbID;

}
