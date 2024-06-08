
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review , Long>{
    List<Review> findByNannyId(Long nannyId);
}
