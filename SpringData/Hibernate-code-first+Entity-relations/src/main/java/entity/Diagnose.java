package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnose")
public class Diagnose extends BaseEntity{

    private String name;
    private String comment;


    public Diagnose() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 1000, nullable = false)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
