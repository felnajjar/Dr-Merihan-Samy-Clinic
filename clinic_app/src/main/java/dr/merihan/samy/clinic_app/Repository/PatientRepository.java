package dr.merihan.samy.clinic_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dr.merihan.samy.clinic_app.Models.Patient;
import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByEmail(String email); 

    List<Patient> findAll();
   Patient findById(int id);

}
