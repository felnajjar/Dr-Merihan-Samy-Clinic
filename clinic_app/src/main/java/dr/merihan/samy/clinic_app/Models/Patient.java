package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int PatientId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String Phone;



    public Patient() {
    }

    public Patient(int PatientId, String FirstName, String LastName, String Email, String Password, String Phone) {
        this.PatientId = PatientId;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
    }

    public int getPatientId() {
        return this.PatientId;
    }

    public void setPatientId(int PatientId) {
        this.PatientId = PatientId;
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

    public Patient PatientId(int PatientId) {
        setPatientId(PatientId);
        return this;
    }

    public Patient FirstName(String FirstName) {
        setFirstName(FirstName);
        return this;
    }

    public Patient LastName(String LastName) {
        setLastName(LastName);
        return this;
    }

    public Patient Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Patient Password(String Password) {
        setPassword(Password);
        return this;
    }

    public Patient Phone(String Phone) {
        setPhone(Phone);
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
        return PatientId == patient.PatientId && Objects.equals(FirstName, patient.FirstName) && Objects.equals(LastName, patient.LastName) && Objects.equals(Email, patient.Email) && Objects.equals(Password, patient.Password) && Objects.equals(Phone, patient.Phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PatientId, FirstName, LastName, Email, Password, Phone);
    }

    @Override
    public String toString() {
        return "{" +
            " PatientId='" + getPatientId() + "'" +
            ", FirstName='" + getFirstName() + "'" +
            ", LastName='" + getLastName() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Password='" + getPassword() + "'" +
            ", Phone='" + getPhone() + "'" +
            "}";
    }
    

}
