package com.AptiSpace_BFA.AptiSpace_BFA.model;

import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    Long id;
}
