package com.childcaresolutions.childcare_app.model;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import jakarta.persistence.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Nanny extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String experiences;

    private double nannyRate;
    // Relación ManyToMany con Skill
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "nanny_skills",
            joinColumns = { @JoinColumn(name = "nanny_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<Skill> skills = new HashSet<>();//= new HashSet<>();: Inicializa el conjunto como un HashSet para evitar problemas de referencia nula.

    // Días disponibles
    @ElementCollection(targetClass = Day.class)
    @CollectionTable(name = "nanny_available_days", joinColumns = @JoinColumn(name = "nanny_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<Day> availableDaysN = EnumSet.noneOf(Day.class);

    // Horario disponible
    @Enumerated(EnumType.STRING) //Especifica que el valor de la enumeración TimeSlot se almacenará como una cadena en la base de datos.
    private TimeSlot timeSlotN;


    // Relación OneToMany con Request
    @OneToMany(mappedBy = "nanny", cascade = CascadeType.ALL)
    private List<Request> requests;

    // Relación OneToMany con Review
    @OneToMany(mappedBy = "nanny", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // Relación OneToMany con Penalty
    @OneToMany(mappedBy = "nanny", cascade = CascadeType.ALL)
    private List<Penalty> penalidades;


    public double calculateAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0; // Devolver 0 si no hay revisiones
        }

        int totalStars = 0;
        for (Review review : reviews) {
            totalStars += review.getRating().getValue();
        }

        return (double) totalStars / reviews.size();
    }
}
