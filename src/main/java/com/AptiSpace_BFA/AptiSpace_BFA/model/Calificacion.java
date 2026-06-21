package com.AptiSpace_BFA.AptiSpace_BFA.model;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;
}
