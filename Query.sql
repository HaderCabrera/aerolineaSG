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

CREATE VIEW vista_tripulacion_vuelo AS
SELECT 
    CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ', COALESCE(EM.apellidos, '')) AS Empleado,
    TR.nombre AS Rol_Empleado,
    VU.numero_vuelo AS Numero_Vuelo,
    AO.nombre AS Aeropuerto_Origen,
    AD.nombre AS Aeropuerto_Destino,
    VU.hora_salida AS Hora_Salida,
    VU.hora_llegada AS Hora_Llegada
FROM 
    tripulacionRol AS TR
INNER JOIN 
    empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles
INNER JOIN 
    tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado
INNER JOIN 
    detalle_vuelo AS DT ON TE.id_detalle_vuelo = DT.id_detalle_vuelo
INNER JOIN 
    escala AS ES ON DT.id_detalle_vuelo = ES.id_detalle_vuelo
INNER JOIN 
    vuelo AS VU ON ES.id_vuelo = VU.id_vuelo
INNER JOIN 
    aeropuerto AS AO ON VU.aeropuerto_origen = AO.id_aeropuerto
INNER JOIN 
    aeropuerto AS AD ON VU.aeropuerto_destino = AD.id_aeropuerto;


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
(1, 1),(1, 5),(1, 2),(1, 7),(1, 42),(1, 4),(1, 3),(1, 8),(1, 9),(1, 43),(1, 13),(1,11)
(1, 14 ),(1, 6),(1, 16),(1, 44),(1, 10),(1, 17),(1, 18),(1, 19),(1, 20),(1, 23),(1, 24),(1, 25),(1, 26),
(3, 6),(3, 16),(3, 20),(3, 21),(3, 22),(3, 45),(3, 46),(3, 38),(3, 47),(3, 30),(3, 37), (3, 26), (3, 29)
(4, 32),(4, 33),(4, 34),(4, 35),
(2, 21),(2, 22),(2, 45),(2, 46),(2, 27),(2, 38);

-- Insertar datos en usuario
INSERT INTO usuario (nombre_usuario, pass, id_rolUsuario)
VALUES ('hader', 'hader123', 1),('tecnico', 'tecnico123', 4),('ventas', 'ventas123', 3),('cliente', 'cliente123', 2);

SELECT * FROM usuario;

-- Insertar datos en estadoAvion
INSERT INTO estadoAvion (nombre) VALUES ('Disponible'), ('En Mantenimiento'), ('En Vuelo');
SELECT * FROM modelo;
-- Supongamos que el id_manufactura que deseas referenciar es 1 (debe existir en la tabla manufactura)
-- Insertar datos de aviones en la tabla modelo
INSERT INTO manufactura (nombre, direccion, telefono, email, sitio_web)
VALUES
('Boeing Company', '100 N Riverside Plaza, Chicago, IL 60606, USA', '+1-312-544-2000', 'info@boeing.com', 'https://www.boeing.com'),
('Airbus SAS', '1 Rond-Point Maurice Bellonte, 31707 Blagnac Cedex, France', '+33-5-61-93-33-33', 'contact@airbus.com', 'https://www.airbus.com'),
('Bombardier Aerospace', '400 Côte-Vertu Road West, Dorval, Quebec H4S 1Y9, Canada', '+1-514-855-5000', 'info@aero.bombardier.com', 'https://www.bombardier.com');


INSERT INTO modelo (nombre, id_manufactura) VALUES ('Boeing 737', 1);
INSERT INTO modelo (nombre, id_manufactura) VALUES ('Airbus A320', 2);
INSERT INTO modelo (nombre, id_manufactura) VALUES ('Embraer E190', 3);


-- Insertar datos en la tabla manufactura


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
-- INSERCIONES PAIS

INSERT INTO pais (id_pais, nombre) VALUES 
('PA001', 'Estados Unidos'),
('PA002', 'España');

-- INSERCIONES CIUDAD
INSERT INTO ciudad (id_ciudad, nombre, id_pais) VALUES 
('CI001', 'Nueva York', 'PA001'),
('CI002', 'Madrid', 'PA002');

-- INSERCIONES AEROPUERTO 
INSERT INTO aeropuerto (nombre, id_ciudad) VALUES 
('JFK Airport', 'CI001'),
('Barajas Airport', 'CI002');

-- INSERCIONES PUERTADE ABORDAJE
INSERT INTO puertaSalidaAbordaje (nombre, id_aeropuerto) VALUES 
('Gate A1', 1),
('Gate B2', 2);


-- INSERCIONES VUELO

INSERT INTO vuelo (numero_vuelo, aeropuerto_origen, aeropuerto_destino, hora_salida, hora_llegada)
VALUES ('VU123', 1, 2, '08:00 AM', '10:00 AM');

