package sample.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publishers")
public class Publishers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int Publisher_Id;

    @Column
    private String Publisher_Name;

    @OneToMany(mappedBy = "Publisher")
    private List<Games> Games = new ArrayList<Games>();


    public int getPublisher_Id() {
        return Publisher_Id;
    }

    public void setPublisher_Id(int publisher_Id) {
        Publisher_Id = publisher_Id;
    }

    public String getPublisher_Name() {
        return Publisher_Name;
    }

    public void setPublisher_Name(String publisher_Name) {
        Publisher_Name = publisher_Name;
    }

    public List<sample.Entities.Games> getGames() {
        return Games;
    }

    public void setGames(List<sample.Entities.Games> games) {
        Games = games;
    }
}
