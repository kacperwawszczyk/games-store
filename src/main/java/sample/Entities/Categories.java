package sample.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int Category_Id;

    @Column
    private String Category_Name;

    @OneToMany(mappedBy = "Category")
    private List<Games> Games = new ArrayList<Games>();


    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int category_Id) {
        Category_Id = category_Id;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }

    public List<sample.Entities.Games> getGames() {
        return Games;
    }

    public void setGames(List<sample.Entities.Games> games) {
        Games = games;
    }
}
