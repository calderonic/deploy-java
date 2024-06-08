package com.childcaresolutions.childcare_app.model;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.Data;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


@Data
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private String email;
    private String password;
    private String photo;
    private String location;

 // @ManyToMany(cascade = { CascadeType.ALL })
 // @JoinTable(
 //         name = "user_skills",
 //         joinColumns = { @JoinColumn(name = "user_id") },
 //         inverseJoinColumns = { @JoinColumn(name = "skill_id") }
 // )
 // private Set<Skill> skills = new HashSet<>();

 // @ElementCollection(targetClass = Day.class)
 // @CollectionTable(name = "user_available_days", joinColumns = @JoinColumn(name = "user_id"))
 // @Enumerated(EnumType.STRING)
 // @Column(name = "day")
 // private Set<Day> availableDays = EnumSet.noneOf(Day.class);

 //// private boolean availability;
 //// private boolean day;

    //private TimeSlot timeSlot;



}
