package sample.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceOrders")
    @SequenceGenerator(name = "sequenceOrders", sequenceName = "Sequence_Orders", allocationSize = 1)
    @Column
    private int Order_Id;

    @Column
    private Date Order_Date;

    @Column
    private int Status;

    @ManyToOne
    @JoinColumn(name = "Users_User_Id")
    private Users User;

    @OneToMany(mappedBy = "Order")
    private List<Games> Games = new ArrayList<Games>();


    public Orders() {
    }

    public Orders(Date order_Date, int status, Users user, List<sample.Entities.Games> games) {
        Order_Date = order_Date;
        Status = status;
        User = user;
        Games = games;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public Date getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(Date order_Date) {
        Order_Date = order_Date;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Users getUser() {
        return User;
    }

    public void setUser(Users user) {
        User = user;
    }

    public List<sample.Entities.Games> getGames() {
        return Games;
    }

    public void setGames(List<sample.Entities.Games> games) {
        Games = games;
    }
}
