package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
@View(members =
        "nombre;" +
                "descripcion;" +
                "fechaEntrega;" +
                "seccion"
)
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;

    @Column(length = 100)
    @Required
    String nombre;

    @Stereotype("MEMO")
    String descripcion;

    @Required
    LocalDate fechaEntrega;

    @ManyToOne
    @DescriptionsList
    @Required
    Seccion seccion;
}
