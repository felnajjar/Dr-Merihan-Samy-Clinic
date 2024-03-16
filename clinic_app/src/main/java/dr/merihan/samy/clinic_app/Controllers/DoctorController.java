package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Announcement;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.DoctorRepository;
import dr.merihan.samy.clinic_app.Services.DoctorService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService=doctorService;
    }
   
        @GetMapping("/login")
    public ModelAndView doctorLogin() {
        ModelAndView mav = new ModelAndView();
        Doctor newDoctor = new Doctor();
        mav.addObject("doctor", newDoctor);
        return mav;
    }

    @PostMapping("/login")
    public RedirectView loginprocess(@RequestParam("firstname") String firstName, @RequestParam("password") String password,
            HttpSession session) {
            Doctor dbDoctor = this.doctorService.getByfirstName(firstName);
             Boolean isPasswordMatched = BCrypt.checkpw(password, dbDoctor.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("userId", dbDoctor.getId());
            session.setAttribute("Email", dbDoctor.getEmail());
            return new RedirectView();
        } else {
            return new RedirectView();
        }
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

    
}
