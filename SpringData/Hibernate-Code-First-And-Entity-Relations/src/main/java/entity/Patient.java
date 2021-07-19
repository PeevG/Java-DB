package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDateTime dateOfBirth;
    private Byte[] picture;
    private Boolean medicalInsurance;

    private GP gp;
    private Set<Visitation> visitation;

    @OneToMany(mappedBy = "patient")
    public Set<Visitation> getVisitation() {
        return visitation;
    }

    public void setVisitation(Set<Visitation> visitation) {
        this.visitation = visitation;
    }




    public Patient() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Boolean getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    @ManyToOne
    public GP getGp() {
        return gp;
    }

    public void setGp(GP gp) {
        this.gp = gp;
    }

}
