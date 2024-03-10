package dr.merihan.samy.clinic_app.Models;

import jakarta.persistence.OneToOne;
import java.util.Objects;

public class Review {
    private int ReviewId;
    private String Message;
    @OneToOne
    private Patient Patient;

    public Review() {
    }

    public Review(int ReviewId, String Message, Patient Patient) {
        this.ReviewId = ReviewId;
        this.Message = Message;
        this.Patient = Patient;
    }

    public int getReviewId() {
        return this.ReviewId;
    }

    public void setReviewId(int ReviewId) {
        this.ReviewId = ReviewId;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Patient getPatient() {
        return this.Patient;
    }

    public void setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public Review ReviewId(int ReviewId) {
        setReviewId(ReviewId);
        return this;
    }

    public Review Message(String Message) {
        setMessage(Message);
        return this;
    }

    public Review Patient(Patient Patient) {
        setPatient(Patient);
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
        return ReviewId == review.ReviewId && Objects.equals(Message, review.Message) && Objects.equals(Patient, review.Patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ReviewId, Message, Patient);
    }

    @Override
    public String toString() {
        return "{" +
            " ReviewId='" + getReviewId() + "'" +
            ", Message='" + getMessage() + "'" +
            ", Patient='" + getPatient() + "'" +
            "}";
    }
    
    
}
