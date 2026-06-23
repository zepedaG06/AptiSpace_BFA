package com.AptiSpace_BFA.AptiSpace_BFA.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Getter @Setter
@View(members=
	"DatosGenerales { nombre, activo; descripcion }"
)
@Tab(properties="nombre, descripcion, activo")
public class Rol extends Identifiable {

	@Column(length=40, unique=true)
	@Required
	String nombre;

	@Column(length=150)
	String descripcion;

	boolean activo = true;

}
