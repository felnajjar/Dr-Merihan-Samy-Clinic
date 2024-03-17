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


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    
    public AdminController(AdminService adminService,PatientService patientService,DoctorService doctorService) {
       this.adminService=adminService;
       this.patientService=patientService;
       this.doctorService=doctorService;
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

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return"deleted";
    }

    @GetMapping("/addDoctor")
    public ModelAndView addDoctor() {
        ModelAndView mav = new ModelAndView("");
        Doctor newdoctor=new Doctor();
        mav.addObject("doctor", newdoctor);
        return mav;
    }

    @PostMapping("/addDoctor")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
           doctorService.SaveDoctor(doctor);
           return "Doctor Added";
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return"deleted";
    }

    
    @GetMapping("/adminLogin")
    public ModelAndView adminLogin() {
        ModelAndView mav = new ModelAndView();
        Admin admin = new Admin();
        mav.addObject("admin", admin);
        return mav;
    }

    @PostMapping("/adminLogin")
    public RedirectView adminLoginProcess(@RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session) {
        Admin dbaAdmin = this.adminService.getAdminByEmail(email);
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbaAdmin.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("email", dbaAdmin.getEmail());
            return new RedirectView();
        } else {
            return new RedirectView();
        }

    }

}
