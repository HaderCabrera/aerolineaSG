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

SELECT CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ', COALESCE(EM.apellidos, '')) AS Empleado,
TR.nombre AS Rol_Empleado,
TR.nombre AS Rol_Empleado,
AO.aeropuerto_origen AS Aeropueto_Origen,
AD.aeropuerto_destino AS aeropuerto_destino,
VU.hora_salida AS hora_salida,
VU.hora_llegada AS Hora_Llega
FROM tripulacionRol AS TR
INNER JOIN empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles
INNER JOIN tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado
INNER JOIN detalle_vuelo AS DT ON TE.id_detalle_vuelo = DT.id_detalle_vuelo
INNER JOIN escala AS  ES ON DT.id_detalle_vuelo = ES.id_detalle_vuelo
INNER JOIN vuelo AS AO ON ES.id_vuelo = AO.id_vuelo
INNER JOIN vuelo AS AD ON ES.id_vuelo = AD.id_vuelo;





--
SELECT * FROM permisosUsuarios;

INSERT INTO permisosUsuarios (nombre_permiso) VALUES
('Registrar Avion'),('Consultar Informacion De Avion'),('Eliminar Avion'),('Actualizar Informacion De Avion'),
('Asignar Tripulacion A Trayecto'),('Consultar Tripulacion De Trayecto'),('Consultar Informacion De Trayecto'),('Asignar Aeronave A Trayecto'),
('Actualizar Informacion De Trayecto'),('Eliminar Escala'),('Registrar Aeropuerto'),('Consultar Informacion De Aeronave'),
('Actualizar Informacion De Aeropuerto'),('Eliminar Aeropuerto'),('Consultar Informacion De Vuelo'),('Consultar Escalas De Vuelo'),
('Registrar Tarifa De Vuelo'),('Actualizar Informacion Tarifa De Vuelo'),('Eliminar Tarifa De Vuelo'),
('Consultar Tarifa De Vuelo'),('Buscar Vuelo'),('Seleccionar Vuelo'),('Registrar Tipo Documento'),
('Actualizar Tipo Documento'),('Eliminar Tipo Documento'),('Consultar Tipo Documento'),('Realizar Pago'),
('Registrar Reserva Vuelo'),('Consultar Informacion Cliente'),('Actualizar Informacion Cliente'),('Eliminar Cliente'),
('Registrar Revision Mantenimiento'),('Historico De Revisiones En Avion'),('Actualizar Informacion Revision'),('Eliminar Revision Mantenimiento'),
('Modificar Estado Reserva'),('Eliminar Reserva Vuelo'),
('Consultar Reserva Vuelo'),('Listar Reservas'),('Crear Reserva Vuelo'),('Mostrar Detalles Reserva'),
('Consultar Informacion De Aeropuerto'),('Eliminar Trayecto'),('Actualizar Informacion De Escala'),
('Añadir Pasajero'), ('Seleccionar Asiento'), ('Registrar cliente');

29 -> permiso ID Agente Ventas<- 03

INSERT INTO rolUsuario (nombre_rol) VALUES
('Administrador'), ('Cliente'), ('Agente De Ventas'),('Tecnico De Mantenimiento');

-- Insertar datos en rol_permiso
INSERT INTO rol_permiso(id_rolUsuario, id_permisosUsuarios) VALUES
(1, 1),(1, 5),(1, 2),(1, 7),(1, 42),(1, 4),(1, 3),(1, 8),(1, 9),(1, 43),(1, 13),
(1, 14 ),(1, 6),(1, 16),(1, 44),(1, 10),(1, 17),(1, 18),(1, 19),(1, 20),(1, 23),(1, 24),(1, 25),(1, 26),
(3, 6),(3, 16),(3, 20),(3, 21),(3, 22),(3, 45),(3, 46),(3, 38),(3, 47),(3, 30),(3, 37), (3, 26), (3, 29)
(4, 32),(4, 33),(4, 34),(4, 35),
(2, 21),(2, 22),(2, 45),(2, 46),(2, 27),(2, 38);


