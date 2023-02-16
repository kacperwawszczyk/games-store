package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Entities.Games;
import sample.Entities.Users;
import sample.ParentAccountClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;


/**
 * okno glowne uzytkownika
 */
public class ControllerUserAccount extends ParentAccountClass {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    private Users u;


    @FXML
    private ControllerLogIn parent;
    @FXML
    private ControllerNewUser parent2;

    @FXML
    private Label text;
    @FXML
    private Label usernameGetter;
    @FXML
    private Button changePersonalData;
    @FXML
    private Button searchGames;
    @FXML
    private Button logOut;
    @FXML
    private Label label;

    private List<Games> listGames;


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
     * metoda otrzymania rodzica (ktory jest oknem rejestracji)
     *
     * @return
     */
    public ControllerNewUser getParent2() {
        return parent2;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem rejestracji)
     *
     * @param parent2
     */
    public void setParent2(ControllerNewUser parent2) {
        this.parent2 = parent2;
    }

    /**
     * metoda otrzymania uzytkownika
     *
     * @return
     */
    public Users getUser() {
        return u;
    }

    /**
     * metoda ustawiajaca uzytkownika
     *
     * @param u
     */
    public void setUser(Users u) {
        this.u = u;
    }

    /**
     * metoda pobierajaca uzytkownika (z klasy nadrzednej)
     *
     * @param text
     */
    public void setData(String text) {
        usernameGetter.setText(text);
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
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Personal Data");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();

        second.setData(usernameGetter.getText());

        Stage stage = (Stage) changePersonalData.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda otwierajaca okno listy gier
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void searchGamesInClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gamesView.fxml"));
        Parent root1 = loader.load();
        ControllerGames second = loader.getController();
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Games");
        secondStage.setScene(new Scene(root1, 700, 600));
        secondStage.show();

        second.setUser(usernameGetter.getText());
    }

    /**
     * metoda ktora wylogowuje uzytkownika (cofa do okna logowania)
     *
     * @param event
     */
    @FXML
    public void logOutInClick(ActionEvent event) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }

}
