DROP DATABASE IF EXISTS railway;
CREATE DATABASE railway;
USE railway;

-- TABLA @TIPO_DOCUMENTO
CREATE TABLE IF NOT EXISTS tipoDocumento (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    nombreDoc VARCHAR(50)
);

-- TABLA @ESTADO_PUESTO
CREATE TABLE IF NOT EXISTS estadoPuesto (
    id_estadoPuesto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado_puesto VARCHAR(50) NOT NULL
);
-- TANLA @PUESTO
CREATE TABLE IF NOT EXISTS puesto (
    id_puesto INT PRIMARY KEY AUTO_INCREMENT,
    id_estadoPuesto INT NOT NULL,
    numero_puesto INT NOT NULL,
    Foreign Key (id_estadoPuesto) REFERENCES estadoPuesto (id_estadoPuesto)
);
--TABLA @CLIENTE
CREATE Table IF NOT EXISTS cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    documento INT NOT NULL,
    nombre1 VARCHAR(30) NOT NULL,
    nombre2 VARCHAR(30) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento VARCHAR(12) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    id_tipo_documento INT NOT NULL,
    Foreign Key (id_tipo_documento) REFERENCES tipoDocumento (id_tipo_documento)
);



-- TABLA @CLASES_TARIFA
CREATE TABLE IF NOT EXISTS tipoClase (
    id_tipoClase INT PRIMARY KEY AUTO_INCREMENT,
    nombre_Clase VARCHAR(50)
);

-- TABLA  @TARIFA 
CREATE TABLE IF NOT EXISTS tarifa (
    id_tarifa INT AUTO_INCREMENT PRIMARY KEY,
    id_tipoClase INT NOT NULL,
    precio_tarifa DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    Foreign Key (id_tipoClase) REFERENCES tipoClase (id_tipoClase)
);
-- TABLA @TRAYECTO

CREATE TABLE trayecto (
    id_trayecto INT PRIMARY KEY AUTO_INCREMENT,
    origen_trayecto VARCHAR(3) NOT NULL,
    destino_trayecto VARCHAR(3) NOT NULL,
    desc_trayecto VARCHAR(255),
    distancia VARCHAR(10),
    TiempoEstimado VARCHAR(12)
);

-- TABLA @TRAYECTO_x_TARIFA
CREATE TABLE IF NOT EXISTS trayecto_x_tarifa(
    id_trayecto INT NOT NULL,
    id_tarifa INT NULL,
    Foreign Key (id_trayecto) REFERENCES trayecto(id_trayecto),
    Foreign Key (id_tarifa) REFERENCES tarifa(id_tarifa)
);

-- TABLA @ESTADO_RESERVA

CREATE TABLE IF NOT EXISTS estadoReserva (
    id_estadoReserva INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado VARCHAR(50)
);

-- TABLA @RESERVA

CREATE TABLE IF NOT EXISTS reserva (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    fecha_reserva VARCHAR(12) NOT NULL,
    id_cliente INT NOT NULL,
    id_estadoReserva INT NOT NULL,
    id_puesto INT NOT NULL,
    id_tarifa INT NOT NULL,
    Foreign Key (id_tarifa) REFERENCES tarifa(id_tarifa),
    Foreign Key (id_puesto) REFERENCES puesto(id_puesto),
    Foreign Key (id_estadoReserva) REFERENCES estadoReserva (id_estadoReserva),
    Foreign Key (id_cliente) REFERENCES cliente (id_cliente)
);


CREATE Table IF NOT EXISTS aerolinea (
    id_aerolineas INT PRIMARY KEY AUTO_INCREMENT,
    nombreAeroline VARCHAR(50) NOT NULL
);
CREATE Table IF NOT EXISTS tripulacionRol (
    id_tripulacionRoles INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);
CREATE Table IF NOT EXISTS pais (
    id_pais VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);
