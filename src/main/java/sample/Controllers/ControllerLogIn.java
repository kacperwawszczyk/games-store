package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Entities.Orders;
import sample.Entities.Users;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * okno logowania
 */
public class ControllerLogIn {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");
    EntityManager em = FACTORY.createEntityManager();


    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button logIn;
    @FXML
    private Button signUp;
    @FXML
    private Button exit;


    /**
     * metoda haszujaca hasla uzytkownikow (zeby administrator bazy nie widzial hasel uzytkownikow w bazie)
     *
     * @param password
     * @return zwraca zahaszowane haslo
     * @throws NoSuchAlgorithmException
     */
    public static String passwordToHash(String password) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length/2; i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();

        return generatedPassword;
    }

    /**
     * metoda otwierajaca okno glowne uzytkownika (jesli zalogowana osoba to zwykly uzytkownik) lub admina (jesli zalogowana osoba to admin)
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void logInInClick(ActionEvent event) throws Exception {
        Query q = em.createQuery("SELECT u from Users u WHERE u.Username = :login AND u.Password = :password")
                .setParameter("login", login.getText())
                .setParameter("password", passwordToHash(password.getText()));
        Users u = (Users) q.getSingleResult();
        if (u != null) {
            if ((u.getGroup()).getGroup_Id() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/userAccountView.fxml"));
                Parent root1 = loader.load();
                ControllerUserAccount second = loader.getController();
                second.setParent(this);
                Stage secondStage = new Stage();
                secondStage.setTitle("User Account");
                secondStage.setScene(new Scene(root1, 700, 400));
                secondStage.show();

                //ControllerUserAccount con = loader.getController();
                //con.setUser(u);
                second.setData(u.getUsername());

                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/views/orderGameView.fxml"));
                Parent root2 = loader2.load();
                ControllerOrderGame con = loader2.getController();
                con.setParent2(this);
                con.setU(u);
            }
            else if ((u.getGroup()).getGroup_Id() == 1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/adminAccountView.fxml"));
                Parent root1 = loader.load();
                ControllerAdminAccount second = loader.getController();
                second.setParent(this);
                Stage secondStage = new Stage();
                secondStage.setTitle("Admin Account");
                secondStage.setScene(new Scene(root1, 700, 400));
                secondStage.show();

                second.setData(u.getUsername());
            }
        }
        else {
            System.out.println("Niepoprawne dane logowania!");
        }
    }

    /**
     * metoda pozwalajaca na rejestracje nowego uzytkownika
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void signUpInClick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/newUserView.fxml"));
        Parent root1 = loader.load();
        ControllerNewUser second = loader.getController();
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("New User");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();
    }

    /**
     * metoda wylaczajaca aplikacje
     *
     * @param event
     */
    @FXML
    public void exitInClick(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
