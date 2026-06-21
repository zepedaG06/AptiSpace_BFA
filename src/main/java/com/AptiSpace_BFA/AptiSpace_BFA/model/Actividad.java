package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter
@Setter
@View(members =
        "nombre;" +
                "descripcion;" +
                "fechaEntrega;" +
                "seccion"
)
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Required
    private String nombre;

    @Stereotype("MEMO")
    private String descripcion;

    @Required
    private LocalDate fechaEntrega;

    @ManyToOne
    @DescriptionsList
    @Required
    private Seccion seccion;
}