INSERT INTO vuelo (numero_vuelo, aeropuerto_origen, aeropuerto_destino, hora_salida, hora_llegada)
VALUES ('VU456', 2, 1, '02:00 PM', '04:00 PM');

SELECT * FROM avion;

-- INSERCIONES ESTADO PUESTO
INSERT INTO estadoPuesto (nombre_estado_puesto) VALUES
('Disponible'),
('Ocupado');
SELECT * FROM `vuelo`;
-- INSERCIONES PUESTO

-- Suponiendo que tienes valores específicos para los campos
INSERT INTO puesto (numero_puesto, id_estadoPuesto, id_avion) VALUES
(1, 1, 3),  
(2, 1, 6);  

-- DELETE FROM puesto;
-- ALTER TABLE puesto
-- ADD COLUMN id_avion INT,
-- ADD FOREIGN KEY (id_avion) REFERENCES avion(id_avion);

INSERT INTO detalle_vuelo (id_puesto) VALUES
(3),
(4);

-- Suponiendo que tienes valores específicos para id_vuelo y id_detalle_vuelo
INSERT INTO escala (id_vuelo, id_detalle_vuelo) VALUES
(1, 1),  -- Ejemplo: Asignar id_vuelo 1 y id_detalle_vuelo 1 a la primera escala
(2, 2);  -- Ejemplo: Asignar id_vuelo 2 y id_detalle_vuelo 2 a la segunda escala


-- Suponiendo que los datos se insertan en el orden id_empleado, nombre, id_empleado, id_detalle_vuelo
INSERT INTO tripulacionvuelo_empleado (id_empleado, id_detalle_vuelo) VALUES
('E001', 1),
('E002', 2);
SELECT * FROM vista_detalle_tripulacion WHERE Numero_Vuelo = "VU123";
SELECT * FROM vista_detalle_tripulacion;
SELECT * FROM vuelo;

--PROCEDIMIENTO PARA OBTENER DATOS DE AVION
DELIMITER $$
CREATE PROCEDURE ObtenerDatosAvion(placaIdentificacion VARCHAR(30))
BEGIN
    SELECT A.id_avion, A.placa_identificacion, A.capacidad, A.fabricacion_fecha, E.nombre AS estado, M.nombre AS modelo
    FROM avion AS A
    INNER JOIN estadoAvion AS E ON A.id_estado = E.id_estado
    INNER JOIN modelo AS M ON A.id_modelo = M.id_modelo
    WHERE A.placa_identificacion = placaIdentificacion;
END $$
DELIMITER ;


INSERT INTO pais (id_pais, nombre) VALUES ('US', 'Estados Unidos'), ('CA', 'Canadá'),
 ('MX', 'México'), ('BR', 'Brasil'), ('AR', 'Argentina'), ('CO', 'Colombia'),('PE', 'Perú'),
('VE', 'Venezuela'), ('CL', 'Chile') ,('EC', 'Ecuador');

-- Inserción de datos en la tabla ciudad
INSERT INTO ciudad (id_ciudad, nombre, id_pais) VALUES
('NYC', 'Nueva York', 'US'), ('TOR', 'Toronto', 'CA'),
('CDMX', 'Ciudad de México', 'MX'), ('SAO', 'São Paulo', 'BR'),
('BA', 'Buenos Aires', 'AR'), ('BOG', 'Bogotá', 'CO'),
('LIM', 'Lima', 'PE'), ('CAR', 'Caracas', 'VE'), ('SCL', 'Santiago', 'CL'),
 ('GUQ', 'Guayaquil', 'EC');

-- Procedimiento para botener ciuades
DELIMITER $$
CREATE PROCEDURE ObtenerCiudades()
BEGIN
    SELECT nombre
    FROM ciudad;
END $$
DELIMITER ;

--procedimiento para obtener datos de ciudad
DELIMITER $$
CREATE PROCEDURE ObtenerDatosAeropuerto(idAeropuerto INT)
BEGIN
    SELECT A.nombre, C.nombre
    FROM aeropuerto AS A
    INNER JOIN ciudad AS C ON A.id_ciudad = C.id_ciudad
    WHERE A.id_aeropuerto = idAeropuerto;
END $$
DELIMITER ;

--PROCEDIMIENTO PARA SACAR INFORMACION DE REVISIONES
DELIMITER $$
CREATE PROCEDURE ObtenerHistorialRevisiones(placa VARCHAR(30))
BEGIN
    SELECT R.id_revision, R.fecha_revision, R.descrip, R.id_avion, ER.estado
    FROM revision AS R
    INNER JOIN avion AS A ON R.id_avion = A.id_avion
    INNER JOIN estado_revision AS ER ON R.id_estado_revision = ER.id_estado
    WHERE A.placa_identificacion = placa;
END $$
DELIMITER ;

INSERT INTO estado_revision (estado)
VALUES ('Pendiente'), ('En Progreso'), ('Completado');