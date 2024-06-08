
package com.childcaresolutions.childcare_app.model;

import com.childcaresolutions.childcare_app.enums.RatingEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RatingEnums rating; 
    private String comment; 
    
    //Una niñera puede recibir muchas reseñas
    @ManyToOne
    @JoinColumn(name = "nanny_id")

    private Nanny nanny;

    //Un padre puede elaborar muchas reseñas
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
