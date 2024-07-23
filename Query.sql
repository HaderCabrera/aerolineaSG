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



INSERT INTO permisosUsuarios (nombre_permiso) VALUES
('Registrar Avion'),('Consultar Informacion De Avion'),('Eliminar Avion'),('Actualizar Informacion De Avion'),
('Asignar Tripulacion A Trayecto'),('Consultar Tripulacion De Trayecto'),('Consultar Informacion De Trayecto'),('Asignar Aeronave A Trayecto'),
('Actualizar Informacion De Trayecto'),('Eliminar Escala'),('Registrar Aeropuerto'),('Consultar Informacion De Aeronave'),
('Actualizar Informacion De Aeropuerto'),('Eliminar Aeropuerto'),('Consultar Informacion De Vuelo'),('Consultar Escalas De Vuelo'),
('Registrar Tarifa De Vuelo'),('Actualizar Informacion Tarifa De Vuelo'),('Eliminar Tarifa De Vuelo'),
('Consultar Tarifa De Vuelo'),('Buscar Vuelo'),('Seleccionar Vuelo'),('Registrar Tipo Documento'),
('Actualizar Tipo Documento'),('Eliminar Tipo Documento'),('Consultar Tipo Documento'),('Realizar Pago'),
('Crear Reserva'),('Consultar Informacion Cliente'),('Actualizar Informacion Cliente'),('Eliminar Cliente'),
('Registrar Revision Mantenimiento'),('Historico De Revisiones En Avion'),('Actualizar Informacion Revision'),('Eliminar Revision Mantenimiento'),
('Modificar Estado Reserva'),('Eliminar Reserva Vuelo'),
('Consultar Reserva Vuelo'),('Listar Reservas'),('Crear Reserva Vuelo'),('Mostrar Detalles Reserva'),
('Consultar Informacion De Aeropuerto'),('Eliminar Trayecto'),('Actualizar Informacion De Escala'),
('Añadir Pasajero'), ('Seleccionar Asiento'), ('Registrar cliente');

29 -> permiso ID Agente Ventas<- 03

INSERT INTO rolUsuario (nombre_rol) VALUES
('Administrador'), ('Cliente'), ('Agente De Ventas'),('Tecnico De Mantenimiento');
INSERT INTO usuario (nombre_usuario, pass, id_rolUsuario)
VALUES ('hader', 'hader123', 1),('tecnico', 'tecnico123', 4),('ventas', 'ventas123', 3),('cliente', 'cliente123', 2);
-- Insertar datos en rol_permiso
INSERT INTO rol_permiso(id_rolUsuario, id_permisosUsuarios) VALUES
(1, 1),(1, 5),(1, 2),(1, 7),(1, 42),(1, 4),(1, 3),(1, 8),(1, 9),(1, 43),(1, 13),(1,11),
(1, 14 ),(1, 6),(1, 16),(1, 44),(1, 10),(1, 17),(1, 18),(1, 19),(1, 20),(1, 23),(1, 24),(1, 25),(1, 26),
(3, 6),(3, 16),(3, 20),(3, 21),(3, 22),(3, 45),(3, 46),(3, 38),(3, 47),(3, 30),(3, 37), (3, 26), (3, 29), (3,28)
(4, 32),(4, 33),(4, 34),(4, 35),
(2, 21),(2, 22),(2, 45),(2, 46),(2, 27),(2, 38);

-- Insertar datos en usuario


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

SELECT * FROM avion;
-- Insertar datos en la tabla avion
INSERT INTO avion (placa_identificacion, capacidad, fabricacion_fecha, id_estado, id_modelo)
VALUES
('A1', 180, '2021-01-15', 1, 1),   -- Avión Boeing 737 en estado activo
('B1', 220, '2020-11-20', 1, 2),   -- Avión Airbus A320 en estado activo
('C1', 100, '2022-03-05', 1, 3);   -- Avión Embraer E190 en estado activo

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
SELECT * FROM vuelo;
INSERT INTO vuelo (numero_vuelo, aeropuerto_origen, aeropuerto_destino, hora_salida, hora_llegada)
VALUES ('VU123', 1, 2, '08:00 AM', '10:00 AM');

INSERT INTO vuelo (numero_vuelo, aeropuerto_origen, aeropuerto_destino, hora_salida, hora_llegada)
VALUES ('VU456', 2, 1, '02:00 PM', '04:00 PM');

SELECT * FROM avion;

