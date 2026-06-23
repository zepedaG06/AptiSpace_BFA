package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
@View(members =
    "Datos {" +
        "estudiante;" +
        "seccion;" +
        "fecha;" +
        "presente;" +
        "observacion" +
    "}"
)
@Tab(properties = "fecha, seccion.codigo, seccion.curso.nombre, estudiante.carnet, estudiante.nombres, estudiante.apellidos, presente")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "carnet, nombres, apellidos")
    @Required
    Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "codigo, horario, aula")
    @Required
    Seccion seccion;

    @Required
    LocalDate fecha = LocalDate.now();

    boolean presente = true;

    @Stereotype("MEMO")
    String observacion;
}
