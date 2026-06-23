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
        "DatosDeAsignatura { codigo, nombre; creditos; descripcion } " +
                "Cursos { cursos }"
)
@Tab(properties="codigo, nombre, creditos")
public class Asignatura extends Identifiable {

    @Column(length=20, unique=true)
    @Required
    String codigo;

    @Column(length=80)
    @Required
    String nombre;

    @Min(1)
    @Max(10)
    int creditos = 1;

    @Column(length=250)
    String descripcion;

    @OneToMany(mappedBy="asignatura")
    @ListProperties("codigo, nombre")
    Collection<Curso> cursos = new ArrayList<>();

}
