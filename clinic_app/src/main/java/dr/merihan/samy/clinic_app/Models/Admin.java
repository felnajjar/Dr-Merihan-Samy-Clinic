package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String email;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin id(int id) {
        setId(id);
        return this;
    }

    public Admin email(String email) {
        setEmail(email);
        return this;
    }

    public Admin username(String username) {
        setUsername(username);
        return this;
    }

    public Admin password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Admin)) {
            return false;
        }
        Admin admin = (Admin) o;
        return id == admin.id && Objects.equals(email, admin.email) && Objects.equals(username, admin.username) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
 
}
