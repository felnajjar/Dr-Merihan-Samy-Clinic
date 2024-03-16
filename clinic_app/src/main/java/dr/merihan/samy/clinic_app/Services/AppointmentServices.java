package dr.merihan.samy.clinic_app.Services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Repository.AppointmentRepository;

@Service
public class AppointmentServices {
    @Autowired
    private AppointmentRepository appointmentRepository;

       public boolean isAppointmentSlotAvailable(Date startsAt, Date endsAt){
        return true;
       }
}
