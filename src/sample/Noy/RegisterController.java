package sample.Noy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    public TextField username ;
    @FXML
    public TextField email ;
    @FXML
    public TextField password ;
    @FXML
    public Button b_register ;
    @FXML
    public Button b_rank ;
    public boolean hasRanked = false ;

    public void gradTenMovies () throws IOException {
        Stage showTenMovie = new  Stage ();
        showTenMovie.setTitle("Rank ten movies");
        Parent root = FXMLLoader.load(getClass().getResource("Noy/RankTenMovies.fxml"));
        showTenMovie.setScene(new Scene(root, 600, 600));
        showTenMovie.initModality(Modality.WINDOW_MODAL);
        showTenMovie.setTitle("DICTIONARY");
        showTenMovie.show();


    }

}
