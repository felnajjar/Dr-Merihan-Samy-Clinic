package dr.merihan.samy.clinic_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Doctor;
import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByfirstName(String firstName);
    Doctor findById(int id);

}
