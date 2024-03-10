package dr.merihan.samy.clinic_app.Models;

import java.sql.Date;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Objects;

public class Appointment {
    private int AppointmentId;
    private Date StartsAt;
    private Date EndsAt;
    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    public Appointment() {
    }

    public Appointment(int AppointmentId, Date StartsAt, Date EndsAt, Doctor doctor, Patient patient) {
        this.AppointmentId = AppointmentId;
        this.StartsAt = StartsAt;
        this.EndsAt = EndsAt;
        this.doctor = doctor;
        this.patient = patient;
    }

    public int getAppointmentId() {
        return this.AppointmentId;
    }

    public void setAppointmentId(int AppointmentId) {
        this.AppointmentId = AppointmentId;
    }

    public Date getStartsAt() {
        return this.StartsAt;
    }

    public void setStartsAt(Date StartsAt) {
        this.StartsAt = StartsAt;
    }

    public Date getEndsAt() {
        return this.EndsAt;
    }

    public void setEndsAt(Date EndsAt) {
        this.EndsAt = EndsAt;
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

    public Appointment AppointmentId(int AppointmentId) {
        setAppointmentId(AppointmentId);
        return this;
    }

    public Appointment StartsAt(Date StartsAt) {
        setStartsAt(StartsAt);
        return this;
    }

    public Appointment EndsAt(Date EndsAt) {
        setEndsAt(EndsAt);
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
        return AppointmentId == appointment.AppointmentId && Objects.equals(StartsAt, appointment.StartsAt) && Objects.equals(EndsAt, appointment.EndsAt) && Objects.equals(doctor, appointment.doctor) && Objects.equals(patient, appointment.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AppointmentId, StartsAt, EndsAt, doctor, patient);
    }

    @Override
    public String toString() {
        return "{" +
            " AppointmentId='" + getAppointmentId() + "'" +
            ", StartsAt='" + getStartsAt() + "'" +
            ", EndsAt='" + getEndsAt() + "'" +
            ", doctor='" + getDoctor() + "'" +
            ", patient='" + getPatient() + "'" +
            "}";
    }
    
    
}
