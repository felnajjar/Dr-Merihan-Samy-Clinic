package dr.merihan.samy.clinic_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmailAndPassword(String email, String password);

    List<Admin> findById(int id);

    Admin findByEmail(String email);

}
