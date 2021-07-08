package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitation")
public class Visitation extends BaseEntity{

    private LocalDate date;
    private String comments;

    public Visitation() {
    }

    @Column
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
