package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Admin;
import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.AdminRepository;
import dr.merihan.samy.clinic_app.Services.AdminService;
import dr.merihan.samy.clinic_app.Services.AppointmentService;
import dr.merihan.samy.clinic_app.Services.DoctorService;
import dr.merihan.samy.clinic_app.Services.PatientService;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AdminController(AdminService adminService, PatientService patientService, DoctorService doctorService) {
        this.adminService = adminService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public ModelAndView homepage(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin_home.html");
        if (session.getAttribute("email") == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        mav.addObject("admin_email", session.getAttribute("admin_email"));
        mav.addObject("pageName", "Dashboard");

        return mav;
    }

    @GetMapping("/addPatient")
    public ModelAndView addPatient() {
        ModelAndView mav = new ModelAndView("");
        Patient newpatient = new Patient();
        mav.addObject("patient", newpatient);
        return mav;
    }

    @PostMapping("/addPatient")
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "Patient Added";
    }

    @GetMapping("/editPatient/{id}")
    public ModelAndView editPatientForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("");
        Patient patient = patientService.getPatientById(id);
        mav.addObject("patient", patient);
        return mav;
    }

    @PostMapping("/editPatient")
    public String editPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "Patient Updated";
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return "deleted";
    }

    @GetMapping("/addDoctor")
    public ModelAndView addDoctor() {
        ModelAndView mav = new ModelAndView("");
        Doctor newdoctor = new Doctor();
        mav.addObject("doctor", newdoctor);
        return mav;
    }

    @PostMapping("/addDoctor")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
        doctorService.SaveDoctor(doctor);
        return "Doctor Added";
    }

    @GetMapping("/editDoctor/{id}")
    public ModelAndView editDoctorForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("");
        Doctor doctor = doctorService.getByDoctorId(id);
        mav.addObject("doctor", doctor);
        return mav;
    }

    @PostMapping("/editDoctor")
    public String editDoctor(@ModelAttribute Doctor doctor) {
        doctorService.SaveDoctor(doctor);
        return "Patient Updated";
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "deleted";
    }

    @GetMapping("/login")
    public ModelAndView adminLogin() {
        return new ModelAndView("admin_login.html");
    }

    @PostMapping("/login")
    public RedirectView adminLoginProcess(@RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session) {
        Admin dbaAdmin = this.adminService.getAdminByEmail(email);
        Boolean isPasswordMatched = password.equals(dbaAdmin.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("admin_email", dbaAdmin.getEmail());
        }
        return new RedirectView("/admin/");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/admin/");
    }

}
