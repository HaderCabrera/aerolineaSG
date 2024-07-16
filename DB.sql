DROP DATABASE IF EXISTS railway;
CREATE DATABASE railway;
USE railway;
CREATE TABLE IF NOT EXISTS tipoDocumento (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    nombreDoc VARCHAR(50)
);
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

CREATE TABLE IF NOT EXISTS revision (
    id_revision INT PRIMARY KEY AUTO_INCREMENT,
    fecha_revision VARCHAR(12) NOT NULL,
    descrip TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS avion (
    id_avion INT PRIMARY KEY AUTO_INCREMENT,
    placa_identificacion VARCHAR(30) NOT NULL,
    capacidad INT not NULL,
    fabricacion_fecha VARCHAR(12) NOT NULL,
    id_estado INT NOT NULL,
    id_modelo INT NOT NULL,
    id_revision INT NOT NULL,
    Foreign Key (id_estado) REFERENCES estadoAvion (id_estado),
    Foreign Key (id_modelo) REFERENCES modelo (id_modelo),
    Foreign Key (id_revision) REFERENCES revision(id_revision)
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
/*
 La tabla de escalas contiene información sobre las escalas que hace un vuelo. Incluye el ID del vuelo, el ID del aeropuerto donde se realiza la escala, y las horas de llegada y salida de la escala.
 vuelo_id: Este campo es una clave foránea que referencia a la tabla de Vuelos, indicando a qué vuelo pertenece la escala.
 aeropuerto_id: Este campo es una clave foránea que referencia a la tabla de Aeropuertos, indicando en qué aeropuerto se realiza la escala.
 */
CREATE TABLE escala (
    id_escala INT AUTO_INCREMENT PRIMARY KEY,
    id_vuelo INT NOT NULL,
    FOREIGN KEY (id_vuelo) REFERENCES vuelo (id_vuelo)
);
CREATE TABLE IF NOT EXISTS estadoPuesto (
    id_estadoPuesto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado_puesto VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS puesto (
    id_puesto INT PRIMARY KEY AUTO_INCREMENT,
    numero_puesto INT NOT NULL,
    id_estadoPuesto INT NOT NULL,
    Foreign Key (id_estadoPuesto) REFERENCES estadoPuesto (id_estadoPuesto)
);
CREATE TABLE IF NOT EXISTS detalle_vuelo (
    id_detalle_vuelo INT PRIMARY KEY AUTO_INCREMENT,
    id_puesto INT NOT NULL,
    Foreign Key (id_puesto) REFERENCES puesto (id_puesto)
);
-- CREAXCION DE TABLA ESCALA COMO TABLA INTERMEDIA DE DETALLE_VUELO Y vUELO
CREATE TABLE IF NOT EXISTS escala(
    id_escala INT,
    id_vuelo INT NOT NULL,
    Foreign Key (id_vuelo) REFERENCES vuelo(id_vuelo),
    Foreign Key (id_escala) REFERENCES escala(id_escala)
);
CREATE TABLE IF NOT EXISTS tripulacionvuelo_empleado (
    id_empleado VARCHAR(20) NOT NULL,
    id_detalle_vuelo INT NOT NULL,
    Foreign Key (id_empleado) REFERENCES empleado (id_empleado),
    Foreign Key (id_detalle_vuelo) REFERENCES detalle_vuelo (id_detalle_vuelo)
);


-- GESTION DE PAGOS Y METODOS DE PAGO
CREATE TABLE IF NOT EXISTS tipoClase (
    id_tipoClase INT PRIMARY KEY AUTO_INCREMENT,
    nombre_Clase VARCHAR(50)
);
--
/*Las tarifas en los vuelos se refieren a los diferentes precios que las aerolíneas o proveedores de servicios de vuelo ofrecen para los asientos en una aeronave. Estas tarifas pueden variar según varios factores, como la clase del asiento, la antelación con la que se realiza la reserva, la temporada del año, la duración del vuelo, entre otros.*/
CREATE TABLE IF NOT EXISTS tarifasVuelo (
    id_tarifasVuelo INT AUTO_INCREMENT PRIMARY KEY,
    id_vuelo INT NOT NULL,
    id_tipoClase INT NOT NULL,
    -- Económica, Business, Primera Clase
    precio_tarifa DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    -- Fecha de fin de la tarifa
    FOREIGN KEY (id_vuelo) REFERENCES vuelo (id_vuelo),
    Foreign Key (id_tipoClase) REFERENCES tipoClase (id_tipoClase)
);
-- GESTION DE RESERVAS Y CAPACIDAD
CREATE TABLE IF NOT EXISTS estadoReserva (
    id_estadoReserva INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS reserva (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    fecha_reserva VARCHAR(12) NOT NULL,
    id_vuelo INT NOT NULL,
    id_cliente INT NOT NULL,
    id_estadoReserva INT NOT NULL,
    Foreign Key (id_vuelo) REFERENCES vuelo (id_vuelo),
    Foreign Key (id_estadoReserva) REFERENCES estadoReserva (id_estadoReserva),
    Foreign Key (id_cliente) REFERENCES cliente (id_cliente)
);
CREATE TABLE IF NOT EXISTS metodoPago (
    id_metodoPago INT PRIMARY KEY AUTO_INCREMENT,
    nombre_metodo VARCHAR(60) NOT NULL
);
CREATE TABLE IF NOT EXISTS pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_tarifasVuelo INT NOT NULL,
    fecha_pago VARCHAR(12) NOT NULL,
    total_pago DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES reserva (id_reserva),
    FOREIGN KEY (id_tarifasVuelo) REFERENCES tarifasVuelo (id_tarifasVuelo)
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
--CREACION USERS AEREOPUERTO
CREATE TABLE IF NOT EXISTS rolUsuario (
    id_rolUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(40)
);
CREATE TABLE IF NOT EXISTS permisosUsuarios (
    id_permisosUsuarios INT PRIMARY KEY AUTO_INCREMENT,
    nombre_permiso VARCHAR(50),
    descripcion_permiso TEXT
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

