package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.AppointmentRepository;
import dr.merihan.samy.clinic_app.Repository.PatientRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;







@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
     private AppointmentRepository appointmentRepository;
    @GetMapping("/Registration")
    public ModelAndView Register() {
        ModelAndView mav = new ModelAndView();
        Patient patient = new Patient();
        mav.addObject("patient", patient);
        return mav;
    }

    @PostMapping("/Registration")
    public String savePatient(@ModelAttribute Patient patient) {

        String encodedpassword = BCrypt.hashpw(patient.getPassword(), BCrypt.gensalt(12));
        patient.setPassword(encodedpassword);
        this.patientRepository.save(patient);
        return "Added";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        Patient newPatient = new Patient();
        mav.addObject("user", newPatient);
        return mav;
    }

    @PostMapping("/login")
    public RedirectView loginprocess(@RequestParam("username") String name, @RequestParam("password") String password,
            HttpSession session) {
        Patient dbPatient = this.patientRepository.findByUsername(name);
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbPatient.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("userId", dbPatient.getId());
            session.setAttribute("Email", dbPatient.getEmail());
            return new RedirectView();
        } else {
            return new RedirectView();
        }
    }

      
     @GetMapping("/patient/{patientId}")
    public ModelAndView getAppointmentsByPatientId(@PathVariable int patientId) {
        ModelAndView mav = new ModelAndView();
        List<Appointment> appointments = this.appointmentRepository.findByPatientId(patientId);
        mav.addObject("appointments", appointments);
        return mav;
    }
    
   
    
}

