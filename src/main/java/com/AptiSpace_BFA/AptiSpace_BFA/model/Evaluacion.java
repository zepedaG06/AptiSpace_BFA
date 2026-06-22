package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
@Views({
    @View(members =
        "Datos {" +
            "nombre;" +
            "porcentaje;" +
            "fecha;" +
            "seccion" +
        "}"
    ),
    @View(name = "Simple", members = "nombre; fecha")
})
@Tab(properties = "nombre, porcentaje, fecha, seccion.nombre")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;

    @Required
    @Column(length = 100)
    String nombre;

    @Required
    @DecimalMin("0")
    @DecimalMax("100")
    Double porcentaje;

    @Required
    LocalDate fecha;

    @ManyToOne
    @DescriptionsList
    @Required
    Seccion seccion;
}
