package view;

import DataReader.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
//import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class MainViewHandler {
    public Button searchBtn;
    public ComboBox genreType;
    public TextField searchText;
    public VBox searchResultVBox;

    private Map<Integer, Double> userRates;

    public void searchMovie(ActionEvent actionEvent) {

    }

    public void showSearchResults(List<Movie> movies){

    }

    public void setUp(){
        userRates = new HashMap<>();
        DataReader dataReader = new DataReader("resources/");
        Map<Integer, Movie> movies = dataReader.getMovies();
        Map<Integer, Integer> links = dataReader.getLinks();
        Random random= new Random();
        List<Integer> movieIds= new ArrayList<Integer>(movies.keySet());
        for(int i = 0; i< 10; i++){
            Integer randomKey = movieIds.get(random.nextInt(movieIds.size()) );
            Movie movie = movies.get(randomKey.intValue());
            int movieId = randomKey.intValue();
            int tdbmId = links.get(movieId);
            String imagePath = "30oXQKwibh0uANGMs0Sytw3uN22.jpg";
            try {
                JSONObject jsonObject = JsonReader.readJsonFromUrl("https://api.themoviedb.org/3/movie/" + tdbmId + "?api_key=6323cf8bd5ee99e29a95530e11aff7af&language=en-US");
                imagePath = (String) jsonObject.get("backdrop_path");

            } catch (IOException e) {
                imagePath = "30oXQKwibh0uANGMs0Sytw3uN22.jpg";//add diffrent photo
            }
            String imageUrl = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + imagePath;
            String movieDescription = "the best movie";
            addMovieEntry(imageUrl, movie.getTitle(), movieId);
        }
    }

    private void addMovieEntry(String imageUrl, String movieDescription, int movieId) {
        HBox movieEntry = new HBox();

        movieEntry.setPadding(new Insets(15, 0, 15, 0));
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
        movieDescriptionTextArea.setEditable(false);
        ChoiceBox rankChoiceBox = new ChoiceBox(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
        rankChoiceBox.getSelectionModel().selectFirst();
        Button submitRate = new Button("Submit");
        submitRate.setMinWidth(100);
        submitRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Double selectedItem = Double.parseDouble(rankChoiceBox.getSelectionModel().getSelectedItem().toString());
                userRates.put(movieId, selectedItem);
                System.out.println("rate enterd " + movieId + " rate: " + selectedItem);
            }
        });

        movieEntry.getChildren().add(imageView);
        movieEntry.getChildren().add(movieDescriptionTextArea);
        movieEntry.getChildren().add(rankChoiceBox);
        movieEntry.getChildren().add(submitRate);


        searchResultVBox.getChildren().add(movieEntry);
    }

    public void clearMovies(){
        searchResultVBox.getChildren().clear();
    }

    public void showMovieWindow(String name){
        try {
            FXMLLoader movieViewLoader = new FXMLLoader(getClass().getResource("moviePage.fxml"));
            Parent root = movieViewLoader.load();
            Stage stage = new Stage();
            stage.setTitle("add title like the movie name");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("new window dont open");
        }
    }

}

