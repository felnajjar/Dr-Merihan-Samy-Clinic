package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Announcement;
import dr.merihan.samy.clinic_app.Models.Appointment;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Services.AnnouncementService;
import dr.merihan.samy.clinic_app.Services.AppointmentService;
import dr.merihan.samy.clinic_app.Services.DoctorService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final AnnouncementService announcementService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService,
            AnnouncementService announcementService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.announcementService = announcementService;
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
        Doctor doctor = doctorService.getByEmail((String) session.getAttribute("doctor_email"));
        List<Appointment> appointments = this.appointmentService.getAppointmentsByDoctorId(doctor.getId());
        mav.addObject("doctor_email", session.getAttribute("doctor_email"));
        mav.addObject("doctor_name", session.getAttribute("doctor_name"));
        mav.addObject("page_name", "Appointments");
        mav.addObject("appointments", appointments);
        return mav;
    }

    @GetMapping("/deleteAppointment/{id}")
    public ModelAndView deleteAppointment(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("doctor_email") == null) {
            return new ModelAndView("redirect:/doctor/login");
        }
        appointmentService.deleteAppointment(id);
        return new ModelAndView("redirect:/doctor/appointments#deleted");
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
        Doctor doctor = doctorService.getByEmail((String) session.getAttribute("doctor_email"));
        List<Announcement> announcements = this.announcementService.getByDoctorId(doctor.getId());
        mav.addObject("announcements", announcements);
        return mav;
    }

    @GetMapping("/deleteAnnouncement/{id}")
    public ModelAndView deleteAnnouncement(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("doctor_email") == null) {
            return new ModelAndView("redirect:/doctor/login");
        }
        announcementService.deleteAnnouncements(id);
        return new ModelAndView("redirect:/doctor/announcements#deleted");
    }

    @PostMapping("/sendAnnouncement")
    public RedirectView setAnnouncement(@RequestParam("message") String announcementContent, HttpSession session) {
        Doctor doctor = this.doctorService.getByEmail((String) session.getAttribute("doctor_email"));
        if (doctor == null) {
            return new RedirectView("/doctor/login");
        }
        Announcement announcement = new Announcement();
        announcement.setMessage(announcementContent);
        announcement.setDoctor(doctor);
        announcement.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.announcementService.saveAnnouncement(announcement);
        return new RedirectView("/doctor/announcements#added");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/doctor/");
    }

}
