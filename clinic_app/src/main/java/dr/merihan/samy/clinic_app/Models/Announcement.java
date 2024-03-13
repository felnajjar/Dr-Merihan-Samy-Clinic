package dr.merihan.samy.clinic_app.Models;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private Date createdAt;

    @OneToOne
    private Admin admin;

    public Announcement() {
    }

    public Announcement(int id, String message, Date createdAt, Admin admin) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.admin = admin;
    }

    public int getAnnouncementId() {
        return this.id;
    }

    public void setAnnouncementId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Announcement id(int id) {
        setAnnouncementId(id);
        return this;
    }

    public Announcement message(String message) {
        setMessage(message);
        return this;
    }

    public Announcement createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Announcement admin(Admin admin) {
        setAdmin(admin);
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
        return id == announcement.id && Objects.equals(message, announcement.message)
                && Objects.equals(createdAt, announcement.createdAt) && Objects.equals(admin, announcement.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, createdAt, admin);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getAnnouncementId() + "'" +
                ", message='" + getMessage() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", admin='" + getAdmin() + "'" +
                "}";
    }

}
