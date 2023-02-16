package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Entities.*;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * okno z lista gier
 */
public class ControllerGames implements Initializable {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");
    EntityManager em = FACTORY.createEntityManager();

    @FXML
    private ControllerUserAccount parent;


    @FXML
    private TextField title;
    @FXML
    private ComboBox category;
    @FXML
    private ComboBox publisher;
    @FXML
    private TableView<Games> table;
    @FXML
    private Button back;
    @FXML
    private Button buyGame;
    @FXML
    private Button searchGame;
    @FXML
    private Label labelUser;
    @FXML
    private TableColumn<Games, String> titleCol;
    @FXML
    private TableColumn<Games, String> descriptionCol;
    @FXML
    private TableColumn<Games, String> requirementsCol;
    @FXML
    private TableColumn<Games, Date> releaseDateCol;
    @FXML
    private TableColumn<Games, Integer> priceCol;

    private List<Games> tmp;
    private List<Categories> tmp_cat;
    private List<Publishers> tmp_pub;


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
        this.parent = parent;
    }

    /**
     * metoda pobierajaca uzytkownika (z klasy nadrzednej)
     *
     * @param text
     */
    public void setUser (String text) {
        labelUser.setText(text);
    }

    /**
     * metoda inicjalizujaca, ktora ustawia ComboBoxy z kategoriami oraz wydawcami gier, a takze wypelnia TableView grami pobierajac je z bazy
     *
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Query q = em.createQuery("SELECT c from Games c");
        tmp = q.getResultList();

        tmp.forEach(g -> {
            if (g.getCount() > 0)
                table.getItems().add(g);
        });
        Query q2 = em.createQuery("SELECT e from Categories e");
        tmp_cat = q2.getResultList();

        tmp_cat.forEach(e -> category.getItems().add(e.getCategory_Name()));
        Query q3 = em.createQuery("SELECT p from Publishers p");
        tmp_pub = q3.getResultList();

        tmp_pub.forEach(p -> publisher.getItems().add(p.getPublisher_Name()));
    }

    /**
     * metoda otwierajaca okno do zamawiania gier
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void buyGameInClick(ActionEvent event) throws IOException {
        Games game_title = table.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/orderGameView.fxml"));
        Parent root1 = loader.load();
        ControllerOrderGame second = loader.getController();
        second.setParent(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("Order Game");
        secondStage.setScene(new Scene(root1, 500, 300));
        secondStage.show();

        second.setGame(game_title.getTitle());
        //second.setUser2(labelUser.getText());

        EntityTransaction transaction = em.getTransaction();
        Date d = new Date(System.currentTimeMillis());
        Query q = em.createQuery("SELECT u from Users u WHERE u.Username = :username")
                .setParameter("username", labelUser.getText());
        Users user = (Users) q.getSingleResult();
        Query q2 = em.createQuery("SELECT g from Games g WHERE g.Title = :title")
                .setParameter("title", game_title.getTitle());
        Games game = (Games) q2.getSingleResult();

        Orders order = new Orders();
        List<Games> gamesList = new ArrayList<>();
        order.setOrder_Date(d);
        order.setStatus(0);
        order.setUser(user);
        gamesList.add(game);
        order.setGames(gamesList);
        game.setOrder(order);

        em.persist(order);
        transaction.begin();
        transaction.commit();
    }

    /**
     * metoda cofajaca do okna glownego uzytkownika
     *
     * @param event
     */
    @FXML
    public void backInClick(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda filtrujaca liste gier pod katem ketegorii
     *
     * @param event
     */
    @FXML
    public void filterCategoryOnAction(ActionEvent event) {
        if (publisher.getSelectionModel().getSelectedItem() != null) {
            Query q_cat_pub = em.createQuery("SELECT g from Games g JOIN Categories c ON g.Category = c JOIN Publishers p ON g.Publisher = p WHERE c.Category_Name = :cat AND p.Publisher_Name = :pub")
                    .setParameter("cat", category.getSelectionModel().getSelectedItem())
                    .setParameter("pub", publisher.getSelectionModel().getSelectedItem());
            tmp = q_cat_pub.getResultList();
            table.getItems().clear();
            tmp.forEach(g -> {
                if (g.getCount() > 0)
                    table.getItems().add(g);
            });
        }
        else {
            Query q_cat = em.createQuery("SELECT e from Games e JOIN Categories c ON e.Category = c WHERE c.Category_Name = :cat")
                    .setParameter("cat", category.getSelectionModel().getSelectedItem());
            tmp = q_cat.getResultList();
            table.getItems().clear();

            tmp.forEach(g -> {
                if (g.getCount() > 0)
                    table.getItems().add(g);
            });
        }
    }

    /**
     * metoda filtrujaca liste gier pod katem wydawcow
     *
     * @param event
     */
    @FXML
    public void filterPublisherOnAction(ActionEvent event) {
        if (category.getSelectionModel().getSelectedItem() != null) {
            Query q_pub_cat = em.createQuery("SELECT g from Games g JOIN Categories c ON g.Category = c JOIN Publishers p ON g.Publisher = p WHERE c.Category_Name = :cat AND p.Publisher_Name = :pub")
                    .setParameter("cat", category.getSelectionModel().getSelectedItem())
                    .setParameter("pub", publisher.getSelectionModel().getSelectedItem());
            tmp = q_pub_cat.getResultList();
            table.getItems().clear();
            tmp.forEach(g -> {
                if (g.getCount() > 0)
                    table.getItems().add(g);
            });
        }
        else {
            Query q_pub = em.createQuery("SELECT e from Games e JOIN Publishers p ON e.Publisher = p WHERE p.Publisher_Name = :pub")
                    .setParameter("pub", publisher.getSelectionModel().getSelectedItem());
            tmp = q_pub.getResultList();
            table.getItems().clear();

            tmp.forEach(g -> {
                if (g.getCount() > 0)
                    table.getItems().add(g);
            });
        }
    }

    /**
     * metoda wyszukujaca gre o danej nazwie
     *
     * @param event
     */
    public void searchGameInClick(ActionEvent event) {
        Query q_game = em.createQuery("SELECT g from Games g WHERE g.Title = :game")
                .setParameter("game", title.getText());
        tmp = q_game.getResultList();
        table.getItems().clear();

        tmp.forEach(g -> {
            if (g.getCount() > 0)
                table.getItems().add(g);
        });
    }

}
