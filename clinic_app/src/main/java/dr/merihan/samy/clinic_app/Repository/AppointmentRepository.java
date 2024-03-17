package dr.merihan.samy.clinic_app.Repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByPatientId(int patientId);

    List<Appointment> findByDoctorId(int doctorId);

    List<Appointment> findByDoctorIdAndStartsAtBetweenOrEndsAtBetween(int doctorId, Timestamp startsAt1, Timestamp endsAt1, Timestamp startsAt2, Timestamp endsAt2);
   

}
