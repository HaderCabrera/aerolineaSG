USE railway;
-- CONSULTA EMPLEADO NO DISPONIBLE PARA ASIGNAR
SELECT CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ', COALESCE(EM.apellidos, '')) AS Empleado,
TR.nombre AS Rol_Empleado
FROM tripulacionRol AS TR
INNER JOIN empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles
INNER JOIN tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado;

-- CONSULTA EMPLEADO DISPONIBLE PARA ASIGNAR
SELECT CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ', COALESCE(EM.apellidos, '')) AS Empleado,
TR.nombre AS Rol_Empleado
FROM tripulacionRol AS TR
INNER JOIN empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles
INNER JOIN tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado;



CREATE TABLE IF NOT EXISTS revision (
    id_revision INT PRIMARY KEY AUTO_INCREMENT,
    fecha_revision DATE NOT NULL,
    descrip TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS avion (
    id_avion INT PRIMARY KEY AUTO_INCREMENT,
    placa_identificacion VARCHAR(30) NOT NULL,
    capacidad INT not NULL,
    fabricacion_fecha DATE NOT NULL,
    id_estado INT NOT NULL,
    id_modelo INT NOT NULL
    id_revision INT NOT NULL
    Foreign Key (id_estado) REFERENCES estadoAvion (id_estado),
    Foreign Key (id_modelo) REFERENCES modelo (id_modelo),
    Foreign Key (id_revision) REFERENCES revision(id_revision)
);
