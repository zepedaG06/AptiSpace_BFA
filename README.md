# AptiSpace BFA

Proyecto base OpenXava puro con PostgreSQL local para gestionar usuarios del sistema AptiSpace BFA, orientado a ejercicios de aptitud espacial, orientacion y desplazamiento.

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

## Modulo base

Entidades creadas para la base del sistema:

- `Usuario`
- `Rol`
- `Administrador`
- `Estudiante`
- `Docente`

## Paquetes base

- `com.AptiSpace_BFA.AptiSpace_BFA.model`
- `com.AptiSpace_BFA.AptiSpace_BFA.actions`
- `com.AptiSpace_BFA.AptiSpace_BFA.validators`
- `com.AptiSpace_BFA.AptiSpace_BFA.calculators`

## Flujo de trabajo actual

- `main`: rama estable.
- `andres`: rama de trabajo actual para la base OpenXava.

Por ahora todo el trabajo de la base se realiza en `andres`. No se trabaja directo en `main`.
