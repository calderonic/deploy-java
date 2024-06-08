
package com.childcaresolutions.childcare_app.service;


import com.childcaresolutions.childcare_app.model.Nanny;
import com.childcaresolutions.childcare_app.model.Parent;
import com.childcaresolutions.childcare_app.model.Review;
import com.childcaresolutions.childcare_app.model.dto.request.CreateReviewDto;
import com.childcaresolutions.childcare_app.model.dto.respose.ReviewDto;

import java.util.List;

public interface IReviewService {

    ReviewDto createReview (CreateReviewDto createReviewDto);

    List<ReviewDto> getReviewByNannnyId (Long nannyId);
    
}






