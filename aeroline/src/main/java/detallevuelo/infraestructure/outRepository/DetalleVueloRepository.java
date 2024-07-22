package detallevuelo.infraestructure.outRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;



import com.aeroline.DatabaseConfig;


import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import empleado.domain.entity.empleado;


public class DetalleVueloRepository implements DetalleVueloService {

    @Override
    public DetalleVuelo asignarTripulacionTrayecto(empleado empleado) {
        

        return null;
            
    }

    @Override
    public DetalleVuelo consultarTracayecto(int id_trayecto) {
        DetalleVuelo detalleVuelo = null;
        String query = "CALL abstraerTrayecto_Escalas(?);";
        try (Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){
                
                stm.setInt(1, id_trayecto);

                try(ResultSet resultset = stm.executeQuery()){
                    if(resultset.next()) {
                        detalleVuelo = new DetalleVuelo();

                        detalleVuelo.setId_trayecto(resultset.getInt("ID_trayecto"));
                        detalleVuelo.setDesc_trayecto(resultset.getString("desc_trayecto"));
                        detalleVuelo.setOrigen_trayecto(resultset.getString("origen_trayecto"));
                        detalleVuelo.setDistancia(resultset.getString("distancia"));
                        detalleVuelo.setTimpoEstimado(resultset.getString("TiempoEstimado"));

                    }else return null;
                }

            }catch (SQLException e) { 
                e.printStackTrace();
                String mensaje = "Registro Fallido!"; 
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
            }
        
        return detalleVuelo;
    }

    @Override
    public boolean editarTrayecto(DetalleVuelo trayecto) {
        String query = "UPDATE trayecto\n" + //
                        "SET origen_trayecto = ?, \n" + //
                        "    destino_trayecto = ?,\n" + //
                        "    desc_trayecto = ?,\n" + //
                        "    distancia = ?\n" + //
                        "    TiempoEstimado = ?\n" + //
                        "WHERE id_trayecto = ?;";
                try(Connection conec = DatabaseConfig.getConnection();
                    PreparedStatement stm = conec.prepareStatement(query)){
                        stm.setString(1, trayecto.getOrigen_trayecto());
                        stm.setString(2, trayecto.getDestino_tracyecto());
                        stm.setString(3, trayecto.getDesc_trayecto());
                        stm.setString(4, trayecto.getDistancia());
                        stm.setString(5, trayecto.getTimpoEstimado());
                        stm.setInt(6, trayecto.getId_trayecto());


                    }catch(SQLException e){
                        e.printStackTrace();
                        return false;
                    }

        return true;
    }

    @Override
    public boolean eliminarTrayecto(int id_trayecto) {
        String query = "DELETE FROM trayecto WHERE id_trayecto = ?;";
        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){
                stm.setInt(1, id_trayecto);
                stm.executeUpdate();
                return true;

            }catch(SQLException e){
                e.printStackTrace();
            }
        return true;
        
    }
    

}
