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
import sample.Entities.Groups;
import sample.Entities.Users;

import javax.persistence.*;

/**
 * okno rejestracji
 */
public class ControllerNewUser {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    @FXML
    private ControllerLogIn parent;


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
     * metoda tworzaca nowego uzytkownika i otwierajaca okno glowne uzytkownika
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void applyChangesInClick(ActionEvent event) throws Exception {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Query q = em.createQuery("SELECT g from Groups g WHERE g.Group_Id = :l")
                .setParameter("l", 2);
        Groups gr = (Groups) q.getSingleResult();

        Users user = new Users();
        user.setUsername(login.getText());
        user.setPassword(ControllerLogIn.passwordToHash(password.getText()));
        user.setFirst_Name(firstName.getText());
        user.setLast_Name(lastName.getText());
        user.setAddress(address.getText());
        user.setPost_Code(postCode.getText());
        user.setCity(city.getText());
        user.setPhone(phone.getText());
        user.setEmail(email.getText());
        user.setGroup(gr);

        em.persist(user);
        transaction.begin();
        transaction.commit();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/userAccountView.fxml"));
        Parent root1 = loader.load();
        ControllerUserAccount con = loader.getController();
        con.setParent2(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("User Account");
        secondStage.setScene(new Scene(root1, 700, 400));
        secondStage.show();

        con.setData(user.getUsername());

        Stage stage = (Stage) applyChanges.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda cofajaca do okna logowania
     *
     * @param event
     */
    @FXML
    public void cancelInClick(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
