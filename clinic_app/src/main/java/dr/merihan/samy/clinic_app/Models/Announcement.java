package dr.merihan.samy.clinic_app.Models;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private Timestamp createdAt;

    @OneToOne
    private Doctor doctor;

    public Announcement() {
    }

    public Announcement(int id, String message, Timestamp createdAt, Doctor doctor) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.doctor = doctor;
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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Announcement id(int id) {
        setId(id);
        return this;
    }

    public Announcement message(String message) {
        setMessage(message);
        return this;
    }

    public Announcement createdAt(Timestamp createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Announcement doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Announcement)) {
            return false;
        }
        Announcement announcement = (Announcement) o;
        return id == announcement.id && Objects.equals(message, announcement.message) && Objects.equals(createdAt, announcement.createdAt) && Objects.equals(doctor, announcement.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, createdAt, doctor);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", message='" + getMessage() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", doctor='" + getDoctor() + "'" +
            "}";
    }

   
}
