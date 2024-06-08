package com.childcaresolutions.childcare_app.model;


import com.childcaresolutions.childcare_app.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    // Fecha de comienzo de la penalización
    private Date startDate;

    // Fecha de fin de la penalización
    private Date endDate;

    // Estado de la penalización
    private Status status;

    // Relación ManyToOne con Administrator
    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    // Relación ManyToOne con Nanny
    @ManyToOne
    @JoinColumn(name = "nanny_id")
    private Nanny nanny;
}
