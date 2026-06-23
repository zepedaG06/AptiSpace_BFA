INSERT INTO rol (id, activo, descripcion, nombre) VALUES
	('roladmin00000000000000000000001', true, 'Acceso administrativo del sistema', 'Administrador'),
	('rolestudiante00000000000000001', true, 'Usuario estudiante', 'Estudiante'),
	('roldocente0000000000000000001', true, 'Usuario docente', 'Docente')
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuario (id, activo, apellidos, cedula, correo, fecharegistro, nombres, telefono) VALUES
	('usradmin00000000000000000000001', true, 'Prueba', '001-010101-0001A', 'admin.prueba@aptispace.local', current_date, 'Admin', '8888-0001'),
	('usrestudiante00000000000000001', true, 'Prueba', '001-010101-0002B', 'estudiante.prueba@aptispace.local', current_date, 'Estudiante', '8888-0002'),
	('usrdocente0000000000000000001', true, 'Prueba', '001-010101-0003C', 'docente.prueba@aptispace.local', current_date, 'Docente', '8888-0003')
ON CONFLICT (id) DO NOTHING;

INSERT INTO administrador (id, cargo, nivelacceso) VALUES
	('usradmin00000000000000000000001', 'Administrador general', 'TOTAL')
ON CONFLICT (id) DO NOTHING;

INSERT INTO estudiante (id, carnet, fechaingreso) VALUES
	('usrestudiante00000000000000001', 'EST-0001', current_date)
ON CONFLICT (id) DO NOTHING;

INSERT INTO docente (id, codigodocente, especialidad) VALUES
	('usrdocente0000000000000000001', 'DOC-0001', 'Razonamiento espacial')
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuario_rol (usuario_id, roles_id)
SELECT 'usradmin00000000000000000000001', 'roladmin00000000000000000000001'
WHERE NOT EXISTS (
	SELECT 1 FROM usuario_rol
	WHERE usuario_id = 'usradmin00000000000000000000001'
	AND roles_id = 'roladmin00000000000000000000001'
);

INSERT INTO usuario_rol (usuario_id, roles_id)
SELECT 'usrestudiante00000000000000001', 'rolestudiante00000000000000001'
WHERE NOT EXISTS (
	SELECT 1 FROM usuario_rol
	WHERE usuario_id = 'usrestudiante00000000000000001'
	AND roles_id = 'rolestudiante00000000000000001'
);

INSERT INTO usuario_rol (usuario_id, roles_id)
SELECT 'usrdocente0000000000000000001', 'roldocente0000000000000000001'
WHERE NOT EXISTS (
	SELECT 1 FROM usuario_rol
	WHERE usuario_id = 'usrdocente0000000000000000001'
	AND roles_id = 'roldocente0000000000000000001'
);

INSERT INTO asignatura (id, codigo, nombre, creditos, descripcion)
SELECT 'asig00000000000000000000000001', 'MAT-ESP', 'Aptitud espacial', 4, 'Asignatura base para practica de orientacion y razonamiento espacial'
WHERE NOT EXISTS (SELECT 1 FROM asignatura WHERE codigo = 'MAT-ESP');

INSERT INTO curso (id, codigo, nombre, descripcion, asignatura_id)
SELECT 'curso0000000000000000000000001', 'CUR-ESP-01', 'Curso inicial de aptitud espacial', 'Curso de prueba para el flujo academico', 'asig00000000000000000000000001'
WHERE NOT EXISTS (SELECT 1 FROM curso WHERE codigo = 'CUR-ESP-01');

INSERT INTO periodoacademico (id, activo, codigo, nombre, fechainicio, fechafin)
SELECT 'periodo00000000000000000000001', true, '2026-I', 'Primer periodo 2026', DATE '2026-01-15', DATE '2026-06-30'
WHERE NOT EXISTS (SELECT 1 FROM periodoacademico WHERE codigo = '2026-I');

INSERT INTO seccion (id, codigo, horario, aula, cupo, curso_id, docente_id)
SELECT 'seccion00000000000000000000001', 'SEC-ESP-01', 'Lunes y miercoles 08:00-10:00', 'A-101', 30, 'curso0000000000000000000000001', 'usrdocente0000000000000000001'
WHERE NOT EXISTS (SELECT 1 FROM seccion WHERE codigo = 'SEC-ESP-01');

INSERT INTO matricula (id, fechamatricula, estado, estudiante_id, seccion_id, periodoacademico_id)
SELECT 'matricula000000000000000000001', current_date, 'ACTIVA', 'usrestudiante00000000000000001', 'seccion00000000000000000000001', 'periodo00000000000000000000001'
WHERE NOT EXISTS (
	SELECT 1 FROM matricula
	WHERE estudiante_id = 'usrestudiante00000000000000001'
	AND seccion_id = 'seccion00000000000000000000001'
	AND periodoacademico_id = 'periodo00000000000000000000001'
);

INSERT INTO actividad (nombre, descripcion, fechaentrega, seccion_id)
SELECT 'Practica de figuras 3D', 'Actividad de prueba para rotacion de figuras', current_date + 7, 'seccion00000000000000000000001'
WHERE NOT EXISTS (SELECT 1 FROM actividad WHERE nombre = 'Practica de figuras 3D');

INSERT INTO evaluacion (nombre, porcentaje, fecha, seccion_id)
SELECT 'Evaluacion diagnostica', 100, current_date + 14, 'seccion00000000000000000000001'
WHERE NOT EXISTS (SELECT 1 FROM evaluacion WHERE nombre = 'Evaluacion diagnostica');

INSERT INTO calificacion (estudiante_id, evaluacion_id, nota, observacion)
SELECT 'usrestudiante00000000000000001', e.id, 95, 'Calificacion de prueba'
FROM evaluacion e
WHERE e.nombre = 'Evaluacion diagnostica'
AND NOT EXISTS (
	SELECT 1 FROM calificacion c
	WHERE c.estudiante_id = 'usrestudiante00000000000000001'
	AND c.evaluacion_id = e.id
);

INSERT INTO asistencia (fecha, presente, observacion, estudiante_id, seccion_id)
SELECT current_date, true, 'Asistencia de prueba', 'usrestudiante00000000000000001', 'seccion00000000000000000000001'
WHERE NOT EXISTS (
	SELECT 1 FROM asistencia
	WHERE fecha = current_date
	AND estudiante_id = 'usrestudiante00000000000000001'
	AND seccion_id = 'seccion00000000000000000000001'
);
