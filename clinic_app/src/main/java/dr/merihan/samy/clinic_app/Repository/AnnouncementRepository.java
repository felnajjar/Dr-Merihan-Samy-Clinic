package dr.merihan.samy.clinic_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
     List<Announcement>findAll();
     List<Announcement> findAllByDoctorId(int id);


}
