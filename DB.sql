DROP DATABASE IF EXISTS railway;

CREATE DATABASE railway;
USE railway;

-- TABLA @TIPO_DOCUMENTO
CREATE TABLE IF NOT EXISTS tipoDocumento (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    nombreDoc VARCHAR(50)
);

-- TABLA @ESTADO_PUESTO
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
    Foreign Key (id_tipo_documento) REFERENCES tipoDocumento (id_tipo_documento) ON DELETE CASCADE
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
        Foreign Key (id_tipoClase) REFERENCES tipoClase (id_tipoClase) ON DELETE CASCADE
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
    Foreign Key (id_trayecto) REFERENCES trayecto(id_trayecto) ON DELETE CASCADE,
    Foreign Key (id_tarifa) REFERENCES tarifa(id_tarifa) ON DELETE CASCADE
);

-- TABLA @ESTADO_RESERVA

CREATE TABLE IF NOT EXISTS estadoReserva (
    id_estadoReserva INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado VARCHAR(50)
);

-- TABLA @RESERVA


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
    Foreign Key (id_pais) REFERENCES pais (id_pais) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS aeropuerto (
    id_aeropuerto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_ciudad VARCHAR(5) NOT NULL,
    Foreign Key (id_ciudad) REFERENCES ciudad (id_ciudad) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS puertaSalidaAbordaje (
    id_puertaSalidaAbordaje INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) not NULL,
    id_aeropuerto INT NOT NULL,
    Foreign Key (id_aeropuerto) REFERENCES aeropuerto (id_aeropuerto) ON DELETE CASCADE
);
-- crear ticket
CREATE TABLE estado_empleado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY, 
    estado VARCHAR(50) NOT NULL
    );
-- TABLE @EMPLEADO
CREATE TABLE IF NOT EXISTS empleado (
    id_empleado VARCHAR(20) PRIMARY KEY,
    nombre1 VARCHAR(40) NOT NULL,
    nombre2 VARCHAR(40) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    id_tripulacionRoles INT NULL NULL,
    id_aerolineas INT NOT NULL,
    id_estado INT NOT NULL, 
    Foreign Key (id_tripulacionRoles) REFERENCES tripulacionRol (id_tripulacionRoles) ON DELETE CASCADE,
    Foreign Key (id_aerolineas) REFERENCES aerolinea (id_aerolineas) ON DELETE CASCADE,
    Foreign Key (id_estado) REFERENCES estado_empleado(id_estado) ON DELETE CASCADE
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
    Foreign Key (id_manufactura) REFERENCES manufactura (id_manufactura) ON DELETE CASCADE
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
    Foreign Key (id_estado) REFERENCES estadoAvion (id_estado) ON DELETE CASCADE,
    Foreign Key (id_modelo) REFERENCES modelo (id_modelo) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS estadoPuesto (
    id_estadoPuesto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado_puesto VARCHAR(50) NOT NULL
);
-- TANLA @PUESTO
CREATE TABLE IF NOT EXISTS puesto (
    id_puesto INT PRIMARY KEY AUTO_INCREMENT,
    id_avion INT NOT NULL,
    id_estadoPuesto INT NOT NULL,
    numero_puesto INT NOT NULL,
    Foreign Key (id_estadoPuesto) REFERENCES estadoPuesto (id_estadoPuesto) ON DELETE CASCADE,
    Foreign Key (id_avion) REFERENCES avion(id_avion) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS reserva (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    fecha_reserva VARCHAR(12) NOT NULL,
    id_cliente INT NOT NULL,
    id_estadoReserva INT NOT NULL,
    id_puesto INT NOT NULL,
    id_tarifa INT NOT NULL,
    Foreign Key (id_tarifa) REFERENCES tarifa(id_tarifa) ON DELETE CASCADE,
    Foreign Key (id_puesto) REFERENCES puesto(id_puesto) ON DELETE CASCADE,
    Foreign Key (id_estadoReserva) REFERENCES estadoReserva (id_estadoReserva) ON DELETE CASCADE,
    Foreign Key (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE
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
    FOREIGN KEY (id_estado_revision) REFERENCES estado_revision(id_estado) ON DELETE CASCADE,
    Foreign Key (id_avion) REFERENCES avion (id_avion)
);

CREATE TABLE IF NOT EXISTS revision_empleado(
    id_revision_empleado INT PRIMARY KEY AUTO_INCREMENT,
	id_revision INT NOT NULL,
    id_empleado VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_revision) REFERENCES revision(id_revision) ON DELETE CASCADE,
	FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE
);

-- TABLA @VUELO
CREATE TABLE IF NOT EXISTS vuelo (
    id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
    numero_vuelo VARCHAR(20) NOT NULL,
    aeropuerto_origen INT NOT NULL,
    aeropuerto_destino INT NOT NULL,
    hora_salida VARCHAR(12) NOT NULL,
    hora_llegada VARCHAR(12) NOT NULL,
    id_avion  INT  NOT NULL,
    FOREIGN KEY (aeropuerto_origen) REFERENCES aeropuerto (id_aeropuerto) ON DELETE CASCADE,
    FOREIGN KEY (aeropuerto_destino) REFERENCES aeropuerto (id_aeropuerto) ON DELETE CASCADE,
    Foreign Key (id_avion) REFERENCES avion(id_avion) ON DELETE CASCADE
);

-- TABLA @ESCALA
CREATE TABLE IF NOT EXISTS escala(
    id_vuelo INT,
    id_trayecto INT NOT NULL,
    origen VARCHAR(40),
    destino VARCHAR(40),
    Foreign Key (id_vuelo) REFERENCES vuelo(id_vuelo) ON DELETE CASCADE,
    Foreign Key (id_trayecto) REFERENCES trayecto(id_trayecto) ON DELETE CASCADE
);

-- TABLA @TRIPULACION

CREATE TABLE IF NOT EXISTS tripulacion(
    id_tripulacion INT PRIMARY KEY AUTO_INCREMENT,
    id_vuelo INT,
    id_empleado VARCHAR(20) ,
    Foreign Key (id_vuelo) REFERENCES vuelo(id_vuelo) ON DELETE CASCADE,
    Foreign Key (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE
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
    FOREIGN KEY (id_reserva) REFERENCES reserva (id_reserva) ON DELETE CASCADE
);


-- PODEMOS CREAR UNA VISTA QUE GENERE LA FACTURA
CREATE TABLE factura_Neta (
    id_factura_Neta INT AUTO_INCREMENT PRIMARY KEY,
    id_metodoPago INT NOT NULL,
    fecha_emision VARCHAR(12) NOT NULL,
    id_pago INT NOT NULL,
    FOREIGN KEY (id_metodoPago) REFERENCES metodoPago (id_metodoPago) ON DELETE CASCADE,
    Foreign Key (id_pago) REFERENCES pago (id_pago) ON DELETE CASCADE
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
    Foreign Key (id_rolUsuario) REFERENCES rolUsuario (id_rolUsuario) ON DELETE CASCADE,
    Foreign Key (id_permisosUsuarios) REFERENCES permisosUsuarios (id_permisosUsuarios) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    id_rolUsuario INT NOT NULL,
    Foreign Key (id_rolUsuario) REFERENCES rolUsuario (id_rolUsuario) ON DELETE CASCADE
);
SHOW TABLEs;

