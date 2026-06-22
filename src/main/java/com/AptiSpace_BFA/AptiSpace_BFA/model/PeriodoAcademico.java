package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
        "Datos del periodo { codigo, nombre; fechaInicio, fechaFin; activo } " +
                "Matriculas { matriculas }"
)
@Tab(properties="codigo, nombre, fechaInicio, fechaFin, activo")
public class PeriodoAcademico extends Identifiable {

    @Column(length=20, unique=true)
    @Required
    String codigo;

    @Column(length=80)
    @Required
    String nombre;

    @Required
    LocalDate fechaInicio;

    @Required
    LocalDate fechaFin;

    boolean activo = true;

    @OneToMany(mappedBy="periodoAcademico")
    @ListProperties("estudiante.carnet, estudiante.nombres, estudiante.apellidos, seccion.codigo, seccion.curso.nombre, estado")
    Collection<Matricula> matriculas = new ArrayList<>();

}