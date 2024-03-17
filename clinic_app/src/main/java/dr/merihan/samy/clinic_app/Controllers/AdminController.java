package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public ModelAndView homepage() {
        return new ModelAndView("redirect:/admin/patients");
    }

    @GetMapping("/login")
    public ModelAndView adminLogin() {
        return new ModelAndView("admin_login.html");
    }

    @PostMapping("/login")
    public RedirectView adminLoginProcess(@RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session) {
        Admin dbaAdmin = this.adminService.getAdminByEmail(email);
        if (dbaAdmin == null) {
            return new RedirectView("/admin/login#loginFailed");
        }
        Boolean isPasswordMatched = password.equals(dbaAdmin.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("admin_email", dbaAdmin.getEmail());
            return new RedirectView("/admin/");
        } else {
            return new RedirectView("/admin/login#loginFailed");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/admin/");
    }

    // Patients CRUD
    //////////////////////////////

    @GetMapping("/patients")
    public ModelAndView patients(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin_patients.html");
        if (session.getAttribute("admin_email") == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        mav.addObject("admin_email", session.getAttribute("admin_email"));
        mav.addObject("page_name", "Patients");
        mav.addObject("patients", patientService.getAllPatients());
        return mav;
    }

    @GetMapping("/editPatient/{id}")
    public ModelAndView editPatientForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("");
        Patient patient = patientService.getPatientById(id);
        mav.addObject("patient", patient);
        return mav;
    }

    @PostMapping("/editPatient")
    public ModelAndView editPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return new ModelAndView("redirect:/admin/patients");
    }

    @GetMapping("/deletePatient/{id}")
    public ModelAndView deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return new ModelAndView("redirect:/admin/patients");
    }

    // Doctor CRUD
    //////////////////////////////

    @GetMapping("/doctors")
    public ModelAndView doctors(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin_doctors.html");
        if (session.getAttribute("admin_email") == null) {
            return new ModelAndView("redirect:/admin/login");
        }
        mav.addObject("admin_email", session.getAttribute("admin_email"));
        mav.addObject("page_name", "Doctors");
        mav.addObject("doctors", doctorService.getAllDoctors());
        return mav;
    }

    @PostMapping("/addDoctor")
    public ModelAndView saveDoctor(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
    @RequestParam("email") String email, @RequestParam("phone") String phone,
    @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Doctor doctor=new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setEmail(email);
        doctor.setPhone(phone);
        String encodedpassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        doctor.setPassword(encodedpassword);
        this.doctorService.SaveDoctor(doctor);
        session.setAttribute("userId", doctor.getId());
        session.setAttribute("firstName", doctor.getFirstName());
        session.setAttribute("email", doctor.getEmail());

        return new ModelAndView("redirect:/admin/doctors");
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

    @GetMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "deleted";
    }

}
