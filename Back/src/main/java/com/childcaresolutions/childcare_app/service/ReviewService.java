
package com.childcaresolutions.childcare_app.service;

import com.childcaresolutions.childcare_app.model.Nanny;
import com.childcaresolutions.childcare_app.model.Parent;
import com.childcaresolutions.childcare_app.model.Review;
import com.childcaresolutions.childcare_app.model.dto.request.CreateReviewDto;
import com.childcaresolutions.childcare_app.model.dto.respose.ReviewDto;
import com.childcaresolutions.childcare_app.repository.INannyRepository;
import com.childcaresolutions.childcare_app.repository.IParentRepository;
import com.childcaresolutions.childcare_app.repository.IReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewService implements IReviewService {

    private final INannyRepository nannyRepository;
    private final IReviewRepository reviewRepository;
    private final IParentRepository parentRepository;

    @Override
    public ReviewDto createReview(CreateReviewDto createReviewDto) {
        Nanny nanny = nannyRepository.findById(createReviewDto.getNannyId())
                .orElseThrow(() -> new IllegalArgumentException("Nanny not found"));
        Parent parent = parentRepository.findById(createReviewDto.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("Parent not found"));

        Review review = new Review();
        review.setNanny(nanny);
        review.setParent(parent);
        review.setRating(createReviewDto.getRating());
        review.setComment(createReviewDto.getComment());

        Review savedReview = reviewRepository.save(review);


        // Recalcula y actualiza el promedio
        double averageRating = nanny.calculateAverageRating();
        nanny.setNannyRate((int) Math.round(averageRating));
        nannyRepository.save(nanny);

        return new ReviewDto(
                savedReview.getId(),
                savedReview.getRating(),
                savedReview.getComment(),
                savedReview.getParent().getName(),
                savedReview.getParent().getPhoto()
        );
    }

    @Override
    public List<ReviewDto> getReviewByNannnyId(Long nannyId) {
        List<Review> reviews = reviewRepository.findByNannyId(nannyId);

        return reviews.stream()
                .map(review -> new ReviewDto(
                        review.getId(),
                        review.getRating(),
                        review.getComment(),
                        review.getParent().getName(),
                        review.getParent().getPhoto()
                ))
                .collect(Collectors.toList());
    }
   // @Override
   // public Review createReview(Review review) {
   //     return reviewRepository.save(review);
   // }
}
