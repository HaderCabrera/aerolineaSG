package detallevuelo.infraestructure.outRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import com.aeroline.DatabaseConfig;


import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import empleado.domain.entity.empleado;


public class DetalleVueloRepository implements DetalleVueloService {
    @Override

    public DetalleVuelo consultarTrayecto(int Numero_Vuelo) {
        String sql = "CALL abstraerTrayecto_Escalas (?);";
        DetalleVuelo detalleVuelo = null;

        

        
        try (Connection conexion = DatabaseConfig.getConnection();
            CallableStatement sentenciaPreparada = conexion.prepareCall(sql)){

            sentenciaPreparada.setInt(1, Numero_Vuelo);
            try(ResultSet resultset = sentenciaPreparada.executeQuery()){
                while(resultset.next()) {
                    detalleVuelo = new DetalleVuelo();
                    
                    detalleVuelo.setId_trayecto(resultset.getInt("ID_trayecto"));
                    detalleVuelo.setDestino_trayecto(resultset.getString("origen_trayecto"));
                    detalleVuelo.setOrigen_trayecto(resultset.getString("destino_trayecto"));
                    detalleVuelo.setDesc_trayecto(resultset.getString("desc_trayecto"));
                    detalleVuelo.setDistancia(resultset.getString("distancia"));
                    detalleVuelo.setNumero_vuelo(resultset.getString("numero_vuelo"));
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
    public DetalleVuelo actualizarTrayecto(int id_trayecto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DetalleVuelo asignarTripulacionTrayecto(empleado empleado) {
        
        return null;
    }

    @Override
    public void eliminarTrayecto(int id_treayecto) {
        // TODO Auto-generated method stub
        
    }

   
}
