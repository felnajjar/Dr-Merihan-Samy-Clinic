package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.OneToOne;
import java.util.Objects;

public class Review {
    private int ReviewId;
    private String message;
    @OneToOne
    private Patient patient;

    public Review() {
    }

    public Review(int ReviewId, String message, Patient patient) {
        this.ReviewId = ReviewId;
        this.message = message;
        this.patient = patient;
    }

    public int getReviewId() {
        return this.ReviewId;
    }

    public void setReviewId(int ReviewId) {
        this.ReviewId = ReviewId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Review ReviewId(int ReviewId) {
        setReviewId(ReviewId);
        return this;
    }

    public Review message(String message) {
        setMessage(message);
        return this;
    }

    public Review patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return ReviewId == review.ReviewId && Objects.equals(message, review.message) && Objects.equals(patient, review.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ReviewId, message, patient);
    }

    @Override
    public String toString() {
        return "{" +
            " ReviewId='" + getReviewId() + "'" +
            ", message='" + getMessage() + "'" +
            ", patient='" + getPatient() + "'" +
            "}";
    }
    
    
}
