package dr.merihan.samy.clinic_app.Models;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AnnouncementId;
    private String Message;
    private Date CreatedAt;

    @OneToOne
    private Admin Admin;

    public Announcement() {
    }

    public Announcement(int AnnouncementId, String Message, Date CreatedAt, Admin Admin) {
        this.AnnouncementId = AnnouncementId;
        this.Message = Message;
        this.CreatedAt = CreatedAt;
        this.Admin = Admin;
    }

    public int getAnnouncementId() {
        return this.AnnouncementId;
    }

    public void setAnnouncementId(int AnnouncementId) {
        this.AnnouncementId = AnnouncementId;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Date getCreatedAt() {
        return this.CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Admin getAdmin() {
        return this.Admin;
    }

    public void setAdmin(Admin Admin) {
        this.Admin = Admin;
    }

    public Announcement AnnouncementId(int AnnouncementId) {
        setAnnouncementId(AnnouncementId);
        return this;
    }

    public Announcement Message(String Message) {
        setMessage(Message);
        return this;
    }

    public Announcement CreatedAt(Date CreatedAt) {
        setCreatedAt(CreatedAt);
        return this;
    }

    public Announcement Admin(Admin Admin) {
        setAdmin(Admin);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Announcement)) {
            return false;
        }
        Announcement announcement = (Announcement) o;
        return AnnouncementId == announcement.AnnouncementId && Objects.equals(Message, announcement.Message) && Objects.equals(CreatedAt, announcement.CreatedAt) && Objects.equals(Admin, announcement.Admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AnnouncementId, Message, CreatedAt, Admin);
    }

    @Override
    public String toString() {
        return "{" +
            " AnnouncementId='" + getAnnouncementId() + "'" +
            ", Message='" + getMessage() + "'" +
            ", CreatedAt='" + getCreatedAt() + "'" +
            ", Admin='" + getAdmin() + "'" +
            "}";
    }

    
}
