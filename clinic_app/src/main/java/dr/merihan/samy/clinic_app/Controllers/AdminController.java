package dr.merihan.samy.clinic_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dr.merihan.samy.clinic_app.Models.Admin;
import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.AdminRepository;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired 
    private AdminRepository adminRepository;

    @GetMapping("/addPatient")
    public ModelAndView addPatient() {
       ModelAndView mav=new ModelAndView("");
       Patient newpatient=new Patient();
       mav.addObject("patient", newpatient);
       return mav;
    }
    @PostMapping("/addPatient")
    public String savePatient(@ModelAttribute Patient patient) {
       //na2es a save lama a create patient repo 
        return"Patient Added successfully";
    }
    @GetMapping("/addAdmin")
    public ModelAndView addAdmin() {
       ModelAndView mav=new ModelAndView("");
       Admin newAdmin=new Admin();
       mav.addObject("Admin", newAdmin);
       return mav;
    }
    
    @PostMapping("/addAdmin")
    public String save(@ModelAttribute Admin admin) {
        this.adminRepository.save(admin);
        return "Admin added successfully";
    }
    @DeleteMapping("/deletePatient/{patientID}")
    public String deletePatient(@PathVariable int id){
        //if patient exists through given id then delete
        //if not then display error message 
        return""; 
    }

    @DeleteMapping("/deleteAdmin/{adminID}")
    public String deleteAdmin(@PathVariable int id){
        //if admin exists through given id then delete
        //if not then display error message 
        return""; 
    }

    @GetMapping("/adminLogin")
    public ModelAndView adminLogin() {
        ModelAndView mav = new ModelAndView();
        Admin admin=new Admin();
        mav.addObject("admin", admin);
        return mav;
    }
    @PostMapping("/adminLogin")
    public RedirectView adminLoginProcess(@RequestParam ("email") String email,@RequestParam ("password")String password,HttpSession session) {
       Admin dbaAdmin= this.adminRepository.findByEmail(email);
       Boolean isPasswordMatched=BCrypt.checkpw(password, dbaAdmin.getPassword());
       if(isPasswordMatched){
            session.setAttribute("email",dbaAdmin.getEmail() );
            return new RedirectView();
       }else{
            return new RedirectView();
       }

    }

  
    
    
    
}
