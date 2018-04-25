package sample.YAEL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoviePageController {
    ObservableList<String> rate= FXCollections.observableArrayList("0","1","2","3","4","5");
    @FXML
    private ChoiceBox ranking;
    @FXML
    private ImageView picture;
    @FXML
    private Label movieYear;
    @FXML
    private Label movieTitle= new Label();;
    @FXML
    private TextArea infoMovie;
    @FXML
    private ImageView moviepic;
    @FXML
    public void initialize(){
        ranking.setItems(rate);
        movieYear.setText("2016");
        movieTitle.setText("How to Be Single");
        ///infoMovie.isResizable();
        String desc="There's a right way to be single, a wrong " +"\n"+
                "way to be single, and then...there's Alice.\n" +
                " And Robin. Lucy. Meg. Tom. David. \n" +
                "New York City is full of lonely hearts seeking the right match, be it a love connection,\n" +
                " a hook-up, or something in the middle.\n" +
                " And somewhere between the teasing texts and one-night stands,\n" +
                " what these unmarrieds all have in common is the need \n" +
                "to learn how to be single in a world filled with ever-evolving definitions of love.\n" +
                " Sleeping around in the city that never sleeps was never so much fun.\n";
        infoMovie.setText(desc);
        infoMovie.setEditable(false);
//        infoMovie.setFont(new Font("Serif", Font.ITALIC, 16));
//        infoMovie.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        textArea.setOpaque(false);
//        textArea.setEditable(false);

        Image pic= new Image("https://ia.media-imdb.com/images/M/MV5BNzI4MDMwMzUwNF5BMl5BanBnXkFtZTgwMDgyNzkyNzE@._V1_SY1000_CR0,0,674,1000_AL_.jpg");
        moviepic.setImage(pic);
        moviepic.setFitHeight(200);
        moviepic.setSmooth(true);
        moviepic.setPreserveRatio(true);
    }


}
