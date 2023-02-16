package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Entities.Games;
import sample.Entities.Orders;
import sample.Entities.Users;

import javax.persistence.*;

/**
 * okno do zamawiania gier
 */
public class ControllerOrderGame {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    private Users u;

    @FXML
    private ControllerGames parent;
    @FXML
    private ControllerLogIn parent2;


    @FXML
    private Label gameTitle;
    @FXML
    private Button confirm;
    @FXML
    private Button back;
    @FXML
    private Label usernameGetter;


    /**
     * metoda otrzymania rodzica (ktory jest oknem z lista gier)
     *
     * @return
     */
    public ControllerGames getParent() {
        return parent;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem z lista gier)
     *
     * @param parent
     */
    public void setParent(ControllerGames parent) {
        this.parent = parent;
    }

    /**
     * metoda otrzymania rodzica (ktory jest oknem logowania)
     *
     * @return
     */
    public ControllerLogIn getParent2() {
        return parent2;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem logowania)
     *
     * @param parent2
     */
    public void setParent2(ControllerLogIn parent2) {
        this.parent2 = parent2;
    }

    /**
     * metoda pobierajaca gre zamowiona przez uzytkownika (z klasy nadrzednej)
     *
     * @param text
     */
    public void setGame(String text) {
        gameTitle.setText(text);
    }

    /**
     * metoda otrzymania uzytkownika
     *
     * @return
     */
    public Users getU() {
        return u;
    }

    /**
     * metoda ustawiajaca uzytkownika
     *
     * @param u
     */
    public void setU(Users u) {
        this.u = u;
    }

    /**
     * metoda pobierajaca uzytkownika (z klasy nadrzednej)
     *
     * @param text
     */
    public void setUser2(String text) {
        usernameGetter.setText(text);
    }


    /**
     * metoda tworzaca nowe zamowienie z gra, ktora zamawia uzytkownik (zmniejszajac ilosc danej gry o 1 w bazie)
     *
     * @param event
     */
    @FXML
    public void confirmInClick(ActionEvent event) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Query q = em.createQuery("SELECT g from Games g WHERE g.Title = :title")
                .setParameter("title", gameTitle.getText());
        Games g = (Games) q.getSingleResult();
        Query q2 = em.createQuery("SELECT o from Orders o JOIN Games g ON g.Order = o WHERE g.Title = :title")
                .setParameter("title", gameTitle.getText());
        Orders o = (Orders) q2.getSingleResult();

        int c = g.getCount();
        c = c-1;
        g.setCount(c);
        o.setStatus(1);

        em.persist(o);
        em.persist(g);
        transaction.begin();
        transaction.commit();

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda cofajaca do okna z lista gier
     *
     * @param event
     */
    @FXML
    public void backInClick(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

}
