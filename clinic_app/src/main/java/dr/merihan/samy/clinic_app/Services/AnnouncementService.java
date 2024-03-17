package dr.merihan.samy.clinic_app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.merihan.samy.clinic_app.Models.Announcement;
import dr.merihan.samy.clinic_app.Repository.AnnouncementRepository;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement>getAllAnnouncements(){
        return announcementRepository.findAll();
    }
    public List<Announcement> getByDoctorId(int id){
        return announcementRepository.findAllByDoctorId(id);
    }
    public void saveAnnouncement(Announcement announcement){
        announcementRepository.save(announcement);
    }
    public void deleteAnnouncements(int  id){
        announcementRepository.deleteById(id);
    }

}
