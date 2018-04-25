package sample.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainViewHandler {
    public Button searchBtn;
    public ComboBox genreType;
    public TextField searchText;
    public VBox searchResultVBox;

    public void searchMovie(ActionEvent actionEvent) {

    }

    public void showSearchResults(List<Movie> movies){

    }

    public void setUp(){
        String imageUrl = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/30oXQKwibh0uANGMs0Sytw3uN22.jpg";
        String movieDescription = "the best movie";
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
        addMovieEntry(imageUrl, movieDescription);
    }

    private void addMovieEntry(String imageUrl, String movieDescription) {
        HBox movieEntry = new HBox();
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);
        TextArea movieDescriptionTextArea = new TextArea();
        movieDescriptionTextArea.setText(movieDescription);
        movieDescriptionTextArea.setMinWidth(300);
        movieDescriptionTextArea.setMaxHeight(100);
        ChoiceBox rankChoiceBox = new ChoiceBox(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
        rankChoiceBox.getSelectionModel().selectFirst();
        movieEntry.getChildren().add(imageView);
        movieEntry.getChildren().add(movieDescriptionTextArea);
        movieEntry.getChildren().add(rankChoiceBox);

        searchResultVBox.getChildren().add(movieEntry);
    }

    public void clearMovies(){
        searchResultVBox.getChildren().clear();
    }
}
