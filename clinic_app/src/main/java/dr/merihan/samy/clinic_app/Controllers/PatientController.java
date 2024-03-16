package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/registration")
    public String register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("password") String password, HttpSession session) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhone(phone);
        String encodedpassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        patient.setPassword(encodedpassword);
        this.patientRepository.save(patient);
        session.setAttribute("userId", patient.getId());
        session.setAttribute("firstName", patient.getFirstName());
        session.setAttribute("email", patient.getEmail());

        return "Added";
    }

    @PostMapping("/login")
    public ModelAndView loginProcess(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes) {
        Patient dbPatient = this.patientRepository.findByEmail(email);
        if (dbPatient == null) {
            return new ModelAndView("redirect:/#loginFailed");
        }
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbPatient.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("userId", dbPatient.getId());
            session.setAttribute("firstName", dbPatient.getFirstName());
            session.setAttribute("email", dbPatient.getEmail());
            return new ModelAndView("redirect:/");
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
        List<Appointment> appointments = this.appointmentRepository.findByPatientId(patientId);
        mav.addObject("appointments", appointments);
        return mav;
    }

}
