# Caso de Uso 4: Registrar Revisión de Mantenimiento
1. Gestiono la creacion de una revision en la entidas **Revision**.
    1. Crear arquitectura hexagonal para entidad Revision con servicio **Registrar Revisión de Mantenimiento**
2. Asigno la revision anterior a un **Empleado** mediante la entidad intermedia **revision_empleado**
    1. Crear arquitectura hexagonal para entidad **revision_empleado** con servicio **Asignar Revision A Empleado**
3. Si no se logra realizar la asignacion del empleado a la revision anteriomente creada se debe elmiminar la revision de la base de datos.
