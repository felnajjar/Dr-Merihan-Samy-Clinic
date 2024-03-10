package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String userName;
    private String password;

    public Doctor() {
    }

    public Doctor(int id, String email, String userName, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor id(int id) {
        setId(id);
        return this;
    }

    public Doctor email(String email) {
        setEmail(email);
        return this;
    }

    public Doctor userName(String userName) {
        setUserName(userName);
        return this;
    }

    public Doctor password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return id == doctor.id && Objects.equals(email, doctor.email) && Objects.equals(userName, doctor.userName) && Objects.equals(password, doctor.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, userName, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }



    
}
