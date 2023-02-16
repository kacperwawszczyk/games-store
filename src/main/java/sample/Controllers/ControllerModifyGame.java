package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Entities.Categories;
import sample.Entities.Games;
import sample.Entities.Publishers;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * okno do zmiany wlasciwosci gier
 */
public class ControllerModifyGame implements Initializable {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    @FXML
    private ControllerAdminAccount parent;


    @FXML
    private ComboBox title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker releaseDate;
    @FXML
    private TextArea requirements;
    @FXML
    private TextField coverUrl;
    @FXML
    private TextField gameplayUrl;
    @FXML
    private Spinner<Integer> price;
    @FXML
    private Spinner<Integer> count;
    @FXML
    private ComboBox category;
    @FXML
    private ComboBox publisher;
    @FXML
    private Button apply;
    @FXML
    private Button cancel;

    List<Categories> tmp_cat;
    List<Publishers> tmp_pub;
    List<Games> tmp_gam;


    /**
     * metoda otrzymania rodzica (ktory jest oknem glownym admina)
     *
     * @return
     */
    public ControllerAdminAccount getParent() {
        return parent;
    }

    /**
     * metoda ustawiajaca rodzica (ktory jest oknem glownym admina)
     *
     * @param parent
     */
    public void setParent(ControllerAdminAccount parent) {
        this.parent = parent;
    }


    /**
     * metoda inicjalizujaca, ktora ustawia ComboBoxy z tytulami, kategoriami oraz wydawcami gier pobierajac je z bazy
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EntityManager em = FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT e from Categories e");
        tmp_cat = q.getResultList();

        tmp_cat.forEach(e -> category.getItems().add(e.getCategory_Name()));
        Query q2 = em.createQuery("SELECT p from Publishers p");
        tmp_pub = q2.getResultList();

        tmp_pub.forEach(p -> publisher.getItems().add(p.getPublisher_Name()));
        Query q3 = em.createQuery("SELECT g from Games g");
        tmp_gam = q3.getResultList();

        tmp_gam.forEach(g -> title.getItems().add(g.getTitle()));
    }

    /**
     * metoda zmieniajaca wlasciwosci wybranej gry
     *
     * @param event
     */
    @FXML
    public void applyInClick(ActionEvent event) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Query q = em.createQuery("SELECT g from Games g WHERE g.Title = :title")
                .setParameter("title", title.getSelectionModel().getSelectedItem());
        Games game = (Games) q.getSingleResult();


        if (!((description.getText()).equals(""))) {
            game.setDescription(description.getText());
        }
        if (releaseDate.getValue() != null) {
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(releaseDate.getValue());
            game.setRelease_Date(gettedDatePickerDate);
        }
        if (!((requirements.getText()).equals(("")))) {
            game.setRequirements(requirements.getText());
        }
        if (!((coverUrl.getText()).equals(""))) {
            game.setCover_Url(coverUrl.getText());
        }
        if (!((gameplayUrl.getText()).equals(""))) {
            game.setGameplay_Url(gameplayUrl.getText());
        }
        if (price.getValue() > 0) {
            game.setPrice(price.getValue());
        }
        if (count.getValue() > 0) {
            game.setCount(count.getValue());
        }
        if (category.getSelectionModel().getSelectedItem() != null) {
            Query q1 = em.createQuery("SELECT c from Categories c WHERE c.Category_Name = :cat")
                    .setParameter("cat", category.getSelectionModel().getSelectedItem());
            Categories cat = (Categories) q1.getSingleResult();
            game.setCategory(cat);
        }
        if (publisher.getSelectionModel().getSelectedItem() != null) {
            Query q2 = em.createQuery("SELECT p from Publishers p WHERE p.Publisher_Name = :pub")
                    .setParameter("pub", publisher.getSelectionModel().getSelectedItem());
            Publishers pub = (Publishers) q2.getSingleResult();
            game.setPublisher(pub);
        }

        em.persist(game);
        transaction.begin();
        transaction.commit();

        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda cofajaca do okna glownego admina
     *
     * @param event
     */
    @FXML
    public void cancelInClick(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
