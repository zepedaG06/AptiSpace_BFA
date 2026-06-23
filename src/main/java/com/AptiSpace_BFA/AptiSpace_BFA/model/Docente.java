package com.AptiSpace_BFA.AptiSpace_BFA.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
	"DatosPersonales { cedula; nombres, apellidos; correo, telefono } " +
	"DatosProfesionales { codigoDocente, especialidad } " +
	"Estado { activo, fechaRegistro } " +
	"Seguridad { roles }"
)
@Tab(properties="codigoDocente, cedula, nombres, apellidos, correo, especialidad, activo")
public class Docente extends Usuario {

	@Column(length=20, unique=true)
	@Required
	String codigoDocente;

	@Column(length=80)
	String especialidad;

}
