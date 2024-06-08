package com.childcaresolutions.childcare_app.model.dto.request;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import com.childcaresolutions.childcare_app.model.Penalty;
import com.childcaresolutions.childcare_app.model.Request;
import com.childcaresolutions.childcare_app.model.Review;
import com.childcaresolutions.childcare_app.model.Skill;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public record RequestCreateNanny(

        @NotNull
        Long id,
        @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters in length")
        String name,
        @Size(min = 2, max = 20, message = "secondName must be between 2 and 20 characters in length")
        String email,
        @Size(min = 2, max = 20, message = "secondName must be between 2 and 20 characters in length")
        String password,
        String photo,
        String location,
        String experiences,
        int nannyRate,
        Set<Skill> skills,
        Set<Day> availableDaysN,
        TimeSlot timeSlotN
    //  List<Request>requests
    //            List<Review> reviews
    //            List<Penalty> penalidades;
) {
}
