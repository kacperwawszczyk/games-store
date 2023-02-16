package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.ParentAccountClass;

import java.io.IOException;

/**
 * okno glowne admina
 */
public class ControllerAdminAccount extends ParentAccountClass {

    @FXML
    private ControllerLogIn parent;


    @FXML
    private Button addGame;
    @FXML
    private Button modifyGame;
    @FXML
    private Button changePersonalData;
    @FXML
    private Button logOut;
    @FXML
    private Label usernameGetter;
    @FXML
    private Label label;


    /**
     * metoda otrzymania rodzica (ktory jest oknem logowania)
     *
     * @return
     */
    public ControllerLogIn getParent() {
        return parent;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem logowania)
     *
     * @param parent
     */
    public void setParent(ControllerLogIn parent) {
        this.parent = parent;
    }

    /**
     * metoda pobierajaca uzytkownika (z klasy nadrzednej)
     *
     * @param text
     */
    public void setData (String text) {
        usernameGetter.setText(text);
    }


    /**
     * metoda otwierajaca okno do dodawania nowych gier
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void addGameInClick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addGameView.fxml"));
        Parent root1 = loader.load();
        ControllerAddGame second = loader.getController();
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Add Game");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();
    }

    /**
     * metoda otwierajaca okno do zmian wlasciwosci gier
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void modifyGameInClick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/modifyGameView.fxml"));
        Parent root1 = loader.load();
        ControllerModifyGame second = loader.getController();
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Modify Game");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();
    }

    /**
     * metoda otwierajaca okno do zmian danych personalnych
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void changePersonalDataInClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/personalDataView.fxml"));
        Parent root1 = loader.load();
        ControllerPersonalData second = loader.getController();
        second.setParent2(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Personal Data");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();

        second.setData(usernameGetter.getText());

        Stage stage = (Stage) changePersonalData.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda ktora wylogowuje admina (cofa do okna logowania)
     *
     * @param event
     */
    @FXML
    public void logOutInClick(ActionEvent event) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }
}
