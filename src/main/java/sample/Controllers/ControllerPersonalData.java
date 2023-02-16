package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Entities.Users;
import sample.ParentAccountClass;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

/**
 * okno do zmiany danych personalnych
 */
public class ControllerPersonalData {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    private Users User;

    private ParentAccountClass par;


    @FXML
    private ControllerUserAccount parent;
    @FXML
    private ControllerAdminAccount parent2;


    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField postCode;
    @FXML
    private TextField city;
    @FXML
    private TextField phone;
    @FXML
    private Button applyChanges;
    @FXML
    private Button cancel;
    @FXML
    private Label usernameGetter;
    @FXML
    private Label label;


    /**
     * metoda otrzymania rodzica (ktory jest oknem glownym uzytkownika)
     *
     * @return
     */
    public ControllerUserAccount getParent() {
        return parent;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem glownym uzytkownika)
     *
     * @param parent
     */
    public void setParent(ControllerUserAccount parent) {
        this.par = parent;
    }

    /**
     * metoda otrzymania rodzica (ktory jest oknem glownym admina)
     *
     * @return
     */
    public ControllerAdminAccount getParent2() {
        return parent2;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem glownym admina)
     *
     * @param parent2
     */
    public void setParent2(ControllerAdminAccount parent2) {
        this.par = parent2;
    }

    /**
     * metoda otrzymania uzytkownika
     *
     * @return
     */
    public Users getUser() {
        return User;
    }

    /**
     * metoda ustawiajaca uzytkownika
     *
     * @param user
     */
    public void setUser(Users user) {
        User = user;
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
     * metoda zmieniajaca dane personalne uzytkownika
     *
     * @param event
     * @throws NoSuchAlgorithmException
     */
    @FXML
    public void applyChangesInClick(ActionEvent event) throws NoSuchAlgorithmException {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Query q = em.createQuery("SELECT u from Users u WHERE u.Username = :username")
                .setParameter("username", usernameGetter.getText());
        Users user = (Users) q.getSingleResult();

        if (!((login.getText()).equals(""))) {
            user.setUsername(login.getText());
        }
        if (!((password.getText()).equals(""))) {
            user.setPassword(ControllerLogIn.passwordToHash(password.getText()));
        }
        if (!((firstName.getText()).equals(""))) {
            user.setFirst_Name(firstName.getText());
        }
        if (!((lastName.getText()).equals(""))) {
            user.setLast_Name(lastName.getText());
        }
        if (!((address.getText()).equals(""))) {
            user.setLast_Name(address.getText());
        }
        if (!((postCode.getText()).equals(""))) {
            user.setPost_Code(postCode.getText());
        }
        if (!((city.getText()).equals(""))) {
            user.setCity(city.getText());
        }
        if (!((phone.getText()).equals(""))) {
            user.setPhone(phone.getText());
        }
        if (!((email.getText()).equals(""))) {
            user.setEmail(email.getText());
        }

        em.persist(user);
        transaction.begin();
        transaction.commit();

        Stage stage = (Stage) applyChanges.getScene().getWindow();
        stage.close();
        Stage st = (Stage) par.label.getScene().getWindow();
        st.show();
    }

    /**
     * metoda cofajaca do okna glownego uzytkownika badz admina (w zaleznosci kto w to okno wszedl) - dziedziczenie
     *
     * @param event
     */
    @FXML
    public void cancelInClick(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Stage st = (Stage) par.label.getScene().getWindow();
        st.show();
    }

}
