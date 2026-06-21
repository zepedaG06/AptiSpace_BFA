package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;

    @Required
    @Column(length = 100)
    String nombre;

    @Required
    Double porcentaje;

    @Required
    LocalDate fecha;

    @ManyToOne
    @DescriptionsList
    @Required
    Seccion seccion;
}
