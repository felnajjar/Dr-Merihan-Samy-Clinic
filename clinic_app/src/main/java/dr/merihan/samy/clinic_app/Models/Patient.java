package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    public Patient() {
    }

    public Patient(int id, String firstName, String lastName, String email, String password, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Patient id(int id) {
        setId(id);
        return this;
    }

    public Patient firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Patient lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Patient email(String email) {
        setEmail(email);
        return this;
    }

    public Patient password(String password) {
        setPassword(password);
        return this;
    }

    public Patient phone(String phone) {
        setPhone(phone);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient patient = (Patient) o;
        return id == patient.id && Objects.equals(firstName, patient.firstName)
                && Objects.equals(lastName, patient.lastName) && Objects.equals(email, patient.email)
                && Objects.equals(password, patient.password) && Objects.equals(phone, patient.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, phone);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", phone='" + getPhone() + "'" +
                "}";
    }

}
