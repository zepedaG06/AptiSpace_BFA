package com.AptiSpace_BFA.AptiSpace_BFA.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
	"Datos personales { cedula; nombres, apellidos; correo, telefono } " +
	"Administracion { cargo, nivelAcceso } " +
	"Estado { activo, fechaRegistro } " +
	"Seguridad { roles }"
)
@Tab(properties="cedula, nombres, apellidos, correo, cargo, nivelAcceso, activo")
public class Administrador extends Usuario {

	@Column(length=60)
	@Required
	String cargo;

	@Column(length=30)
	@Required
	String nivelAcceso;

}
