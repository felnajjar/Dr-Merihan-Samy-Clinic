package dr.merihan.samy.clinic_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Models.Doctor;
import dr.merihan.samy.clinic_app.Repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getByfirstName(String firstname){
        return doctorRepository.findByfirstName(firstname);
    }
    public Doctor getByDoctorId(int id){
        return doctorRepository.findById(id);
    }
    public void SaveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public void deleteDoctor(int id){
        doctorRepository.deleteById(id);
    }
}
