package detallevuelo.infraestructure.outRepository;


import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class DetalleVueloRepository implements DetalleVueloService {
    @Override
    public DetalleVuelo consultarDetalleVuelo(int id_vuelo){

        return null;
        
    }

    @Override
    public DetalleVuelo asignarEmpleadoTripulacion(int id_empleado) {
        // listar Empleados Disponibles
        String sql = "SELECT CONCAT(EM.nombre1, ' ', COALESCE(EM.nombre2, ''), ' ',     COALESCE(EM.apellidos, '')) AS Empleado,\r\n" + //
                        "TR.nombre AS Rol_Empleado\r\n" + //
                        "FROM tripulacionRol AS TR\r\n" + //
                        "INNER JOIN empleado AS EM ON TR.id_tripulacionRoles = EM.id_tripulacionRoles\r\n" + //
                        "INNER JOIN tripulacionvuelo_empleado AS TE ON EM.id_empleado = TE.id_empleado;";
        System.out.println(sql);
        return null;
    }

    @Override
    public DetalleVuelo consultarInfoTripulacion(int id_empleado) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DetalleVuelo editarEscalaVuelo(int id_escala) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminarDetalleVuelo(int id_detalle_vuelo) {
        // TODO Auto-generated method stub
        
    }
}
