
package com.childcaresolutions.childcare_app.model;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.RoleEnum;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import jakarta.persistence.*;

import java.util.*;

import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Parent extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private RoleEnum role = RoleEnum.PARENT;
    private  String phoneNumber;
    private int numberOfChildren; 
    private String infoFamily;
    private boolean isPremium;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "parent_skills",
            joinColumns = { @JoinColumn(name = "parent_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<Skill> skills = new HashSet<>();

    @ElementCollection(targetClass = Day.class)
    @CollectionTable(name = "parent_available_days", joinColumns = @JoinColumn(name = "parent_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<Day> availableDays = EnumSet.noneOf(Day.class);

    @Enumerated(EnumType.STRING)
    private TimeSlot timeSlot;

    //Un padre puede tener muchos hijos
     @OneToMany(mappedBy = "parent")
     private List<Child> childrens;
     
    //Un padre puede tener muchas solicitudes
     @OneToMany(mappedBy = "parent")
     private List<Request> requests;
     
     //Un padre puede tener muchas Niñeras Favoritas
     
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<NannyFavorite> nannyFavorites;
    
       // Relación OneToMany con las reseñas escritas por este padre
    @OneToMany(mappedBy = "parent")
    private List<Review> reviews;


}