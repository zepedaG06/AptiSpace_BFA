package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.math.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
@View(members =
    "Datos {" +
        "estudiante;" +
        "evaluacion;" +
        "nota;" +
        "observacion" +
    "}"
)
@Tab(properties = "estudiante.nombres, estudiante.apellidos, evaluacion.nombre, nota")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;

    @ManyToOne
    @DescriptionsList
    @Required
    Estudiante estudiante;

    @ManyToOne
    @DescriptionsList
    @ReferenceView("Simple")
    @Required
    Evaluacion evaluacion;

    @Required
    @DecimalMin("0")
    @DecimalMax("100")
    BigDecimal nota;

    @Stereotype("MEMO")
    String observacion;
}
