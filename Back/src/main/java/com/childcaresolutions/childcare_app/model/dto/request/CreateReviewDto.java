package com.childcaresolutions.childcare_app.model.dto.request;

import com.childcaresolutions.childcare_app.enums.RatingEnums;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateReviewDto {

    private Long nannyId;
    private Long parentId;
    private RatingEnums rating;
    private String comment;

}
