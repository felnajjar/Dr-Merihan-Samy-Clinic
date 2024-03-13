package dr.merihan.samy.clinic_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
