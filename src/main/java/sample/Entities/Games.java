package sample.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Games")
public class Games {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceGames")
    @SequenceGenerator(name = "sequenceGames", sequenceName = "Sequence_Games", allocationSize = 1)
    @Column
    private int Game_Id;

    @Column
    private String Title;

    @Column
    private String Description;

    @Column
    private String Requirements;

    @Column
    private Date Release_Date;

    @Column
    private String Cover_Url;

    @Column
    private String Gameplay_Url;

    @Column
    private int Price;

    @Column
    private int Count;

    @ManyToOne
    @JoinColumn(name = "Orders_Order_Id")
    private Orders Order;

    @ManyToOne
    @JoinColumn(name = "Publishers_Publisher_Id")
    private Publishers Publisher;

    @ManyToOne
    @JoinColumn(name = "Categories_Category_Id")
    private Categories Category;


    public Games() {
    }

    public Games(String title, String description, String requirements, Date release_Date, int price) {
        Title = title;
        Description = description;
        Requirements = requirements;
        Release_Date = release_Date;
        Price = price;
    }

    @Override
    public String toString() {
        return "Games{" +
                "Game_Id=" + Game_Id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Requirements='" + Requirements + '\'' +
                ", Release_Date=" + Release_Date +
                ", Cover_Url='" + Cover_Url + '\'' +
                ", Gameplay_Url='" + Gameplay_Url + '\'' +
                ", Price=" + Price +
                ", Count=" + Count +
                ", Order=" + Order +
                ", Publisher=" + Publisher +
                ", Category=" + Category +
                '}';
    }

    public int getGame_Id() {
        return Game_Id;
    }

    public void setGame_Id(int game_Id) {
        Game_Id = game_Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        Requirements = requirements;
    }

    public Date getRelease_Date() {
        return Release_Date;
    }

    public void setRelease_Date(Date release_Date) {
        Release_Date = release_Date;
    }

    public String getCover_Url() {
        return Cover_Url;
    }

    public void setCover_Url(String cover_Url) {
        Cover_Url = cover_Url;
    }

    public String getGameplay_Url() {
        return Gameplay_Url;
    }

    public void setGameplay_Url(String gameplay_Url) {
        Gameplay_Url = gameplay_Url;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Orders getOrder() {
        return Order;
    }

    public void setOrder(Orders order) {
        Order = order;
    }

    public Publishers getPublisher() {
        return Publisher;
    }

    public void setPublisher(Publishers publisher) {
        Publisher = publisher;
    }

    public Categories getCategory() {
        return Category;
    }

    public void setCategory(Categories category) {
        Category = category;
    }
}
