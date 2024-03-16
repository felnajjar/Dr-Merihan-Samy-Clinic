package dr.merihan.samy.clinic_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dr.merihan.samy.clinic_app.Repository.AdminRepository;

//nos elbeta3a de fadya 3shan ana bal3ab lesa 

@Service
public class AdminServices {
    @Autowired
    private AdminRepository adminRepository;

    public String addPatient(String firstName, String email, String password, String phoneNumber) {
        return "patient added successfully";
    }

    public boolean editPatient(int patientId) {
        return true;
    }

    public boolean deletePatient(int patientId) {
        return true;
    }

    public String addAdmin(String email, String username, String Password) {
        return "admin added successfully";
    }

    public boolean editAdmin(int adminId) {
        return true;
    }

    public boolean deleteAdmin(int adminId) {
        return true;
    }

}
