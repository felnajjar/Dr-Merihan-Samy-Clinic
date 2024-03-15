package dr.merihan.samy.clinic_app.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByPatientId(int patientId);

    boolean isAppointmentSlotAvailable(Date startsAt, Date endsAt);

   

}
