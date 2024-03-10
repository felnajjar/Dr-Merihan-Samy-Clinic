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
    private int DoctorId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String Phone;


    public Doctor() {
    }

    public Doctor(int DoctorId, String FirstName, String LastName, String Email, String Password, String Phone) {
        this.DoctorId = DoctorId;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
    }

    public int getDoctorId() {
        return this.DoctorId;
    }

    public void setDoctorId(int DoctorId) {
        this.DoctorId = DoctorId;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
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

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Doctor DoctorId(int DoctorId) {
        setDoctorId(DoctorId);
        return this;
    }

    public Doctor FirstName(String FirstName) {
        setFirstName(FirstName);
        return this;
    }

    public Doctor LastName(String LastName) {
        setLastName(LastName);
        return this;
    }

    public Doctor Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Doctor Password(String Password) {
        setPassword(Password);
        return this;
    }

    public Doctor Phone(String Phone) {
        setPhone(Phone);
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
        return DoctorId == doctor.DoctorId && Objects.equals(FirstName, doctor.FirstName) && Objects.equals(LastName, doctor.LastName) && Objects.equals(Email, doctor.Email) && Objects.equals(Password, doctor.Password) && Objects.equals(Phone, doctor.Phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DoctorId, FirstName, LastName, Email, Password, Phone);
    }

    @Override
    public String toString() {
        return "{" +
            " DoctorId='" + getDoctorId() + "'" +
            ", FirstName='" + getFirstName() + "'" +
            ", LastName='" + getLastName() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Password='" + getPassword() + "'" +
            ", Phone='" + getPhone() + "'" +
            "}";
    }

    

    
}