-- INSERCIONES ESTADO PUESTO
INSERT INTO estadoPuesto (nombre_estado_puesto) VALUES
('Disponible'),
('Ocupado');

INSERT INTO puesto (id_avion, id_estadoPuesto, numero_puesto) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 1, 5),
(2, 1, 1),
(2, 1, 2),
(2, 1, 3),
(2, 1, 4),
(2, 1, 5);


INSERT INTO tripulacion(id_vuelo, id_empleado) VALUES
(1,'E001'),
(2,'E002');
SELECT * FROM vista_tripulacion_vuelo WHERE Numero_Vuelo = "VU123";
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

--procedimiento para obtener datos aeropuerto
DELIMITER $$
CREATE PROCEDURE ObtenerDatosAeropuerto(idAeropuerto INT)
BEGIN
    SELECT A.nombre AS aeropuerto, C.nombre AS ciudad, C.id_ciudad
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


SELECT * FROM vuelo;
-- INSERCIONES A LA TABLA TRAYECTO

INSERT INTO trayecto (origen_trayecto, destino_trayecto, desc_trayecto, distancia, TiempoEstimado)
VALUES 
('NYC', 'LAX', 'Vuelo directo de Nueva York a Los Ángeles', '2475', '05:30:00'),
('ORD', 'MIA', 'Vuelo de Chicago a Miami', '1197', '02:45:00');
-- INSERCIONES A LA TABLA ESCALA

-- INSERT INTO escala (id_vuelo ,id_trayecto)
-- VALUES
-- (1 ,1),
-- (2, 2),
-- (2,1),
-- (1,2);

INSERT INTO escala (id_vuelo, id_trayecto, origen, destino)
VALUES (1, 1, 'JJJ', 'ppp'), (2, 1, 'JJJ', 'pp2');

-- PROCEDIMIENTO PARA ABTRAER INFORMACION DE TRAYECTO
-- LO CORRECTO ES DECIR CUANTOS VUELOS TIENE ASOCIADO UN TRAYECTO 
-- NO UN VUELO A CUANTOS TRAYECTOS ESTA ASOCIADO
-- Recordemos que el vuelo o los trayectos lo podemos hacer una lista
DROP Procedure `abstraerTrayecto_Escalas`;

DELIMITER $$
CREATE PROCEDURE abstraerTrayecto_Escalas(numero_trayecto int)
BEGIN
SELECT  TR.id_trayecto AS ID_trayecto,
        TR.origen_trayecto,
        TR.destino_trayecto,
        TR.desc_trayecto,
        TR.distancia,
        TR.TiempoEstimado
FROM trayecto AS TR
WHERE TR.id_trayecto = numero_trayecto;
END$$
DELIMITER ;

CALL abstraerTrayecto_Escalas(1);
SHOW CREATE TABLE escala;

ALTER TABLE escala DROP FOREIGN KEY escala_ibfk_2;
ALTER TABLE escala 
ADD CONSTRAINT escala_ibfk_2
FOREIGN KEY (id_trayecto) REFERENCES trayecto(id_trayecto) 
ON DELETE CASCADE;

-- CONSULAR ESCALAS DE UN TRAYECTO ES OTRA CONSULTA QUE PUEDO IR EN EL MISMO JPANEL

SELECT * FROM trayecto;
SHOW CREATE TABLE vuelo;

ALTER Table trayecto_x_tarifa DROP FOREIGN KEY trayecto_x_tarifa_ibfk_2;

ALTER TABLE trayecto_x_tarifa 
ADD CONSTRAINT trayecto_x_tarifa_ibfk_2
FOREIGN KEY (id_trayecto) REFERENCES trayecto(id_trayecto) 
ON DELETE CASCADE;

INSERT INTO tipoClase (nombre_Clase) VALUES
('Economica'),
('Ejecutiva'),
('Primera Clase');

INSERT INTO tarifa (id_tipoClase, precio_tarifa, descripcion) VALUES
(1, 250.00, 'Tarifa Vuelo Con Escala'),
(1, 500.00, 'Tarifa Vuelo Directo'),
(2, 250.00, 'Tarifa Vuelo Con Escala'),
(2, 500.00, 'Tarifa Vuelo Directo'),
(3, 250.00, 'Tarifa Vuelo Con Escala'),
(3, 500.00, 'Tarifa Vuelo Directo');

INSERT INTO estadoReserva (nombre_estado) VALUES
('Confirmada'),
('Cancelada');



