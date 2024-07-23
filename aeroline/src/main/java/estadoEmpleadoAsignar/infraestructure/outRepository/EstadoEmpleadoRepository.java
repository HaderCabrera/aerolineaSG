

package estadoEmpleadoAsignar.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import estadoEmpleadoAsignar.domain.entity.EstadoEmpleado;
import estadoEmpleadoAsignar.domain.service.EstadoEmpeladoService;





public class EstadoEmpleadoRepository implements  EstadoEmpeladoService{

    @Override
    public List<EstadoEmpleado> listarEstadosEmpleados() {
        String sql = "SELECT id_estado, estado FROM estado_empleado;";
        List<EstadoEmpleado> lstEestEstadoEmpleados = new ArrayList<>();
        EstadoEmpleado estadoEmpleado = null;

        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(sql)){
                try(ResultSet rs = stm.executeQuery()){
                    while(rs.next()){
                        estadoEmpleado = new EstadoEmpleado();

                        estadoEmpleado.setId_estado(rs.getInt("id_estado"));
                        estadoEmpleado.setNombreEstado(rs.getString("nombreEstado"));
                        lstEestEstadoEmpleados.add(estadoEmpleado);
                    }
                }
            }catch (SQLException e) {
            e.printStackTrace();

            }
            return  lstEestEstadoEmpleados;
    
    }
}
