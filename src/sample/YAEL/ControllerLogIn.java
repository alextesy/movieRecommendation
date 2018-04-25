package sample.YAEL;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogIn {// need to change in fxml file to the right controller
    @FXML
    private TextField userField ;
    @FXML
    private PasswordField pswField;

    public void login(){
        String _user=userField.getText();
        String _password=pswField.getText();
        boolean member=checkLogin(_user,_password);
        if (member)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login message");
            alert.setHeaderText(null);
            alert.setContentText("You logged in successfully");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login message");
            alert.setHeaderText(null);
            alert.setContentText("You failed to log in please try again");
            alert.showAndWait();
        }

    }
    public void clearLogin(){
        userField.clear();
        pswField.clear();
    }
    private boolean checkLogin(String user,String password)
    {
        if(user=="0")//exists in database
        {
            if (password=="0")
                return true;
            else
                return false;
        }
        return false;
    }
}
