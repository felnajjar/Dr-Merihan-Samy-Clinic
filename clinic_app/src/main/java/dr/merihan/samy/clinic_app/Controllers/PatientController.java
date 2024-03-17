package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.AppointmentRepository;
import dr.merihan.samy.clinic_app.Repository.PatientRepository;
import dr.merihan.samy.clinic_app.Services.AppointmentService;
import dr.merihan.samy.clinic_app.Services.DoctorService;
import dr.merihan.samy.clinic_app.Services.PatientService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public PatientController(PatientService patientService, AppointmentService appointmentService,
            DoctorService doctorService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @PostMapping("/registration")
    public ModelAndView register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhone(phone);
        String encodedpassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        patient.setPassword(encodedpassword);
        this.patientService.savePatient(patient);
        session.setAttribute("userId", patient.getId());
        session.setAttribute("firstName", patient.getFirstName());
        session.setAttribute("email", patient.getEmail());

        return new ModelAndView("redirect:/#loginSuccess");
    }

    @PostMapping("/login")
    public ModelAndView loginProcess(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes) {
        Patient dbPatient = this.patientService.getByPatientEmail(email);
        if (dbPatient == null) {
            return new ModelAndView("redirect:/#loginFailed");
        }
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbPatient.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("userId", dbPatient.getId());
            session.setAttribute("firstName", dbPatient.getFirstName());
            session.setAttribute("email", dbPatient.getEmail());
            return new ModelAndView("redirect:/#loginSuccess");
        } else {
            return new ModelAndView("redirect:/#loginFailed");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/");
    }

    @GetMapping("/patient/{patientId}")
    public ModelAndView getAppointmentsByPatientId(@PathVariable int patientId) {
        ModelAndView mav = new ModelAndView();
        List<Appointment> appointments = this.appointmentService.getAppointmentsByPatientId(patientId);
        mav.addObject("appointments", appointments);
        return mav;
    }

    @GetMapping("/book")
    public ModelAndView bookAppointment(HttpSession session) {
        ModelAndView mav = new ModelAndView("patient_book.html");

        Appointment appointment = new Appointment();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime roundedTime = now.truncatedTo(ChronoUnit.HOURS)
                .plusMinutes((now.getMinute() / 30) * 30).plusMinutes(30);

        appointment.setStartsAt(Timestamp.valueOf(roundedTime));
        appointment.setEndsAt(Timestamp.valueOf(roundedTime.plusMinutes(30)));

        List<Doctor> doctors = this.doctorService.getAllDoctors();
        mav.addObject("doctors", doctors);
        
        mav.addObject("page_name", "Book Appointment");
        mav.addObject("firstName", session.getAttribute("firstName"));
        mav.addObject("email", session.getAttribute("email"));
        mav.addObject("userId", session.getAttribute("userId"));
        mav.addObject("appointment", appointment);
        return mav;
    }

    
    @PostMapping("/book")
    public String setAppointment(@RequestParam("startsAt") String startsAt,@RequestParam("endsAt") String endsAt,@ModelAttribute Doctor doctor,HttpSession session) {
        Appointment appointment = new Appointment();
        Timestamp startsAtDate= Timestamp.valueOf(LocalDateTime.parse(startsAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Timestamp endsAtDate= Timestamp.valueOf(LocalDateTime.parse(endsAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        appointment.setStartsAt(startsAtDate);
        appointment.setEndsAt(endsAtDate);
        appointment.setDoctor(doctor);
        Patient patient = patientService.getByPatientEmail(session.getAttribute("email").toString());
        appointment.setPatient(patient);
        boolean isSlotAvailable = appointmentService.isAppointmentSlotAvailable(appointment);
        if (!isSlotAvailable) {
            return "Appointment slot is already booked. Please choose another slot.";
        }
        this.appointmentService.saveAppointment(appointment);
        return "Appointment is set";
    }

}
