package com.childcaresolutions.childcare_app.model.dto.request;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import com.childcaresolutions.childcare_app.model.Skill;

import java.util.Set;

public record RequestEditParent(

        Long id,
        String name,
        String email,
        String password,
        String photo,
        //boolean availability,
        Set<Skill> skills,
        Set<Day> availableDays,
        TimeSlot timeSlot,
        String location,
         String phoneNumber,
        int numberOfChildren,
        String infoFamily,
        boolean isPremium



) {
}
