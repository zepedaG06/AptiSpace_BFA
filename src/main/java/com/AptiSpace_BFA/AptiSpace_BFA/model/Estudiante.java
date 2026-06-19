package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
	"Datos personales { cedula; nombres, apellidos; correo, telefono } " +
	"Datos de practica { carnet, fechaIngreso } " +
	"Estado { activo, fechaRegistro } " +
	"Seguridad { roles }"
)
@Tab(properties="carnet, cedula, nombres, apellidos, correo, fechaIngreso, activo")
public class Estudiante extends Usuario {

	@Column(length=20, unique=true)
	@Required
	String carnet;

	@ReadOnly
	LocalDate fechaIngreso;

	@PrePersist
	void registrarIngreso() {
		if (fechaIngreso == null) fechaIngreso = LocalDate.now();
	}

}
