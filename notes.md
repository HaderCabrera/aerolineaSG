## MODIFICACIONES A LA BASE DE DATOS A TENER EN CUENTA

0. MODIFICAR ENTIDAD *TRIP*/*FLIGHCONNECTION*, LA TABLA INTERMEDIA ENTRE *TRIP* Y *PLANES* SE DEBE LLAMAR *ESCALA*, LA ENTIDAD *TRIP* SE DEBE AÑADIR INICIO Y DESTINO, DE IGUAL MANERA PARA LA ENTIDAD *ESCALA* SE DEBE AÑADIR INICIO Y DESTINO (SI INICIO/DESTINO CORRESPONDE A LO MISMO DE LA ENTIDAD *TRIP* QUIERE DECIR QUE NO TIENE ESCALAS).

1. AGREGAR ENTIDAD DE PAGO (PARA PODER REGITRAR PAGO).

2. HACER CONEXION ENTRE ENTIDAD *TRIPBOOKINGDETAILS* Y *PLANES* PARA DE ALGUNA MANERA MAPEAR LOS ASIENTOS DIPONIBLES.

3. AÑADIR ESTADO DE LA RESERVA PARA PODER GESTIONAR CASOS.

4. ELIMINAR *REVISION_DETAILS* Y COMPLEMENTAR ENTIDAD *REVISIONS* CON DETALLES DE LA REVISION.

## EXTRACCION DE ROLES Y PERMISOS

1. ADMINISTRADOR DEL SISTEMA: 
    1. REGISTRAR AVION, 
    2. ASIGNAR TRIPULACION A TRAYECTO,
    3. CONSULTAR INFORMACION DE AVION,
    4. CONSULTAR INFORMACION DE TRAYECTO,
    5. REGISTRAR AEROPUERTO,
    6. CONSULTAR INFORMACION DE AEROPUERTO,
    7. ACTUALIZAR INFORMACION DE AVION,
    8. ELIMINAR AVION,
    9. ASIGNAR AERONAVE A TRAYECTO,
    10. ACTUALIZAR INFORMACION DE TRAYECTO,
    11. ELIMINAR TRAYECTO,
    12. ACTUALIZAR INFORMACION DE AEROPUERTO,
    13. ELIMINAR AEROPUERTO,
    14. CONSULTAR INFORMACION DE VUELO, (SE PLANTE COMO LA MISMA 4)
    15. CONSULTAR ASIGNACION DE TRIPULACION,
    16. CONSULTAR ESCALA DE UN TRAYECTO, (CONSULTAR ESCALAS DE UN VUELO)
    17. ACTUALIZAR INFORMACION DE ESCALA,
    18. ELIMINAR ESCALA,
    19. REGISTRAR TARIFA DE VUELO,
    20. ACTUALIZAR INFORMACION DE TARIFA DE VUELO,
    21. ELIMINAR TARIFA DE VUELO,
    22. CONSULTAR TARIFA DE VUELO,
    23. ()REGISTRAR TIPO DE DOCUMENTO,
    24. ACTUALIZAR TIPO DE DOCUMENTO,
    25. ELIMINAR TIPO DE DOCUMENTO,
    26. CONSULTAR TIPO DE DOCUMENTO
			   
			   
2. CLIENTE: 
    1. BUSCAR VUELOS,
    2. SELECCIONAR VUELO,
    3. AÑADIR PASAJEROS,
    4. SELECCIONAR ASIENTOS,
    5. REALIZAR PAGO,
    6. CONSULTAR RESERVA DE VUELO,
    7. CANCELAR RESERVA DE VUELO,	   
    8. MODIFICAR RESERVA DE VUELO


3. AGENTE DE VENTAS: 
    1. CREAR RESERVA DE VUELO,
    2. CONSULTAR INFORMACION DEL CLIENTE,
    3. CONSULTAR RESERVA DE VUELO,
    4. REGISTRAR CLIENTE,
    5. ACTUALIZAR INFORMACION DEL CLIENTE,
    6. ELIMINAR RESERVA DE VUELO,
    7. CONSULTAR ASIGNACION DE TRIPULACION,
    8. CONSULTAR ESCALA DE UN TRAYECTO,
    9. CONSULTAR TARIFA DE VUELO,
    10. CONSULTAR TIPO DE DOCUMENTO
        	 
		  

4. TECNICO DE MANTENIMIENTO: 
    1. REGISTRAR REVISION DE MANTENIMIENTO,
    2. CONSULTAR HISTORIAL DE REVISIONES DE AVIONES,
    3. ACTUALIZAR INFORMACION DE REVISION,
    4. ELIMINAR REVISION DE MANTENIMIENTO

## EXTRACCION DE ENTIDADES Y SERVICIOS
1. AVION: 
    1. REGISTRAR AVION
    2. CONSULTAR INFORMACION DE AVION
    3. ACTUALIZAR INFORMACION DE AVION
    4. ELIMINAR AVION
	  

2. DETALLEVUELO
    1. ASIGNAR TRIPULACION A TRAYECTO
    2. CONSULTAR INFORMACION DE TRAYECTO / CONSULTAR ESCALA DE UN TRAYECTO
    3. ASIGNAR AERONAVE A TRAYECTO
    4. ACTUALIZAR INFORMACIONb DE TRAYECTO
    5. ELIMINAR TRAYECTO
    6. CONSULTAR ASIGNACION DE TRIPULACION
    7. ACTUALIZAR INFORMACION DE ESCALA
    8. ELIMINAR ESCALA
	     
	     
3. AEROPUERTO: 
    1. REGISTRAR AEROPUERTO
    2. CONSULTAR INFORMACION DE AEROPUERTO
    3. ACTUALIZAR INFORMACION DE AEROPUERTO
    4. ELIMINAR AEROPUERTO
	       
4. VUELO: 
    1. CONSULTAR INFORMACION VUELO
    2. CONSULTAR ESCALAS DE UN VUELO
    7. BUSCAR VUELOS
    8. SELECCIONAR VUELO
    
	  
5. TIPO DOCUMENTO: 
    1. REGISTRAR TIPO DE DOCUMENTO
    2. ACTUALIZAR TIPO DE DOCUMENTO
    3. ELIMINAR TIPO DE DOCUMENTO
    4. CONSULTAR TIPO DE DOCUMENTO

6. PAGO: 
    1. REALIZAR PAGO (REALIZAR PUSH A LA TABLA)

8. CLIENTE: 
    1. REGISTRAR RESERVA
    2. CONSULTAR INFORMACION DEL CLIENTE
    3. ACTUALIZAR INFORMACION DEL CLIENTE
    3. ELIMINAR CLIENTE


11. REVISION: 
    1. REGISTRAR REVISION DE MANTENIMIENTO
    2. CONSULTAR HISTORIAL DE REVISIONES DE AVIONES
    3. ACTUALIZAR INFORMACION DE REVISION
    4. ELIMINAR REVISION DE MANTENIMIENTO


13. RESERVA:
    1. MODIFICAR ESTADO RESERVA
    2. ELIMINAR RESERVA DE VUELO 
    3. CONSULTAR RESERVA DE VUELO
    4. LISTAR RESERVA
    5. CREAR RESERVA DE VUELO

14. FACTURA
    1.  MOSTRAR DETALLES DE LA RESERVA

15. TARIFA DE VUELO
    1. EDITAR TARIFA DE VUELO
    2. CREAR UNA TARIFA DE VUELO ASIGNANDO(REVISAR QUE VUELO YA TIENE ASIGANADA UNA TARIFA -- PERO EL VUELO A ACOMPANADO DE TARIFA QUE VUELO VALE TANTO YA ES EL AVION COMO TAL )
    3. ELIMINAR LA TARIFA
    4. LISTAR TARIFAS DE VUELO
    5. CONSULTAR TARIFA DE VUELO




## pajazo mental

- **Al momento de generar la rezerva y el estado esta en Pendiente pago, se pues hace 
el push a la tabla Pago dado que solo esta reservando osea que el pago es una opcion que se puede generar desde el backend**
