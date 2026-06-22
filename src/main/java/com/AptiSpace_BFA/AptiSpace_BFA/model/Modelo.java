package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
        "Datos de matricula { estudiante; seccion; periodoAcademico } " +
                "Estado { fechaMatricula, estado }"
)
@Tabs({
        @Tab(properties="estudiante.carnet, estudiante.nombres, estudiante.apellidos, seccion.codigo, seccion.curso.nombre, periodoAcademico.nombre, fechaMatricula, estado"),
        @Tab(name="EstudiantesPorSeccion", properties="seccion.codigo, seccion.curso.nombre, estudiante.carnet, estudiante.nombres, estudiante.apellidos, periodoAcademico.nombre, estado")
})
public class Matricula extends Identifiable {

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="carnet, nombres, apellidos")
    Estudiante estudiante;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="codigo, horario, aula")
    Seccion seccion;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="codigo, nombre")
    PeriodoAcademico periodoAcademico;

    @ReadOnly
    LocalDate fechaMatricula = LocalDate.now();

    @Required
    @Enumerated(EnumType.STRING)
    EstadoMatricula estado = EstadoMatricula.ACTIVA;

    @PrePersist
    void registrarFecha() {
        if (fechaMatricula == null) fechaMatricula = LocalDate.now();
    }

}