CREATE TABLE IF NOT EXISTS ciudad (
    id_ciudad VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    id_pais VARCHAR(5) NOT NULL,
    Foreign Key (id_pais) REFERENCES pais (id_pais)
);
CREATE TABLE IF NOT EXISTS aeropuerto (
    id_aeropuerto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_ciudad VARCHAR(5) NOT NULL,
    Foreign Key (id_ciudad) REFERENCES ciudad (id_ciudad)
);
CREATE TABLE IF NOT EXISTS puertaSalidaAbordaje (
    id_puertaSalidaAbordaje INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) not NULL,
    id_aeropuerto INT NOT NULL,
    Foreign Key (id_aeropuerto) REFERENCES aeropuerto (id_aeropuerto)
);
-- crear ticket
CREATE TABLE IF NOT EXISTS empleado (
    id_empleado VARCHAR(20) PRIMARY KEY,
    nombre1 VARCHAR(40) NOT NULL,
    nombre2 VARCHAR(40) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    id_tripulacionRoles INT NULL NULL,
    id_aerolineas INT NOT NULL,
    Foreign Key (id_tripulacionRoles) REFERENCES tripulacionRol (id_tripulacionRoles),
    Foreign Key (id_aerolineas) REFERENCES aerolinea (id_aerolineas)
);
CREATE TABLE IF NOT EXISTS manufactura (
    id_manufactura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(50),
    email VARCHAR(100),
    sitio_web VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS modelo (
    id_modelo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_manufactura INT NOT NULL,
    Foreign Key (id_manufactura) REFERENCES manufactura (id_manufactura)
);
CREATE TABLE IF NOT EXISTS estadoAvion (
    id_estado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULl
);
CREATE TABLE IF NOT EXISTS avion (
    id_avion INT PRIMARY KEY AUTO_INCREMENT,
    placa_identificacion VARCHAR(30) NOT NULL,
    capacidad INT not NULL,
    fabricacion_fecha VARCHAR(12) NOT NULL,
    id_estado INT NOT NULL,
    id_modelo INT NOT NULL,
    Foreign Key (id_estado) REFERENCES estadoAvion (id_estado),
    Foreign Key (id_modelo) REFERENCES modelo (id_modelo)
);

CREATE TABLE IF NOT EXISTS estado_revision(
	id_estado INT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS revision (
    id_revision INT PRIMARY KEY AUTO_INCREMENT,
    fecha_revision VARCHAR(12) NOT NULL,
    id_avion INT NOT NULL,
    descrip TEXT NOT NULL,
    id_estado_revision INT NOT NULL,
    FOREIGN KEY (id_estado_revision) REFERENCES estado_revision(id_estado),
    Foreign Key (id_avion) REFERENCES avion (id_avion)
);

CREATE TABLE IF NOT EXISTS revision_empleado(
    id_revision_empleado INT PRIMARY KEY AUTO_INCREMENT,
	id_revision INT NOT NULL,
    id_empleado VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_revision) REFERENCES revision(id_revision),
	FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);


CREATE TABLE IF NOT EXISTS vuelo (
    id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
    numero_vuelo VARCHAR(20) NOT NULL UNIQUE,
    aeropuerto_origen INT NOT NULL,
    aeropuerto_destino INT NOT NULL,
    hora_salida VARCHAR(12) NOT NULL,
    hora_llegada VARCHAR(12) NOT NULL,
    FOREIGN KEY (aeropuerto_origen) REFERENCES aeropuerto (id_aeropuerto),
    FOREIGN KEY (aeropuerto_destino) REFERENCES aeropuerto (id_aeropuerto)
);



CREATE TABLE IF NOT EXISTS escala(
    id_vuelo INT,
    id_trayecto INT NOT NULL,
    Foreign Key (id_vuelo) REFERENCES vuelo(id_vuelo),
    Foreign Key (id_trayecto) REFERENCES trayecto(id_trayecto)
);

-- TABLA @TRIPULACION

CREATE TABLE IF NOT EXISTS tripulacion(
    id_vuelo INT NOT NULL,
    id_empleado VARCHAR(20) ,
    Foreign Key (id_vuelo) REFERENCES vuelo(id_vuelo),
    Foreign Key (id_empleado) REFERENCES empleado(id_empleado)
);


CREATE TABLE IF NOT EXISTS metodoPago (
    id_metodoPago INT PRIMARY KEY AUTO_INCREMENT,
    nombre_metodo VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    fecha_pago VARCHAR(12) NOT NULL,
    total_pago DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES reserva (id_reserva)
);


-- PODEMOS CREAR UNA VISTA QUE GENERE LA FACTURA
CREATE TABLE factura_Neta (
    id_factura_Neta INT AUTO_INCREMENT PRIMARY KEY,
    id_metodoPago INT NOT NULL,
    fecha_emision VARCHAR(12) NOT NULL,
    id_pago INT NOT NULL,
    FOREIGN KEY (id_metodoPago) REFERENCES metodoPago (id_metodoPago),
    Foreign Key (id_pago) REFERENCES pago (id_pago)
);


-- CREACION USERS AEREOPUERTO
CREATE TABLE IF NOT EXISTS rolUsuario (
    id_rolUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(40)
);
CREATE TABLE IF NOT EXISTS permisosUsuarios (
    id_permisosUsuarios INT PRIMARY KEY AUTO_INCREMENT,
    nombre_permiso VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS rol_permiso (
    id_rolUsuario INT NOT NULL,
    id_permisosUsuarios INT NOT NULL,
    Foreign Key (id_rolUsuario) REFERENCES rolUsuario (id_rolUsuario),
    Foreign Key (id_permisosUsuarios) REFERENCES permisosUsuarios (id_permisosUsuarios)
);
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    id_rolUsuario INT NOT NULL,
    Foreign Key (id_rolUsuario) REFERENCES rolUsuario (id_rolUsuario)
);
SHOW TABLEs;
-- SELECT PU.nombre_permiso
-- FROM
--     permisosUsuarios AS PU
--     INNER JOIN rol_permiso AS RP ON PU.id_permisosUsuarios = RP.id_permisosUsuarios
-- WHERE
--     RP.id_rolUsuario = ?;

-- CREATE VIEW vista_detalle_tripulacion AS
-- SELECT 
--     CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ', COALESCE(EM.apellidos, '')) AS Empleado,
--     VU.id_vuelo AS Codec_vuelo,
--     TR.nombre AS Rol_Empleado,
--     AO.nombre AS Aeropuerto_Origen,
--     AD.nombre AS Aeropuerto_Destino,
--     VU.hora_salida AS Hora_Salida,
--     VU.hora_llegada AS Hora_Llegada
-- FROM 
--     tripulacionRol AS TR
-- INNER JOIN 
--     empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles
-- INNER JOIN 
--     tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado
-- INNER JOIN 
--     detalle_vuelo AS DT ON TE.id_detalle_vuelo = DT.id_detalle_vuelo
-- INNER JOIN 
--     escala AS ES ON DT.id_detalle_vuelo = ES.id_detalle_vuelo
-- INNER JOIN 
--     vuelo AS VU ON ES.id_vuelo = VU.id_vuelo
-- INNER JOIN 
--     aeropuerto AS AO ON VU.aeropuerto_origen = AO.id_aeropuerto
-- INNER JOIN 
--     aeropuerto AS AD ON VU.aeropuerto_destino = AD.id_aeropuerto;


-- SELECT * FROM vista_detalle_tripulacion WHERE Codec_vuelo = 1;