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
    private Doctor Doctor;

    @OneToOne
    private Patient Patient;

    public Appointment() {
    }

    public Appointment(int AppointmentId, Date StartsAt, Date EndsAt, Doctor Doctor, Patient Patient) {
        this.AppointmentId = AppointmentId;
        this.StartsAt = StartsAt;
        this.EndsAt = EndsAt;
        this.Doctor = Doctor;
        this.Patient = Patient;
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
        return this.Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public Patient getPatient() {
        return this.Patient;
    }

    public void setPatient(Patient Patient) {
        this.Patient = Patient;
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

    public Appointment Doctor(Doctor Doctor) {
        setDoctor(Doctor);
        return this;
    }

    public Appointment Patient(Patient Patient) {
        setPatient(Patient);
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
        return AppointmentId == appointment.AppointmentId && Objects.equals(StartsAt, appointment.StartsAt) && Objects.equals(EndsAt, appointment.EndsAt) && Objects.equals(Doctor, appointment.Doctor) && Objects.equals(Patient, appointment.Patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AppointmentId, StartsAt, EndsAt, Doctor, Patient);
    }

    @Override
    public String toString() {
        return "{" +
            " AppointmentId='" + getAppointmentId() + "'" +
            ", StartsAt='" + getStartsAt() + "'" +
            ", EndsAt='" + getEndsAt() + "'" +
            ", Doctor='" + getDoctor() + "'" +
            ", Patient='" + getPatient() + "'" +
            "}";
    }
    
    
}
