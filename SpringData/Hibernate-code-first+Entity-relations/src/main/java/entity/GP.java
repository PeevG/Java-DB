package entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "gp")
public class GP extends BaseEntity{


    public GP() {
    }

}
