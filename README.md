# AptiSpace BFA

Proyecto base OpenXava puro con PostgreSQL local.

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
- Usuario: `aptispace`
- Clave: `aptispace`
- URL JDBC: `jdbc:postgresql://localhost:5432/aptispace_bfa`

## Ejecutar

```bash
mvn compile exec:java
```

Abrir:

```text
http://localhost:8080/aptispace
```

## Modulo base

Entidades creadas para la base del sistema:

- `Usuario`
- `Rol`
- `Administrador`
- `Estudiante`
- `Docente`
