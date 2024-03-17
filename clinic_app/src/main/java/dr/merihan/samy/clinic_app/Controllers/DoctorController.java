package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Announcement;
import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.DoctorRepository;
import dr.merihan.samy.clinic_app.Services.AppointmentService;
import dr.merihan.samy.clinic_app.Services.DoctorService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public ModelAndView homepage() {
        return new ModelAndView("redirect:/doctor/appointments");
    }

    @GetMapping("/login")
    public ModelAndView adminLogin() {
        return new ModelAndView("doctor_login.html");
    }

    @PostMapping("/login")
    public RedirectView loginProcess(@RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {
        Doctor dbDoctor = this.doctorService.getByEmail(email);
        if (dbDoctor == null) {
            return new RedirectView("/doctor/login#loginFailed");
        }
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbDoctor.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("doctor_email", dbDoctor.getEmail());
            session.setAttribute("doctor_name", "Dr. " + dbDoctor.getFirstName() + " " + dbDoctor.getLastName());
            return new RedirectView("/doctor/appointments");
        } else {
            return new RedirectView("/doctor/login#loginFailed");
        }
    }

    @GetMapping("/appointments")
    public ModelAndView getAppointments(HttpSession session) {
        ModelAndView mav = new ModelAndView("doctor_appointments.html");
        if (session.getAttribute("doctor_email") == null) {
            return new ModelAndView("redirect:/doctor/login");
        }
        mav.addObject("doctor_email", session.getAttribute("doctor_email"));
        mav.addObject("doctor_name", session.getAttribute("doctor_name"));
        mav.addObject("page_name", "Appointments");
        return mav;
    }

    @GetMapping("/announcements")
    public ModelAndView getAnnouncements(HttpSession session) {
        ModelAndView mav = new ModelAndView("doctor_announcements.html");
        if (session.getAttribute("doctor_email") == null) {
            return new ModelAndView("redirect:/doctor/login");
        }
        mav.addObject("doctor_email", session.getAttribute("doctor_email"));
        mav.addObject("doctor_name", session.getAttribute("doctor_name"));
        mav.addObject("page_name", "Announcements");
        return mav;
    }

    @GetMapping("/setannouncement")
    public ModelAndView setAnnouncementPage() {
        return new ModelAndView("setannouncement");
    }

    @PostMapping("/setannouncement")
    public RedirectView setAnnouncement(@RequestParam("announcement") String announcementContent, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        if (userId != 0) {
            Doctor doctor = this.doctorService.getByDoctorId(userId);
            if (doctor != null) {
                Announcement announcement = new Announcement();
                announcement.message(announcementContent);
                return new RedirectView("");
            }
        }
        return new RedirectView("");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/doctor/");
    }

}
