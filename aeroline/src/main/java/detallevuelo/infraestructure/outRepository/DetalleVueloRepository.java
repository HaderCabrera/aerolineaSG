package detallevuelo.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import com.aeroline.DatabaseConfig;


import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;


public class DetalleVueloRepository implements DetalleVueloService {
    @Override

    public DetalleVuelo consultarDetalleVuelo(int Codec_vuelo) {
        String sql = "SELECT * FROM vista_detalle_tripulacion WHERE Codec_vuelo = ?;";
        DetalleVuelo detalleVuelo = null;

        

        
        try (Connection conexion = DatabaseConfig.getConnection();
                PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql)){

            sentenciaPreparada.setInt(1, Codec_vuelo);
            try(ResultSet resultset = sentenciaPreparada.executeQuery()){
                while(resultset.next()) {
                    detalleVuelo = new DetalleVuelo();
                    detalleVuelo.setId_detalle_vuelo(resultset.getInt("Codec_vuelo"));
                    detalleVuelo.setEmpleado(resultset.getString("Empleado"));
                    detalleVuelo.setRolEmpleado(resultset.getString("Rol_Empleado"));
                    detalleVuelo.setAeropuertoDestino(resultset.getString("Aeropuerto_Origen"));
                    detalleVuelo.setAeropuertoOrigen(resultset.getString("Aeropuerto_Destino"));
                    detalleVuelo.setHoraLlegada(resultset.getString("Hora_Salida"));
                    detalleVuelo.setHoraSalida(resultset.getString("Hora_Llegada"));
        


                }

            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            String mensaje = "Consulta Fallida!";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
        }
        return detalleVuelo;

    }

    @Override
    public DetalleVuelo consultarInfoTripulacion(int id_empleado) {

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
