package sample.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceUsers")
    @SequenceGenerator(name = "sequenceUsers", sequenceName = "Sequence_Users", allocationSize = 1)
    @Column
    private int User_id;

    @Column
    private String Username;

    @Column
    private String Password;

    @Column
    private String First_Name;

    @Column
    private String Last_Name;

    @Column
    private String Address;

    @Column
    private String Post_Code;

    @Column
    private String City;

    @Column
    private String Phone;

    @Column
    private String Email;

    @ManyToOne
    @JoinColumn(name = "Groups_Group_Id")
    private Groups Group;

    @OneToMany(mappedBy = "User")
    private List<Orders> Orders = new ArrayList<Orders>();


    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPost_Code() {
        return Post_Code;
    }

    public void setPost_Code(String post_Code) {
        Post_Code = post_Code;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Groups getGroup() {
        return Group;
    }

    public void setGroup(Groups group) {
        Group = group;
    }

    public List<sample.Entities.Orders> getOrders() {
        return Orders;
    }

    public void setOrders(List<sample.Entities.Orders> orders) {
        Orders = orders;
    }
}
