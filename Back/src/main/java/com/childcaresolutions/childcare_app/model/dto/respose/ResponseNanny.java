package com.childcaresolutions.childcare_app.model.dto.respose;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import com.childcaresolutions.childcare_app.model.Skill;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public record ResponseNanny (

        @NotNull
        Long id,
        @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters in length")
        String name,
        @Size(min = 2, max = 20, message = "Email must be between 2 and 20 characters in length")
        String email,
        String photo,
        String location,
        String experiences,
        int nannyRate,
        // 3 variables new for parent
        Set<Skill> skills,
        Set<Day> availableDaysN,
        TimeSlot timeSlotN
){
}
