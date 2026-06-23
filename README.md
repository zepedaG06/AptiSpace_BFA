# AptiSpace BFA

Proyecto OpenXava con PostgreSQL local para gestionar usuarios, docentes, estudiantes, cursos, matriculas, actividades, evaluaciones, calificaciones y asistencias del sistema AptiSpace BFA.

## Requisitos

- Java 17
- Maven
- PostgreSQL local en el puerto 5432

## Base de datos local

Entrar a PostgreSQL con un usuario administrador y ejecutar:

```sql
\i sql/crear_base_postgresql.sql
```

La aplicacion queda configurada con:

- Base de datos: `aptispace_bfa`
- Usuario: `aptispace_bfa`
- Clave: `aptispace_bfa`
- URL JDBC: `jdbc:postgresql://localhost:5432/aptispace_bfa`

## Ejecutar

```bash
mvn package exec:java
```

Abrir:

```text
http://localhost:8080/aptispace_bfa
```

## Datos de prueba

Con la aplicacion ejecutada al menos una vez para que OpenXava cree las tablas, cargar datos base con:

```sql
\c aptispace_bfa
\i sql/datos_prueba_base.sql
```

El script crea roles, usuarios y datos academicos de prueba. Se puede ejecutar varias veces sin duplicar registros.

## Modulos principales

Entidades creadas para el sistema:

- `Usuario`
- `Rol`
- `Administrador`
- `Estudiante`
- `Docente`
- `Asignatura`
- `Curso`
- `Seccion`
- `PeriodoAcademico`
- `Matricula`
- `Actividad`
- `Evaluacion`
- `Calificacion`
- `Asistencia`

## Paquetes base

- `com.AptiSpace_BFA.AptiSpace_BFA.model`
- `com.AptiSpace_BFA.AptiSpace_BFA.actions`
- `com.AptiSpace_BFA.AptiSpace_BFA.validators`
- `com.AptiSpace_BFA.AptiSpace_BFA.calculators`

## Flujo de trabajo actual

- `main`: rama estable.
- Ramas personales: se integran a `main` cuando cada parte compila y se revisa en OpenXava.
