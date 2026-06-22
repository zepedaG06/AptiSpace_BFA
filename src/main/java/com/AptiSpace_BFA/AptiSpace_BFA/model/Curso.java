package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
        "Datos del curso { codigo, nombre; asignatura; descripcion } " +
                "Secciones { secciones }"
)
@Tab(properties="codigo, nombre, asignatura.nombre")
public class Curso extends Identifiable {

    @Column(length=20, unique=true)
    @Required
    String codigo;

    @Column(length=80)
    @Required
    String nombre;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @Required
    @DescriptionsList(descriptionProperties="codigo, nombre")
    Asignatura asignatura;

    @Column(length=250)
    String descripcion;

    @OneToMany(mappedBy="curso")
    @ListProperties("codigo, docente.codigoDocente, docente.nombres, docente.apellidos, horario, aula, cupo")
    Collection<Seccion> secciones = new ArrayList<>();

}