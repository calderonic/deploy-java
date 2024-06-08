package com.childcaresolutions.childcare_app.model.dto.respose;

import com.childcaresolutions.childcare_app.enums.RatingEnums;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDto {
    private Long id;
    private RatingEnums rating;
    private String comment;
    private String parentName;
    private String parentPhoto;

    public ReviewDto(Long id, RatingEnums rating, String comment, String parentName, String parentPhoto) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.parentName = parentName;
        this.parentPhoto = parentPhoto;
    }

}
