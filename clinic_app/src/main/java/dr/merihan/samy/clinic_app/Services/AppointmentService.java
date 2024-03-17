package dr.merihan.samy.clinic_app.Services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Repository.AppointmentRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    
     public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

        public boolean isAppointmentSlotAvailable(Appointment appointment) {
            Doctor doctor = appointment.getDoctor();
            Timestamp startsAt = appointment.getStartsAt();
            Timestamp endsAt = appointment.getEndsAt();
        List<Appointment> overlappingAppointments = appointmentRepository.findByDoctorIdAndStartsAtBetweenOrEndsAtBetween(doctor.getId(), startsAt, endsAt, startsAt, endsAt);
        return overlappingAppointments.isEmpty();
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

	
}
