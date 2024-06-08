
package com.childcaresolutions.childcare_app.controller;

import com.childcaresolutions.childcare_app.model.dto.request.CreateReviewDto;
import com.childcaresolutions.childcare_app.model.dto.respose.ReviewDto;
import com.childcaresolutions.childcare_app.service.IReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/review")
@AllArgsConstructor
@RestController
public class ReviewController {

    private final IReviewService reviewService;

    @PostMapping("/create")
    public ReviewDto createReview(@RequestBody CreateReviewDto createReviewDto) {
        return reviewService.createReview(createReviewDto);
    }

    @GetMapping("/nanny/{nannyId}")
    public List<ReviewDto> getReviewsByNannyId(@PathVariable Long nannyId) {
        return reviewService.getReviewByNannnyId(nannyId);
    }

 //  @PostMapping("/AddReview")
 //  public ResponseEntity<Review> createReview(@RequestBody Review review) {
 //      Review createdReview = reviewService.createReview(review);
 //      return ResponseEntity.ok(createdReview);
 //  }
}