-- Insertar datos en usuario
INSERT INTO usuario (nombre_usuario, pass, id_rolUsuario)
VALUES ('hader', 'hader123', 1),('tecnico', 'tecnico123', 4),('ventas', 'ventas123', 3),('cliente', 'cliente123', 2);

-- Insertar datos en estadoAvion
INSERT INTO estadoAvion (nombre) VALUES ('Disponible'), ('En Mantenimiento'), ('En Vuelo');
SELECT * FROM modelo;
-- Supongamos que el id_manufactura que deseas referenciar es 1 (debe existir en la tabla manufactura)
-- Insertar datos de aviones en la tabla modelo
INSERT INTO modelo (nombre, id_manufactura) VALUES ('Boeing 737', 1);
INSERT INTO modelo (nombre, id_manufactura) VALUES ('Airbus A320', 2);
INSERT INTO modelo (nombre, id_manufactura) VALUES ('Embraer E190', 3);


-- Insertar datos en la tabla manufactura
INSERT INTO manufactura (nombre, direccion, telefono, email, sitio_web)
VALUES
('Boeing Company', '100 N Riverside Plaza, Chicago, IL 60606, USA', '+1-312-544-2000', 'info@boeing.com', 'https://www.boeing.com'),
('Airbus SAS', '1 Rond-Point Maurice Bellonte, 31707 Blagnac Cedex, France', '+33-5-61-93-33-33', 'contact@airbus.com', 'https://www.airbus.com'),
('Bombardier Aerospace', '400 Côte-Vertu Road West, Dorval, Quebec H4S 1Y9, Canada', '+1-514-855-5000', 'info@aero.bombardier.com', 'https://www.bombardier.com');

-- REVISAR ID DE LA TABLA MODELO DADO QUE Y AVION DADO QUE ESTA PASANDO ALGO CON EL AUTOINCREMT
-- Insertar datos en la tabla avion
INSERT INTO avion (placa_identificacion, capacidad, fabricacion_fecha, id_estado, id_modelo)
VALUES
('ABC123', 180, '2023-01-15', 1, 2),   -- Avión Boeing 737 en estado activo
('XYZ456', 220, '2022-11-20', 1, 3),   -- Avión Airbus A320 en estado activo
('DEF789', 100, '2023-03-05', 1, 4);   -- Avión Embraer E190 en estado activo

INSERT INTO tripulacionRol (nombre) VALUES 
('Piloto'),
('Copiloto'),
('Asistente De Vuelo'),
('Ingeniero De Vuelo'),
('Técnico De Mantenimiento');

INSERT INTO aerolinea (nombreAeroline) VALUES ('American Airlines'),
('Delta Air Lines'),('United Airlines'),('Southwest Airlines'),('Lufthansa');

INSERT INTO empleado (id_empleado, nombre1, nombre2, apellidos, id_tripulacionRoles, id_aerolineas) VALUES 
('E001', 'Santiago', 'Sandoval', 'Smith', 1, 1),
('E002', 'Diego', 'Perez', 'Johnson', 3, 2),
('E003', 'Camilo', 'Hernandez', 'Williams', 4, 3),
('E004', 'Cristian', 'Celis', 'Brown', 5, 4),
('E005', 'James', 'Rodriguez', 'Jones', 2, 5);


-- Insertar tres registros en la tabla tipoDocumento
INSERT INTO tipoDocumento (nombreDoc) VALUES
    ('Documento de Identidad'),
    ('Pasaporte'),
    ('Licencia de Conducir');

-- Insertar un registro en la tabla cliente
INSERT INTO cliente (documento, nombre1, nombre2, apellidos, fecha_nacimiento, email, id_tipo_documento)
VALUES (12345678, 'Juan', 'Carlos', 'Gómez Pérez', '1990-05-15', 'juan@gmail.com', 1);

