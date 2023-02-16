package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Entities.Games;
import sample.Entities.Reviews;
import sample.Entities.Users;

import javax.persistence.*;
import java.util.Date;

public class ControllerRateGame {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    @FXML
    private ControllerGames parent;


    @FXML
    private TextArea review;
    @FXML
    private RadioButton rate1;
    @FXML
    private RadioButton rate2;
    @FXML
    private RadioButton rate3;
    @FXML
    private RadioButton rate4;
    @FXML
    private RadioButton rate5;
    @FXML
    private Button confirm;
    @FXML
    private Button back;
    @FXML
    private Label labelGame;
    @FXML
    private Label labelUser;


    public void setGame (String text) {
        labelGame.setText(text);
    }

    public void setUser (String text) {
        labelUser.setText(text);
    }

    public ControllerGames getParent() {
        return parent;
    }

    public void setParent(ControllerGames parent) {
        this.parent = parent;
    }

    @FXML
    public void confirmInClick(ActionEvent event) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Date d = new Date(System.currentTimeMillis());

        Query q1 = em.createQuery("SELECT g from Games g WHERE g.Title = :title")
                .setParameter("title", labelGame.getText());
        Games game = (Games) q1.getSingleResult();
        Query q2 = em.createQuery("SELECT u from Users u WHERE u.Username = :user")
                .setParameter("user", labelUser.getText());
        Users user = (Users) q2.getSingleResult();

        Reviews rev = new Reviews();
        if (rate1.isSelected()) {
            rev.setGrade(1);
        }
        else if (rate2.isSelected()) {
            rev.setGrade(2);
        }
        else if (rate3.isSelected()) {
            rev.setGrade(3);
        }
        else if (rate4.isSelected()) {
            rev.setGrade(4);
        }
        else if (rate5.isSelected()) {
            rev.setGrade(5);
        }
        String data = review.getText().trim();
        if (!data.equals("")) {
            rev.setComment(review.getText());
        }
        rev.setReview_Date(d);
        rev.setUser(user);
        rev.setGame(game);

        //em.persist(rev);
        transaction.begin();
        transaction.commit();

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void backInClick(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

}
