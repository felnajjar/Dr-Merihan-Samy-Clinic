package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int AdminId;
    private String Email;
    private String Password;


    public Admin() {
    }

    public Admin(int AdminId, String Email, String Password) {
        this.AdminId = AdminId;
        this.Email = Email;
        this.Password = Password;
    }

    public int getAdminId() {
        return this.AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Admin AdminId(int AdminId) {
        setAdminId(AdminId);
        return this;
    }

    public Admin Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Admin Password(String Password) {
        setPassword(Password);
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
        return AdminId == admin.AdminId && Objects.equals(Email, admin.Email) && Objects.equals(Password, admin.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AdminId, Email, Password);
    }

    @Override
    public String toString() {
        return "{" +
            " AdminId='" + getAdminId() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Password='" + getPassword() + "'" +
            "}";
    }

   
   
 
}
