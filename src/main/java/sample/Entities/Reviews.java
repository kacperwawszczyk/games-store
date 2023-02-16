package sample.Entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceReviews")
    @SequenceGenerator(name = "sequenceReviews", sequenceName = "Sequence_Reviews", allocationSize = 1)
    @Column
    private int Review_Id;

    @Column
    private int Grade;

    @Column
    private String Comment;

    @Column
    private Date Review_Date;

    @ManyToOne
    @JoinColumn(name = "Users_User_Id")
    private Users User;

    @ManyToOne
    @JoinColumn(name = "Games_Game_Id")
    private Games Game;


    public int getReview_Id() {
        return Review_Id;
    }

    public void setReview_Id(int review_Id) {
        Review_Id = review_Id;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Date getReview_Date() {
        return Review_Date;
    }

    public void setReview_Date(Date review_Date) {
        Review_Date = review_Date;
    }

    public Users getUser() {
        return User;
    }

    public void setUser(Users user) {
        User = user;
    }

    public Games getGame() {
        return Game;
    }

    public void setGame(Games game) {
        Game = game;
    }
}
