package dr.merihan.samy.clinic_app.Models;

import java.sql.Timestamp;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp startsAt;
    private Timestamp endsAt;
    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    public Appointment() {
    }

    public Appointment(int id, Timestamp startsAt, Timestamp endsAt, Doctor doctor, Patient patient) {
        this.id = id;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.doctor = doctor;
        this.patient = patient;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartsAt() {
        return this.startsAt;
    }

    public void setStartsAt(Timestamp startsAt) {
        this.startsAt = startsAt;
    }

    public Timestamp getEndsAt() {
        return this.endsAt;
    }

    public void setEndsAt(Timestamp endsAt) {
        this.endsAt = endsAt;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment id(int id) {
        setId(id);
        return this;
    }

    public Appointment startsAt(Timestamp startsAt) {
        setStartsAt(startsAt);
        return this;
    }

    public Appointment endsAt(Timestamp endsAt) {
        setEndsAt(endsAt);
        return this;
    }

    public Appointment doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    public Appointment patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Appointment)) {
            return false;
        }
        Appointment appointment = (Appointment) o;
        return id == appointment.id && Objects.equals(startsAt, appointment.startsAt)
                && Objects.equals(endsAt, appointment.endsAt) && Objects.equals(doctor, appointment.doctor)
                && Objects.equals(patient, appointment.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startsAt, endsAt, doctor, patient);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", startsAt='" + getStartsAt() + "'" +
                ", endsAt='" + getEndsAt() + "'" +
                ", doctor='" + getDoctor() + "'" +
                ", patient='" + getPatient() + "'" +
                "}";
    }


}
