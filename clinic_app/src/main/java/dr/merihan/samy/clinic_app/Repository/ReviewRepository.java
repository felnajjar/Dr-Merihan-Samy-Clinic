package dr.merihan.samy.clinic_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dr.merihan.samy.clinic_app.Models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
