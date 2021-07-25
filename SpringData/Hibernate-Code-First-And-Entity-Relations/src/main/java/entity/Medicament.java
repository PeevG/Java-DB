package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Medicament extends BaseEntity {

    private String name;
    private Diagnose diagnose;
    private GP gp;


    public Medicament() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @ManyToOne
    public GP getGp() {
        return gp;
    }

    public void setGp(GP gp) {
        this.gp = gp;
    }
}
