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
