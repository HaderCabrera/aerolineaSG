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
2. Registr