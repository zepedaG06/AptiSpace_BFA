package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter
@View(members=
	"Datos personales { cedula; nombres, apellidos; correo, telefono } " +
	"Estado { activo, fechaRegistro } " +
	"Seguridad { roles }"
)
@Tab(properties="cedula, nombres, apellidos, correo, telefono, activo")
public class Usuario extends Identifiable {

	@Column(length=20, unique=true)
	@Required
	String cedula;

	@Column(length=60)
	@Required
	String nombres;

	@Column(length=60)
	@Required
	String apellidos;

	@Column(length=100, unique=true)
	@Required
	String correo;

	@Column(length=20)
	String telefono;

	boolean activo = true;

	LocalDate fechaRegistro = LocalDate.now();

	@ManyToMany
	@ListProperties("nombre, descripcion, activo")
	Collection<Rol> roles = new ArrayList<>();

	@PrePersist
	void registrarFecha() {
		if (fechaRegistro == null) fechaRegistro = LocalDate.now();
	}

}
