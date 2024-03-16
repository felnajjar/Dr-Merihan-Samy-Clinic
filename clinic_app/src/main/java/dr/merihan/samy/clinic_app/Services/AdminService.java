package dr.merihan.samy.clinic_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Models.Admin;
import dr.merihan.samy.clinic_app.Repository.AdminRepository;

//nos elbeta3a de fadya 3shan ana bal3ab lesa 

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin getByEmailAndPassword(String email,String password){
        return adminRepository.findByEmailAndPassword(email, password);
    }

    public Admin getAdminById(int id){
        return adminRepository.findById(id);
    }

    public Admin getAdminByEmail(String email){
        return adminRepository.findByEmail(email);
    }
    public void saveAdmin(Admin admin){
        adminRepository.save(admin);
    }
    public void deleteAdmin(int id){
        adminRepository.deleteById(id);
    }

}
