package sample.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Groups {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int Group_Id;

    @Column
    private String Group_Name;

    @Column
    private int Rights;

    @OneToMany(mappedBy = "Group")
    private List<Users> Users = new ArrayList<Users>();


    public int getGroup_Id() {
        return Group_Id;
    }

    public void setGroup_Id(int group_Id) {
        Group_Id = group_Id;
    }

    public String getGroup_Name() {
        return Group_Name;
    }

    public void setGroup_Name(String group_Name) {
        Group_Name = group_Name;
    }

    public int getRights() {
        return Rights;
    }

    public void setRights(int rights) {
        Rights = rights;
    }

    public List<sample.Entities.Users> getUsers() {
        return Users;
    }

    public void setUsers(List<sample.Entities.Users> users) {
        Users = users;
    }
}
