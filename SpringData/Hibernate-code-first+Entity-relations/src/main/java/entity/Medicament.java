package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Medicament extends BaseEntity{

    private String name;


    public Medicament() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
