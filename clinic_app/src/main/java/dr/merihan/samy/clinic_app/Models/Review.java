package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    @OneToOne
    private Patient patient;

    public Review() {
    }

    public Review(int id, String message, Patient patient) {
        this.id = id;
        this.message = message;
        this.patient = patient;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Review id(int id) {
        setId(id);
        return this;
    }

    public Review message(String message) {
        setMessage(message);
        return this;
    }

    public Review patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return id == review.id && Objects.equals(message, review.message) && Objects.equals(patient, review.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, patient);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", message='" + getMessage() + "'" +
                ", patient='" + getPatient() + "'" +
                "}";
    }

}
