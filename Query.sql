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


