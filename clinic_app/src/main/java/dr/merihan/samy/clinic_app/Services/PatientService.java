package dr.merihan.samy.clinic_app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Models.Patient;
import dr.merihan.samy.clinic_app.Repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public Patient getByPatientEmail(String email){
        return patientRepository.findByEmail(email);
    }
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }
    public void deletePatient(int id){
        patientRepository.deleteById(id);
    }
    public Patient getPatientById(int id) {
        return patientRepository.findById(id);
    }
}
