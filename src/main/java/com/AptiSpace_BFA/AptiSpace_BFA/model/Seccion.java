package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
        "DatosDeSeccion { codigo, curso; docente } " +
                "Organizacion { horario, aula; cupo } " +
                "Matriculas { matriculas }"
)
@Tabs({
        @Tab(properties="codigo, curso.nombre, curso.asignatura.nombre, docente.codigoDocente, docente.nombres, docente.apellidos, horario, aula, cupo"),
        @Tab(name="CursosPorDocente", properties="docente.codigoDocente, docente.nombres, docente.apellidos, curso.codigo, curso.nombre, curso.asignatura.nombre, codigo, horario, aula")
})
public class Seccion extends Identifiable {

    @Column(length=20, unique=true)
    @Required
    String codigo;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="codigo, nombre")
    Curso curso;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="codigoDocente, nombres, apellidos")
    Docente docente;

    @Column(length=80)
    @Required
    String horario;

    @Column(length=40)
    String aula;

    @Min(1)
    @Max(200)
    int cupo = 30;

    @OneToMany(mappedBy="seccion")
    @ListProperties("estudiante.carnet, estudiante.nombres, estudiante.apellidos, periodoAcademico.nombre, estado")
    Collection<Matricula> matriculas = new ArrayList<>();

}
