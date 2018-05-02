package view;

import DataReader.*;
import algorithm.PearsonCorrelation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
    // public Button searchBtn;
    //public ComboBox genreType;
    //public TextField searchText;
    public VBox searchResultVBox;
    @FXML
    private Button rndBtn;
    @FXML
    private Button genrBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchText;

    private Map<Integer, Double> userRates;
    private DataReader dataReader;


    public void searchMovie() {
        String userInput = searchText.getText().toString();
        if (userInput=="")
            return;
        System.out.println(userInput);
        boolean flag=false;
        //need to add te list of movie titles
        for (Map.Entry<Integer,Movie> m:dataReader.getMovies().entrySet()) {
            if (m.getValue().getTitle().toLowerCase().contains(userInput.toLowerCase())) {
                clearMovies();
                String url = getMoviePictureUrl(m.getValue(), m.getKey());
                addMovieEntry(url, m.getValue().getTitle(), m.getKey());
                flag=true;
                break;
            }
        }
        if (!flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Movie not found \n please change your search input to a valid movie title");
            alert.show();
        }

    }

    public void setUp() {

        searchBtn.setOnAction(e -> searchMovie());
        userRates = new HashMap<>();
        dataReader = new DataReader("resources/");
        generateRandomMovies(10);
    }


    private void generateRandomMovies(int movieLimit) {

        Map<Integer, Movie> movies = dataReader.getMovies();
        List<MovieEntry> entries=new ArrayList<>();
        Random random = new Random();
        List<Integer> movieIds = new ArrayList<Integer>(movies.keySet());
        for (int i = 0; i < movieLimit; i++) {
            Integer randomKey = movieIds.get(random.nextInt(movieIds.size()));
            Movie movie = movies.get(randomKey.intValue());
            int movieId = randomKey.intValue();
            String imagePath = "30oXQKwibh0uANGMs0Sytw3uN22.jpg";
            String imageUrl = getMoviePictureUrl(movie, movieId);
            if (imageUrl == null) continue;
            // String movieDescription = "the best movie";
            entries.add(new MovieEntry(imageUrl,movie.getTitle(),movieId));
            //addMovieEntry(imageUrl, movie.getTitle(), movieId);
        }
        for (MovieEntry m:entries) {
            addMovieEntry(m.imageUrl,m.title,m.ID);
        }

    }

    private String getMoviePictureUrl(Movie movie, int movieId) {
        Map<Integer, Integer> links = dataReader.getLinks();
        String imagePath;
        try {
            int tdbmId = links.get(movieId);
            JSONObject jsonObject = JsonReader.readJsonFromUrl("https://api.themoviedb.org/3/movie/" + tdbmId + "?api_key=6323cf8bd5ee99e29a95530e11aff7af&language=en-US");

            imagePath = (String) jsonObject.get("backdrop_path");
        } catch (Exception e) {
            imagePath = "https://png.pngtree.com/element_origin_min_pic/16/09/08/2057d15a050b0d1.jpg";
            addMovieEntry(imagePath, movie.getTitle(), movieId);
            return null;
        }
        String imageUrl = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + imagePath;
        return imageUrl;
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
            @Override
            public void handle(ActionEvent e) {
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

    public void clearMovies() {
        searchResultVBox.getChildren().clear();
    }

    public void showRecommndation() {

        if(userRates.size()<10)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You have to Rate at least 10 movies");
            alert.show();
            return;
        }

//        rndBtn.setDisable(true);
//        genrBtn.setDisable(true);
        PearsonCorrelation PC = new PearsonCorrelation();
        List<Integer> topMovies = PC.getTopMovies(dataReader.getRates(), userRates);
        Parent root;
        try {
            FXMLLoader RecommandetionsLoader = new FXMLLoader(getClass().getResource("Recommendations.fxml"));
            root = RecommandetionsLoader.load();
            RecommendationsHandler controller = RecommandetionsLoader.getController();
            controller.setDataReader(dataReader);
            controller.showMovies(topMovies);
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        rndBtn.setDisable(false);
//        genrBtn.setDisable(false);
    }

    public void showRandomMovies(ActionEvent actionEvent) {

     //   rndBtn.setDisable(true);
       // genrBtn.setDisable(true);
            clearMovies();
            generateRandomMovies(10);

       // rndBtn.setDisable(false);
        //genrBtn.setDisable(false);
    }
}

