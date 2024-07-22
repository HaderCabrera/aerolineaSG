151841511L
# Caso de Uso 4: Registrar Revisión de Mantenimiento
1. Gestiono la creacion de una revision en la entidas **Revision**.
    1. Crear arquitectura hexagonal para entidad Revision con servicio **Registrar Revisión de Mantenimiento**
    2. Agregar servicio a la entidad Servicio **Eliminar Revision de Mantenimiento**
2. Asigno la revision anterior a un **Empleado** mediante la entidad intermedia **revision_empleado**
    1. Crear arquitectura hexagonal para entidad **revision_empleado** con servicio **Asignar Revision A Empleado**
3. Si no se logra realizar la asignacion del empleado a la revision anteriomente creada se debe 
   eliminar la revision que se creo. Esto se hace porque suponemos que es obligatorio asignar empleado a revision.
4. Modificaciones a DB: Insercion de datos en entidad **Empleado**.

# Caso de Uso 34: Registrar Tipo de Documento
1. Crear arquitectura hexagonal para entidad **tipoDocumento**
    1. Asignar servicio **Registrar Tipo Documento**

#  Caso de Uso 5: Consultar Información de Cliente.
1. Crear arquitectura hexagonal para la entidad Cliente;
    1. Realizar insert en la tabla tipo_documentos para poder crear un Cliente
    2. Realizar un insert de un cliente para poder consultar.
    3. Agregar servicio **Consultar Información de Cliente**

**Dejo registro de cliente a Eliezer ya que hace parte de gestionar trayecto**

# Caso de Uso 8: Consultar Información de Avión
1. Agregar servicio de **Consultar Informacion de Avion** a entidad Avion.
    1. Creacion de procedimiento para obtener datos.

# Caso de Uso 10: Registrar Aeropuerto
1. Hacer Insert de SQL en tablas **Pais** y **Ciudad**
2. Crear arquitectura hexagonal para la entidad aeropuerto
    1. Agregar servicio **Registrar Aeropuerto**
    2. Gestionar entidad **Ciudad** para disponer del servicio **Listar ciudades** y poder agregarla a la vista de registro.

# Caso de Uso 11: Consultar Información de Aeropuerto.
1. Agregar servicio **Consultar informacion de Aeropuerto** a entiedad aeropuerto
    1. implementar procedimiento en SQL.

# Caso de Uso 12: Consultar Historial de Revisiones de Avión
1. Agregar servicio **Consultar Historial de revision de Avion**
    1. implementar procedimiento en SQL.

# Caso de Uso 13(22): Actualizar Información de Cliente
2. Crear servicio en entidad Cliente **Actualizar Información de Cliente** (USAMOS CONSUMER).

# Caso de Uso 15: Actualizar Información de Avión
1. Crear estrcutura Hexagonal para entidad **modeloAvion** y **estadoAvion**
2. Agregar servicio de **Actualizar Avion**.

# Caso de Uso 16: Eliminar Avión
1. Agregar servicio de **Eliminar Avion** en entidad Avion.

# Caso de Uso 20: Actualizar Información de Aeropuerto
1. Agregar servicio **Actualizar informacion de aeropuerto** a entidad aeropuerto.

# Caso de Uso 21: Eliminar Aeropuerto (FALA ELIMINAR EN CASCADA O BUSCAR EL COMANDO)
1. Agregar servicio **Eliminar Aeropuerto**

# Caso de Uso 24: Actualizar Información de Revisión
1. Agregar servicio **Consultar Revision By ID**
2. Agregar servicio **Actualizar Revision**

# Caso de Uso 25: Eliminar Revisión de Mantenimiento (FALA ELIMINAR EN CASCADA O BUSCAR EL COMANDO)
1. Utilizar servicio **Eliminar revision de Mantenimiento**

# Caso de Uso 35: Actualizar Tipo de Documento
1. Agregar servicio **Consultar Tipo Documento**
1. Agregar servicio **Actualizar tipo Documento** en entidad TipoDocumento

# Caso de Uso 36: Eliminar Tipo de Documento
1. Agregar servicio **Eliminar Tipo de Documento**

# Caso de Uso 37: Consultar Tipo de Documento
1. Hacer uso de servicio **Consultar tipo Documento**

# Caso de Uso 3: Crear Reserva de Vuelo
1. Generar inseerts necesarios para las consultas
2. Gestionar entidad **Puesto**, **Estado Reserva**, **tipoClase**, **tarifa**, **Reserva**
    1. Gestionar la información de las reservas de vuelos, incluyendo la fecha y trayecto asociado.
    2. Registrar detalles específicos de cada reserva, incluyendo el cliente, tarifa y valor de la tarifa.


